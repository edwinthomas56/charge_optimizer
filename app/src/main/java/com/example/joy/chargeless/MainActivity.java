package com.example.joy.chargeless;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private  Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar=(ProgressBar) findViewById(R.id.ProgressBar01);
        progressBar.setVisibility(View.INVISIBLE);
        ImageView button=(ImageView) findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                thread = new Thread(){
                    @Override
                    public void run() {

                        try {
                            while(true){
                           thread.sleep(60*1000);}
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                };thread.start();


;
                Intent intent=new Intent(MainActivity.this,mod.class);

                startActivity(intent);
                Toast.makeText(MainActivity.this, "Bluetooth and Wifi Disabled....             Battery saving mode ON",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
