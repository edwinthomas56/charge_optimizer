package com.example.joy.chargeless;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class mod extends AppCompatActivity {







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setContentView(R.layout.activity_mod);

        setTimeout(0);
        freeMemory();
        final View view = findViewById(R.id.view);
        Settings.System.putInt(getBaseContext().getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onAttachedToWindow();
        Settings.System.putInt(this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = 0.0f;
        // 100 / 100.0f;
        getWindow().setAttributes(lp);

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);


        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        }
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);


        ImageView button = (ImageView) findViewById(R.id.imageView7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                startActivity(i);
            }
        });
        ImageView button1 = (ImageView) findViewById(R.id.imageView2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an Intent with the contents of the TextView
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");


                // Make sure the provider knows
                // it should work with that Intent
                startActivity(shareIntent);

            }
        });
        ImageView button2=(ImageView) findViewById(R.id.imageView8);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


            }
        });
       view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                       | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                       | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                       | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                       | View.SYSTEM_UI_FLAG_FULLSCREEN
                       | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


           }
       });


//        PackageManager pm = getPackageManager(); // Get all methods on the PackageManager
//         Method[] methods = pm.getClass().getDeclaredMethods();
//        for (Method m : methods) {
//            if (m.getName().equals("freeStorage"))
//            { // Found the method I want to use
//                 try { long desiredFreeStorage = 8 * 1024 * 1024 * 1024;
//                     // Request for 8GB of free space
//                     m.invoke(pm, desiredFreeStorage , null);
//                 }
//                 catch (Exception e) { // Method invocation failed. Could be a permission problem }
//                      break; } }

                BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
                    int scale = -1;
                    int level = -1;
                    int voltage = -1;
                    int temp = -1;
                    public void onReceive(Context context, Intent intent) {
                        finishAffinity();
                        level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                        scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                        temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                        voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);

                        Log.e("BatteryManager", "level is "+level+"/"+scale+", temp is "+temp+", voltage is "+voltage);
                    }

                };

                IntentFilter filter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
                registerReceiver(batteryReceiver, filter);

            }

    private void setTimeout(int screenOffTimeout) {
        int time;
        switch (screenOffTimeout) {
            case 0:
                time = 15000;
                break;
            case 1:
                time = 30000;
                break;
            case 2:
                time = 60000;
                break;
            case 3:
                time = 120000;
                break;
            case 4:
                time = 600000;
                break;
            case 5:
                time = 1800000;
                break;
            default:
                time = -1;
        }
        android.provider.Settings.System.putInt(getContentResolver(),
                Settings.System.SCREEN_OFF_TIMEOUT, time);
    }
    public void freeMemory(){
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }
    @Override
    public void onBackPressed() {

    }


}
