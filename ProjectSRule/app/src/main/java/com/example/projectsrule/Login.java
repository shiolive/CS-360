package com.example.projectsrule;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity{

    EditText username, password;
    Button loginButton, registerButton;
    LoginDatabase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        DB = new LoginDatabase(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            //LOGIN ACTIVITY ADD RECORD TO DB
           @Override
           public void onClick(View view){
               String user = username.getText().toString();
               String passpass = password.getText().toString();

               if(user.isEmpty() || passpass.isEmpty())
                   makeText(Login.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
               else{
                   Boolean checkLogin = DB.checkUserPassword(user, passpass);
                   if (checkLogin){
                       makeText(Login.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                       startActivity(intent);
                   }
                   else{
                       makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                   }
               }
           }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            //REGISTER ACTIVITY CHECK DB AND ADD CREDENTIALS
            @Override
            public void onClick(View view){
                String user = username.getText().toString();
                String passpass = password.getText().toString();
                Boolean checkCredentials = DB.checkUsername(user);
                if(!checkCredentials){
                    Boolean insert = DB.insertData(user, passpass);
                    if(insert){
                        makeText(Login.this, "Registered Sucessfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        makeText(Login.this, "Registration Failed, Please Try Again.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    makeText(Login.this, "User already exists, Please Log in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
