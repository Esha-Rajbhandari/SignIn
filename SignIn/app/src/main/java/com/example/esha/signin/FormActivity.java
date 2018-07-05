package com.example.esha.signin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.esha.signin.Utility.DisplayMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//start activity for result
//spinner, wiki, read me

public class FormActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout txtInputFName;
    private TextInputLayout txtInputLName;
    private TextInputLayout txtUsername;
    private TextInputLayout txtEmail;
    private TextInputLayout txtPassword;
    private Button btnRegister;
    private Button btnCancelReg;
    private String username;
    private String password;
    private RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        onClickAction();
    }

    private void onClickAction() {
        txtInputFName = findViewById(R.id.textInputFirstName);
        txtInputLName = findViewById(R.id.textInputLastName);
        txtUsername = findViewById(R.id.textInputUsername);
        txtEmail = findViewById(R.id.textInputEmail);
        txtPassword = findViewById(R.id.textInputPassword);
        rgGender = findViewById(R.id.radioGender);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancelReg = findViewById(R.id.btnCancelRegister);


        btnRegister.setOnClickListener(this);
        btnCancelReg.setOnClickListener(this);
    }

    private void reset() {
        txtInputFName.getEditText().setText("");
        txtInputLName.getEditText().setText("");
        txtUsername.getEditText().setText("");
        txtEmail.getEditText().setText("");
        txtPassword.getEditText().setText("");
    }

    private void getFormData() {
        String fName = txtInputFName.getEditText().getText().toString().trim();
        String lName = txtInputLName.getEditText().getText().toString().trim();
        username = txtUsername.getEditText().getText().toString().trim();
        String email = txtEmail.getEditText().getText().toString().trim();
        password = txtPassword.getEditText().getText().toString().trim();
        int selectedId = rgGender.getCheckedRadioButtonId();

        RadioButton rbGender = findViewById(selectedId);
        String gender = rbGender.getText().toString().trim();

        if (TextUtils.isEmpty(fName) || TextUtils.isEmpty(lName) || TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password))

            DisplayMessage.displayMessage(FormActivity.this, "The field cannot be empty", true);
        else if (username.length() > 15) {
            DisplayMessage.displayMessage(FormActivity.this, "Cannot exceed the maximum character for username", true);
        } else {
            if(isValidPassword(password)){
                DisplayMessage.displayMessage(FormActivity.this, "Valid", false);
                DisplayMessage.displayMessage(FormActivity.this, "Requirement fulfilled", true);
                Intent intent= new Intent(FormActivity.this,SignInActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                intent.putExtra("gender",gender);
                startActivity(intent);
                finish();
            } else{
                DisplayMessage.displayMessage(FormActivity.this, "InValid", false);
            }

        }
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN;
        PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnRegister:
                getFormData();

                break;
            case R.id.btnCancelRegister:
                reset();
                break;
        }
    }
}
