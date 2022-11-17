package com.tccsenai.loucosporlanchesapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tccsenai.loucosporlanchesapp.R;

public class DetalhesProduto extends AppCompatActivity {

    int idProduto =0;
    double preco;
    ImageView imgProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);
        getSupportActionBar().hide();

        Intent dados = getIntent();
        idProduto = dados.getIntExtra("id", 0);
        String nome = dados.getStringExtra("nome");
        String descricao = dados.getStringExtra("descricao");
        preco = dados.getDoubleExtra("preco",0);
        String byteImagem = dados.getStringExtra("byteImagem");

        TextView edNome = findViewById(R.id.textVDetNomeProduto);
        TextView edDescricao = findViewById(R.id.textVDetdescricaoProduto);
        TextView edPreco = findViewById(R.id.textVDetPrecoProduto);
        imgProduto = findViewById(R.id.imgDetalhesProduto);

        edNome.setText(nome);
        edDescricao.setText(descricao);
        edPreco.setText(String.valueOf(preco));
        imgProduto.setImageBitmap(converteImagem(byteImagem));

        Button btAddCarrinho = findViewById(R.id.btDetalheAddCarrinho);

    }
    private Bitmap converteImagem(String imagem){
        byte[] bytes= Base64.decode(imagem,Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        // set bitmap on imageView

        return bitmap;
    }
}