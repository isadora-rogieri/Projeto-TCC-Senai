package com.tccsenai.loucosporlanchesapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tccsenai.loucosporlanchesapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TelaAtualizaProduto extends AppCompatActivity {
    int idProduto =0;
    double preco;
    Bitmap fotoEscolhida;
    ImageView imgProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atualiza_produto);
        getSupportActionBar().hide();

        Intent dados = getIntent();
        idProduto = dados.getIntExtra("id", 0);
        String nome = dados.getStringExtra("nome");
        String descricao = dados.getStringExtra("descricao");
        preco = dados.getDoubleExtra("preco",0);
        String byteImagem = dados.getStringExtra("byteImagem");

        EditText edNome = findViewById(R.id.edTextEditNome);
        EditText edDescricao = findViewById(R.id.edTextEditDescricao);
        EditText edPreco = findViewById(R.id.edTextEditPreco);
        imgProduto = findViewById(R.id.imgViewEditProduto);
        Button btSelecionarImagem = findViewById(R.id.btNovaImagem);
        Button btSalvar = findViewById(R.id.btEditarProduto);


        edNome.setText(nome);
        edDescricao.setText(descricao);
        edPreco.setText(String.valueOf(preco));
        imgProduto.setImageBitmap(converteImagem(byteImagem));


        btSelecionarImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(cameraIntent, 12);
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://192.168.43.232:8080/api/hamburgueria/produtos" +"/"+ idProduto;
                JSONObject dadosEnvio = new JSONObject();
                try {

                    dadosEnvio.put("id", idProduto);
                    dadosEnvio.put("nome", edNome.getText().toString());
                    dadosEnvio.put("descricao", edDescricao.getText().toString());
                    dadosEnvio.put("preco", Double.parseDouble(edPreco.getText().toString()));

                    if(fotoEscolhida != null) {
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        fotoEscolhida.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                        byte[] imagemEmByte = stream.toByteArray();
                        String imagemEmString = Base64.encodeToString(imagemEmByte, Base64.DEFAULT);
                        dadosEnvio.put("byteImagem", imagemEmString);
                    }else {
                        dadosEnvio.put("byteImagem", byteImagem);
                    }

                } catch (JSONException jExc) {
                    jExc.printStackTrace();
                }


                JsonObjectRequest config = new JsonObjectRequest(
                        Request.Method.PUT,
                        url,
                        dadosEnvio,
                        new Response.Listener<JSONObject>(){
                            @Override
                            public void onResponse(JSONObject response) {

                                if (response.has("id")) {
                                    Toast.makeText(TelaAtualizaProduto.this,
                                            "Atualizado", Toast.LENGTH_SHORT).show();
                                    Intent it = new Intent(TelaAtualizaProduto.this, ListaEditDelProdutos.class);
                                    startActivity(it);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TelaAtualizaProduto.this,
                                "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
                );
                RequestQueue requisicao = Volley.newRequestQueue(TelaAtualizaProduto.this);

                requisicao.add(config);
            }

        });

    }

    private Bitmap converteImagem(String imagem){
        byte[] bytes= Base64.decode(imagem,Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        // set bitmap on imageView

        return bitmap;
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super .onActivityResult(requestCode,resultCode , data);
        if (resultCode ==RESULT_OK &&  requestCode == 12) {
            try {
                //((BitmapDrawable) imgProduto.getDrawable()).getBitmap().recycle();
                fotoEscolhida = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                imgProduto.setImageBitmap(fotoEscolhida);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}