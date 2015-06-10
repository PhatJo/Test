package com.example.kiptoomagutt.testas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Thread.currentThread().setUncaughtExceptionHandler(
                new DumpHandler(getApplicationInfo().dataDir, this)
        );*/
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem testMenuItem = menu.findItem(R.id.action_test);

        View testView = testMenuItem.getActionView();
        TextView textView = (TextView) testView.findViewById(R.id.id_action_test_text_view);

        return super.onPrepareOptionsMenu(menu);
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

    public void onButtonTestScrollClicked(View view) {
        Intent intent = new Intent(this, ScrollActivity.class);
        startActivity(intent);
    }

    public void onButtonToolBarClicked(View view) {
        Intent intent = new Intent(this, ToolBarActivity.class);
        startActivity(intent);
    }
}
