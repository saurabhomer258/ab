package com.example.saurabhomer.bornbhukkad;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    static int start=0;
    Button btnSignUp,btnSignIn;
    TextView txtSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(start==0)
        {
                Intent splash=new Intent(this,Splash.class);
                startActivity(splash);
        }
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        txtSlogan = (TextView) findViewById(R.id.txtSlogan);

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");
        txtSlogan.setTypeface(face);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp=new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn=new Intent(MainActivity.this,SignIn.class);
                startActivity(signIn);
            }
        });


    }
}