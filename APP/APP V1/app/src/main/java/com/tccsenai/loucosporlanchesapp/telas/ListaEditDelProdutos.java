package com.tccsenai.loucosporlanchesapp.telas;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.tccsenai.loucosporlanchesapp.R;
import com.tccsenai.loucosporlanchesapp.adapter.ListEditDelAdapter;
import com.tccsenai.loucosporlanchesapp.model.Produto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListaEditDelProdutos extends AppCompatActivity {
    RecyclerView.Adapter reclyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Produto> listaProdutos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_edit_del_produtos);
        getSupportActionBar().hide();

        String url = "http://192.168.43.232:8080/api/hamburgueria/produtos";

        RequestQueue requisicao = Volley.newRequestQueue(ListaEditDelProdutos.this);


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

                                Produto p = new Produto(id, nome, preco, descricao, byteImagem);
                                listaProdutos.add(p);

                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();

                            }
                        }

                        RecyclerView recyclerTela = findViewById(R.id.reciclyerEditar);
                        layoutManager = new LinearLayoutManager(ListaEditDelProdutos.this);
                        recyclerTela.setLayoutManager(layoutManager);
                        reclyclerViewAdapter = new ListEditDelAdapter(ListaEditDelProdutos.this,listaProdutos);


                        recyclerTela.setAdapter(reclyclerViewAdapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(ListaEditDelProdutos.this, "Erro ao listar", Toast.LENGTH_LONG).show();

            }
        }
        );

        requisicao.add(config);

    }







}