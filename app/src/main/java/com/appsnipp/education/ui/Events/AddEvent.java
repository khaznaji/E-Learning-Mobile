package com.appsnipp.education.ui.Events;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.Database.AppDataBase;
import com.appsnipp.education.Entity.Event;
import com.appsnipp.education.R;

import java.util.List;

public class AddEvent extends AppCompatActivity {
    Button saveButton;
    EditText name,desc,lieu;
    private AppDataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evenementadd);

        database=AppDataBase.getInstance(this);
        saveButton=findViewById(R.id.addevent);
        name=findViewById(R.id.nameevent);
        desc=findViewById(R.id.descevent);
        lieu=findViewById(R.id.aaa);

        saveButton.setOnClickListener(v -> {
            Event user = new Event(name.getText().toString(),desc.getText().toString(),lieu.getText().toString());
            database.userDAOo().insertTodo(user);
            System.out.println("amannnnnnnnnnnnnnnnnnnnnnnnnnn" + name);


            List<Event> lu = database.userDAOo().getAllTodos();
            for(Event userList :lu ){
                System.out.println(userList);
            }

        });
    }
}