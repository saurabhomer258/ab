package com.example.saurabhomer.bornbhukkad;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = (ImageView) findViewById(R.id.iconimage);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blick);
        imageView.startAnimation(animation);
        MainActivity.start=1;
        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        Thread splash = new Thread() {
            public void run() {
                try {

                    //set sleep time

                    sleep(3 * 1000);

                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                } catch (Exception e) {

                }
            }
        };
        splash.start();

    }
}
