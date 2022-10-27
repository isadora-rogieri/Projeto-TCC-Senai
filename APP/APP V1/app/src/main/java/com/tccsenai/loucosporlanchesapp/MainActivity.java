package com.tccsenai.loucosporlanchesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tccsenai.loucosporlanchesapp.telas.CadastraProduto;
import com.tccsenai.loucosporlanchesapp.telas.CadastraUsuario;
import com.tccsenai.loucosporlanchesapp.telas.ListaProdutos;
import com.tccsenai.loucosporlanchesapp.telas.LoginActivity;
import com.tccsenai.loucosporlanchesapp.telas.TermoEInfoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Button btComeçar = findViewById(R.id.btComecar);
        Button btProdutos = findViewById(R.id.btListaProdutos);
        Button bthome = findViewById(R.id.btHome);
        Button btUser = findViewById(R.id.btDadosUser);
        Button btPedidos = findViewById(R.id.btPedidos);
        Button btLogin = findViewById(R.id.btTelaLogin);
        FloatingActionButton btInfo = findViewById(R.id.floaBtInfo);


        btComeçar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CadastraUsuario.class);
                startActivity(it);


            }
        });

        btProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, ListaProdutos.class);
                startActivity(it);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

        btPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, TermoEInfoActivity.class);
                startActivity(it);
            }
        });



    }
}