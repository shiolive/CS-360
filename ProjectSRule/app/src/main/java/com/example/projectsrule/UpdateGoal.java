package com.example.projectsrule;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateGoal extends AppCompatActivity {
    Button button_home, button_save;
    EditText edit_goal;
    GoalDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updategoal);

        button_home = findViewById(R.id.home_button);
        button_save = findViewById(R.id.save_goal);
        edit_goal = findViewById(R.id.editGoal);

        edit_goal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String update = edit_goal.getText().toString();

                Boolean checkSuccess = DB.insertData(update);
                if(!checkSuccess){
                    Boolean insert = DB.insertData(update);
                    if(insert){
                        makeText(UpdateGoal.this, "Goal Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        makeText(UpdateGoal.this, "Update Failed, Please Try Again.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    makeText(UpdateGoal.this, "User already exists, Please Log in", Toast.LENGTH_SHORT).show();
                }
            }

        });

        button_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                makeText(getApplicationContext(), "Going Home", Toast.LENGTH_SHORT).show();
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
}
