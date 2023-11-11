/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.Events;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.education.R;

public class AddEvent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evenementadd); // Set the layout XML for the UpdateEvent activity
        // You should have an eventupdate.xml layout file in your "res/layout" directory
    }
}
