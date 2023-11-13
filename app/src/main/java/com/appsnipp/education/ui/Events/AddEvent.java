package com.appsnipp.education.ui.Events;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.Database.AppDataBase;
import com.appsnipp.education.Entity.Event;
import com.appsnipp.education.R;
import com.appsnipp.education.ui.Complaints.AddArticle;

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
            String nom = name.getText().toString();
            String des = desc.getText().toString();
            String lie = lieu.getText().toString();
            Event user = new Event(name.getText().toString(),desc.getText().toString(),lieu.getText().toString());
            if (nom.isEmpty() || des.isEmpty() || lie.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }else{
            database.userDAOo().insertTodo(user);
            Toast.makeText(AddEvent.this,"Inserted",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddEvent.this, ListEventsFront.class));
                finish();
            System.out.println("amannnnnnnnnnnnnnnnnnnnnnnnnnn" + name);


            List<Event> lu = database.userDAOo().getAll();
            for(Event userList :lu ){
                System.out.println(userList);
            }}

        });
    }
}