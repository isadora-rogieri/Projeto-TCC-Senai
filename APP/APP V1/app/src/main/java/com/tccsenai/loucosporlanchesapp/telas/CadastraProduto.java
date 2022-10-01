package com.tccsenai.loucosporlanchesapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tccsenai.loucosporlanchesapp.R;

public class CadastraProduto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_produto);
        getSupportActionBar().hide();

        EditText produto = findViewById(R.id.edNomeProduto);
        EditText descricao = findViewById(R.id.edDescricao);
        EditText preco = findViewById(R.id.edPreco);


        Button cadastrarProduto = findViewById(R.id.btCadastrarProduto);


       /* Button btProdutos = findViewById(R.id.btListaProdutos);
        Button bthome = findViewById(R.id.btHome);

        btProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CadastraProduto.this, ListaProdutos.class);
                startActivity(it);
            }
        });

        bthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CadastraProduto.this, LoginActivity.class);
                startActivity(it);
            }
        });*/
    }
}