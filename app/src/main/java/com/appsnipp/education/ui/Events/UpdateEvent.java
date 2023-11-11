/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.appsnipp.education.R.layout.eventupdate); // Set the layout XML for the UpdateEvent activity
        // You should have an eventupdate.xml layout file in your "res/layout" directory
    }
}

