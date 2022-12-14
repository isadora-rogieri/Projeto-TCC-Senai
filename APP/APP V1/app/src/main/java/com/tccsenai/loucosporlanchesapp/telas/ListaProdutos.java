package com.tccsenai.loucosporlanchesapp.telas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tccsenai.loucosporlanchesapp.MainActivity;
import com.tccsenai.loucosporlanchesapp.R;
import com.tccsenai.loucosporlanchesapp.adapter.ListaProdutosAdapter;
import com.tccsenai.loucosporlanchesapp.model.Produto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ListaProdutos extends AppCompatActivity {
    RecyclerView.Adapter reclyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Produto> listaProdutos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        getSupportActionBar().hide();
        FloatingActionButton adicionarNovoProduto = findViewById(R.id.floatBtCadastrarProd);
        Button btHome = findViewById(R.id.btHome3);

        Button btLogin = findViewById(R.id.btDadosUser3);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListaProdutos.this, MainActivity.class);
                startActivity(it);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListaProdutos.this, LoginActivity.class);
                startActivity(it);
            }
        });

        adicionarNovoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListaProdutos.this, CadastraProduto.class);
                startActivity(it);
            }
        });

        String url = "http://192.168.43.232:8080/api/hamburgueria/produtos";

        RequestQueue requisicao = Volley.newRequestQueue(ListaProdutos.this);


        JsonArrayRequest config = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject produto = response.getJSONObject(i);
                                int id = produto.getInt("id");
                                String nome = produto.getString("nome");
                                double preco = produto.getDouble("preco");
                                String descricao = produto.getString("descricao");
                                String byteImagem = produto.getString("byteImagem");

                                Produto p = new Produto(nome, preco, descricao, byteImagem);
                                listaProdutos.add(p);

                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();

                            }
                        }

                        RecyclerView recyclerTela = findViewById(R.id.produto_item);
                        layoutManager = new LinearLayoutManager(ListaProdutos.this);
                        recyclerTela.setLayoutManager(layoutManager);
                        reclyclerViewAdapter = new ListaProdutosAdapter(ListaProdutos.this,listaProdutos);


                        recyclerTela.setAdapter(reclyclerViewAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(ListaProdutos.this, "Erro ao listar", Toast.LENGTH_LONG).show();

            }
        }
        );
        requisicao.add(config);
    }




}