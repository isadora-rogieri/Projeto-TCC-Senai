package com.tccsenai.loucosporlanchesapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.tccsenai.loucosporlanchesapp.R;

public class TermoEInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo_einfo);
        getSupportActionBar().hide();

    }
}