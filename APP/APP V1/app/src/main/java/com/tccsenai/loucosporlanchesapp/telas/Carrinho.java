package com.tccsenai.loucosporlanchesapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tccsenai.loucosporlanchesapp.R;

public class Carrinho extends AppCompatActivity {
    int id =0;
    double preco =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);


        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        preco = extras.getDouble("preco");
        String nomeProduto = extras.getString("nome");
        String descProduto = extras.getString("descricao");
        String img = extras.getString("byteImagem");

    }
}