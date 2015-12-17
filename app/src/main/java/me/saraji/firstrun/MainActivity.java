package me.saraji.firstrun;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String SharedPref_firstRun = "First_Run_SharedPref";
    final String SharedPref_Boolean_name = "First_Run_Boolean";
    SharedPreferences SharedPref_first_Run = null;
    private TextView build_number;
    private TextView first_run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        build_number = (TextView)findViewById(R.id.build_number);
        first_run = (TextView)findViewById(R.id.first_run_stats);

        /*
        Load SharedPreferences For check First run Status
         */
        SharedPref_first_Run = getSharedPreferences(SharedPref_firstRun, Context.MODE_PRIVATE);

        Toast.makeText(getApplicationContext(),String.valueOf(BuildConfig.VERSION_NAME),Toast.LENGTH_LONG).show();
        if ( SharedPref_first_Run.getBoolean(SharedPref_Boolean_name,true)){
            Toast.makeText(getApplicationContext(),"First Run",Toast.LENGTH_LONG).show();
            SharedPref_first_Run.edit().putBoolean(SharedPref_Boolean_name,false).commit();
        }
        else
            Toast.makeText(getApplicationContext(),"Ran App before!",Toast.LENGTH_LONG).show();

        /*
        Parsing build number from version status and gain first run stats
         */

        String build_num = BuildConfig.VERSION_NAME.substring
                (BuildConfig.VERSION_NAME.lastIndexOf('.')+1);
        build_number.append(build_num);

        //Define first run status
        first_run.append(String.valueOf(SharedPref_first_Run.getBoolean(SharedPref_Boolean_name,true)));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
