package com.tccsenai.loucosporlanchesapp.telas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tccsenai.loucosporlanchesapp.MainActivity;
import com.tccsenai.loucosporlanchesapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        EditText edCPF = findViewById(R.id.edCPFLogin);
        EditText edSenha = findViewById(R.id.edSenhaLogin);
        Button btLogar = findViewById(R.id.btlogin);
        Button btProdutos = findViewById(R.id.btListaProdutos4);
        Button bthome = findViewById(R.id.btHome4);



        btProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, ListaProdutos.class);
                startActivity(it);
            }
        });

        bthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });
    }
}