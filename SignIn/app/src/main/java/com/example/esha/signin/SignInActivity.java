package com.example.esha.signin;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView tvPasswordForget;
    private TextView tvSignup;
    private String usernameText;
    private String passwordText;
    private String genderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        LayerDrawable drw = createHorizontallyStackedImages();
        ImageView iv1 = findViewById(R.id.img_media);
        iv1.setImageDrawable(drw);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        tvPasswordForget = findViewById(R.id.tv_password_forget_text);
        tvSignup = findViewById(R.id.tv_sign_up);

        tvPasswordForget.setOnClickListener(this);
        tvSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        readFormData();

    }

    private void readFormData() {
        Intent intent = getIntent();
        if (intent == null)
            return;

        usernameText = intent.getStringExtra("username");
        passwordText = intent.getStringExtra("password");
        genderText = intent.getStringExtra("gender");
        edtUsername.setText(usernameText);
        edtPassword.setText(passwordText);

    }

    private LayerDrawable createHorizontallyStackedImages() {
        BitmapDrawable d1 = (BitmapDrawable) getResources().getDrawable(R.drawable.fb);
        d1.setGravity(Gravity.LEFT);
        BitmapDrawable d2 = (BitmapDrawable) getResources().getDrawable(R.drawable.twitter);
        d2.setGravity(Gravity.LEFT);
        BitmapDrawable d3 = (BitmapDrawable) getResources().getDrawable(R.drawable.gplus);
        d3.setGravity(Gravity.LEFT);

        Drawable drawableArray[] = new Drawable[]{d1, d2, d3};
        LayerDrawable layerDraw = new LayerDrawable(drawableArray);
        layerDraw.setLayerInset(0, 0, 0, 0, 0);
        layerDraw.setLayerInset(1, 100, 0, 0, 0);
        layerDraw.setLayerInset(2, 200, 0, 0, 0);

        return layerDraw;
    }

    public void getInputData() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            if (username.equalsIgnoreCase(usernameText) && password.equalsIgnoreCase(passwordText)) {
                Toast.makeText(this, "Form submitted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                intent.putExtra("gender",genderText);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Cannot sign in", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void removeInputData() {
        edtUsername.setText("");
        edtPassword.setText("");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_login:
                getInputData();
                removeInputData();
                break;
            case R.id.tv_password_forget_text:
                Toast.makeText(this, "Now you can change the password", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_sign_up:
                Intent intent;
                intent = new Intent(SignInActivity.this, FormActivity.class);

                startActivity(intent);
                finish();
                break;
        }
    }
}
