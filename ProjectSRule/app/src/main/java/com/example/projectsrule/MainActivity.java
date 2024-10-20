package com.example.projectsrule;

import static android.widget.Toast.makeText;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectsrule.databinding.ActivityUpdategoalBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button_add, button_change, button_delete, button_settings, button_home;
    EditText edit_date, edit_weight;
    GridView grid_weight;
    TextView view_goal;
    UpdateGoal updateDB;
    Database DB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_add = findViewById(R.id.addData);
        button_delete = findViewById(R.id.delete_button);
        button_change = findViewById(R.id.edit_goal_button);
        button_settings = findViewById(R.id.settings_button);
        edit_date = findViewById(R.id.edit_date);
        edit_weight = findViewById(R.id.edit_weight);
        grid_weight = findViewById(R.id.Weight_Grid);


        Database database = new Database(MainActivity.this);
        List<UserModel> allUserData = database.getEveryone();

        ArrayAdapter userArray = new ArrayAdapter<UserModel>(MainActivity.this, android.R.layout.simple_list_item_1, allUserData);
        grid_weight.setAdapter(userArray);

        //add record to database
        button_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                UserModel userModel;
                try{
                    userModel = new UserModel(-1, edit_date.getText().toString(), Integer.parseInt(edit_weight.getText().toString()));
                    makeText(MainActivity.this, userModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    makeText(MainActivity.this, "Entry error", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1, "ERROR", 0);
                }
                Database database = new Database(MainActivity.this);
                boolean success = database.addOne(userModel);
                makeText(MainActivity.this, "Added Record: " + success, Toast.LENGTH_SHORT).show();
            }

        });
        //delete record from database
        button_delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                UserModel userModel;
                try{
                    userModel = new UserModel(-1, edit_date.getText().toString(), Integer.parseInt(edit_weight.getText().toString()));
                    makeText(MainActivity.this, userModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    makeText(MainActivity.this, "Entry error", Toast.LENGTH_SHORT).show();
                    userModel = new UserModel(-1, "ERROR", 0);
                }
                Database database = new Database(MainActivity.this);
                boolean success = database.removeOne(userModel);
                makeText(MainActivity.this, "Deleted Record: " + success, Toast.LENGTH_SHORT).show();
            }

        });
        //open sms settings - navigate to sms screen
        button_settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, SMS.class); //crashes??
                startActivity(intent);
                makeText(MainActivity.this, "SMS Settings", Toast.LENGTH_SHORT).show();
            }

        });
        //open change goal weight screen
        button_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, UpdateGoal.class); //crashes???
                startActivity(intent);
                makeText(MainActivity.this, "Update Goal", Toast.LENGTH_SHORT).show();
            }

        });


    }


    // fix mes: Check goal against current weight, add notification for reaching or surpassing goal,


}