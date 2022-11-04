package com.tccsenai.loucosporlanchesapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tccsenai.loucosporlanchesapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CadastraProduto extends AppCompatActivity {
    Bitmap fotoEscolhida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_produto);
        getSupportActionBar().hide();

        EditText produto = findViewById(R.id.edNomeProduto);
        EditText descricao = findViewById(R.id.edDescricao);
        EditText preco = findViewById(R.id.edPreco);
        Button cadastrarProduto = findViewById(R.id.btCadastrarProduto);
        Button btSelecionaImagem = findViewById(R.id.btSelecImagem);


        Button btProdutos = findViewById(R.id.btListaProdutos5);
        Button bthome = findViewById(R.id.btHome5);
        Button btUser = findViewById(R.id.btDadosUser5);
        FloatingActionButton btEditeDel = findViewById(R.id.floatEditEDelet);

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
                Intent it = new Intent(CadastraProduto.this, ListaProdutos.class);
                startActivity(it);
            }
        });
        btUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CadastraProduto.this, LoginActivity.class);
                startActivity(it);
            }
        });
        btEditeDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CadastraProduto.this, ListaEditDelProdutos.class);
                startActivity(it);
            }
        });

        btSelecionaImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(cameraIntent, 12);
            }
        });


        cadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://192.168.43.232:8080/api/hamburgueria/produtos";
                JSONObject dadosEnvio = new JSONObject();
                try {

                   dadosEnvio.put("id", 0);
                    dadosEnvio.put("nome", produto.getText().toString());
                    dadosEnvio.put("descricao", descricao.getText().toString());
                    dadosEnvio.put("preco", Double.parseDouble(preco.getText().toString()));

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    fotoEscolhida.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] imagemEmByte = stream.toByteArray();
                    String imagemEmString = Base64.encodeToString(imagemEmByte, Base64.DEFAULT);
                    dadosEnvio.put("byteImagem", imagemEmString);

                }catch (JSONException jExc){
                    jExc.printStackTrace();
                }

                JsonObjectRequest config = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        dadosEnvio,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Verificar se o conteúdo retornado pelo webservice
                                //possui mesmo um objeto da classe Produto

                                    if(response.has("id")){
                                        Toast.makeText(CadastraProduto.this,
                                                "Cadastrado", Toast.LENGTH_SHORT).show();

                                    }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastraProduto.this,
                                "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
                );
                RequestQueue requisicao = Volley.newRequestQueue(CadastraProduto.this);

                requisicao.add(config);
            }
        });
}

   @Override
   protected void onActivityResult (int requestCode, int resultCode, Intent data) {
       super .onActivityResult(requestCode,resultCode , data);
       if (resultCode ==RESULT_OK &&  requestCode == 12) {
           try {
               fotoEscolhida = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
}