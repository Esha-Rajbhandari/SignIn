package com.example.esha.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity{
    private TextView tvShowText;
    private TextView tvGenderText;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getValue();
    }

    private void getValue(){
        tvShowText=findViewById(R.id.tv_display);
        tvGenderText=findViewById(R.id.gender_display);
        Intent in=getIntent();
        if(in!=null){
            String strUsername=in.getStringExtra("username");
            String genderText=in.getStringExtra("gender");

            tvShowText.setText("Welcome to your dashboard "+strUsername);
            tvGenderText.setText("Gender "+genderText);
        }
    }
}
