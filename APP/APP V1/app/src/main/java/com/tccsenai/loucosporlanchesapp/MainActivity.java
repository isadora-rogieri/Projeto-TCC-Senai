package com.tccsenai.loucosporlanchesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tccsenai.loucosporlanchesapp.telas.CadastraUsuario;
import com.tccsenai.loucosporlanchesapp.telas.ListaProdutos;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btComeçar = findViewById(R.id.btComecar);
        Button btProdutos = findViewById(R.id.btListaProdutos);

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
    }
}