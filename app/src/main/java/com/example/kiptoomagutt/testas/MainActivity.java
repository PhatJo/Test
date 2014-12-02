package com.example.kiptoomagutt.testas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Thread.currentThread().setUncaughtExceptionHandler(
                new DumpHandler(getApplicationInfo().dataDir, this)
        );*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onButtonTestModuleClicked(View view) {
        Intent intent = new Intent(this, com.example.mymodule.app2.MainActivity.class);
        startActivity(intent);
    }

    public void onButtonEnvironmentClicked(View view) {
        Intent intent = new Intent(this, EnvironmentActivity.class);
        startActivity(intent);
    }

    public void onButtonViewPagerClicked(View view) {
        Intent intent = new Intent(this, ScreenSlidePagerActivity.class);
        startActivity(intent);
    }

    public void onButtonTestMemoryClicked(View view) {
        //DumpHandler dumpHandler = new DumpHandler(getApplicationInfo().dataDir, this);
        //dumpHandler.dumpHeap();
        String a = null;

        Log.d("MainActivity", "will cause crash ere" + a.toLowerCase());

    }
}
