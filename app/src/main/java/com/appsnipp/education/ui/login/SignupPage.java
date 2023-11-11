/*
 * Copyright (c) 2023. rogergcc
 */

package com.appsnipp.education.ui.login;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.appsnipp.education.R;

import androidx.appcompat.app.AppCompatActivity;
public class SignupPage extends AppCompatActivity {

    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_page);
        back = findViewById(R.id.back);
        back.setOnClickListener(view -> finish());
    }}