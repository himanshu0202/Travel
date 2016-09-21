package com.example.himanshuanand.traveldiaries;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String CHICAGO_INTENT =
            "com.layout.travel.intentChicago";
    private static final String INDIANA_INTENT =
            "com.layout.travel.intentIndiana";
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button chicagoButton = (Button) findViewById(R.id.chicagoButton);
        Button indianaPolisButton = (Button) findViewById(R.id.indianapolisButton);

        chicagoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentChicago = new Intent();
                intentChicago.setAction(CHICAGO_INTENT);
                intentChicago.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                Toast.makeText(MainActivity.this, R.string.chicago_toast, Toast.LENGTH_SHORT).show();
                sendBroadcast(intentChicago);
            }
        });

        indianaPolisButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentIndiana = new Intent();
                intentIndiana.setAction(INDIANA_INTENT);
                intentIndiana.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                Toast.makeText(MainActivity.this, R.string.indianapolis_toast, Toast.LENGTH_SHORT).show();
                sendBroadcast(intentIndiana);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.content_main);

        }
        else
        {
            setContentView(R.layout.content_main);

        }
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
