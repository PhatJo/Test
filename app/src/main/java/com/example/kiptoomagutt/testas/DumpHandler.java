package com.example.kiptoomagutt.testas;

import android.content.Context;
import android.os.Debug;
import android.util.Base64;
import android.util.Log;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.CrashManagerListener;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by kiptoo on 10/16/14.
 */
public class DumpHandler implements  Thread.UncaughtExceptionHandler {
    private static final String HPROF_DUMP_BASENAME = "LeakingApp.dalvik-hprof";
    private static final String HOCKEY_APP_ID = "d2f48cca4bbe327f89dc003df2b67ad3";
    private final String dataDir;
    private final String TAG = "DumpHandler";
    private static Context mContext;
    public DumpHandler(String dataDir, Context context) {
        this.dataDir = dataDir;
        mContext = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        try {
            dumpHeap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ex.printStackTrace();
    }

    public void dumpHeap() {
        String absPath = new File(dataDir, HPROF_DUMP_BASENAME).getAbsolutePath();
        try {
            Debug.dumpHprofData(absPath);
            Log.d(TAG, "dumping hprof data to: " + absPath);
            // encode
            String encoded = encodeHprof(absPath);
            Log.d(TAG, "encoded string: " + encoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String encodeHprof(String filePath) {
        String base64Str = null;
        try {
            File file = new File(filePath);
            byte [] bytes = new byte[(int) file.length()];
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dis.readFully(bytes);
            base64Str = Base64.encodeToString(bytes, Base64.DEFAULT);
            //decodeToHprof(base64Str);
            sendDumpToHockey(base64Str);
            dis.close();
        } catch (Exception e) {
            Log.d(TAG, "error encoding file: " + e.getMessage());
        }

        return base64Str;
    }

    // write an hprof file from the base64 string
    public void decodeToHprof(String encoded) {
        final String decodedFileName = "LeakingApp.dalvik-hprof_decoded";
        String absPath = new File(dataDir, decodedFileName).getAbsolutePath();
        byte[] decoded = Base64.decode(encoded, Base64.DEFAULT);
        final File decodedFile = new File(absPath);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(decodedFile);
            outputStream.write(decoded);
            Log.d(TAG, "File successfully written to file");
            outputStream.close();
        }
        catch (Exception e) {
            Log.e(TAG, "error writing to decoded file: " + e.getMessage());
        }
    }

    public void sendDumpToHockey(final String dump) {
        CrashManager.register(mContext, HOCKEY_APP_ID, new CrashManagerListener() {
            public String getDescription() {
                final String desc = dump;
                return desc;
            }
        });
    }
}
