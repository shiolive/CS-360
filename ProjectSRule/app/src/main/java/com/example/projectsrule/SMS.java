package com.example.projectsrule;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMS extends AppCompatActivity {
    Button button_home;
    EditText phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_message);

        button_home = findViewById(R.id.home_button);
        phone_number = findViewById(R.id.edit_PhoneNumber);

        phone_number.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Database database = new Database(SMS.this);
                makeText(SMS.this, "Number Saved", Toast.LENGTH_SHORT).show();
            }

        });

        button_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                makeText(getApplicationContext(), "Going Home", Toast.LENGTH_SHORT).show();
            }

        });

    }
    //switch compat saved state permission
    //how do i connect to SMS functionality to actually send messages
}
