package com.anoop.dpbsadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText adminEmail , adminPassword;
    private TextView tvShow;
    private RelativeLayout loginBtn;
    private String email,pass;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences =this.getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("islogin","false").equals("yes")){
            openDash();
        }

        adminEmail = findViewById(R.id.user_Email);
        adminPassword = findViewById(R.id.user_Password);
        tvShow = findViewById(R.id.txt_Show);
        loginBtn = findViewById(R.id.login_btn);

        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //hide
                if(adminPassword.getInputType() == 144){
                    //show
                    adminPassword.setInputType(129);
                    tvShow.setText("Hide");
                }else {
                    adminPassword.setInputType(144);
                    tvShow.setText("Show");
                }
                //to move the cursor to last position
                adminPassword.setSelection(adminPassword.getText().length());
            }

        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateData();
            }
        });
    }

    private void validateData() {

        email = adminEmail.getText().toString();
        pass = adminPassword.getText().toString();

        if (email.isEmpty()){
            adminEmail.setError("Required");
            adminEmail.requestFocus();
        }
        else if (pass.isEmpty()){
            adminPassword.setError("Required");
            adminPassword.requestFocus();
        }
        else if (email.equals("dpbsadmin@gmail.com")&& pass.equals("ADM194")){
            editor.putString("islogin","yes");
            editor.commit();
            Toast.makeText(this, "Login Succesfully", Toast.LENGTH_SHORT).show();
            openDash();
        }
        else{
            Toast.makeText(this, "Please check email and password again", Toast.LENGTH_SHORT).show();
        }
    }

    private void openDash() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }
}