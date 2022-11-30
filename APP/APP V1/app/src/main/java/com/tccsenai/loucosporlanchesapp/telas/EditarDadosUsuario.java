package com.tccsenai.loucosporlanchesapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.tccsenai.loucosporlanchesapp.R;
import com.tccsenai.loucosporlanchesapp.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

public class EditarDadosUsuario extends AppCompatActivity {
    int id =0;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dados_usuario);
        getSupportActionBar().hide();

        Button btSalvar = findViewById(R.id.btEditarDadosUser);

        Intent dados = getIntent();
        id = dados.getIntExtra("id",0);
        String nome = dados.getStringExtra("nome");
        String email = dados.getStringExtra("email");
        String telefone = dados.getStringExtra("telefone");
        String endereco = dados.getStringExtra("endereco");
        String numero = dados.getStringExtra("numero");
        String bairro = dados.getStringExtra("bairro");
        String cep = dados.getStringExtra("cep");
        String cidade = dados.getStringExtra("cidade");
        String senha = dados.getStringExtra("senha");
        String cpf = dados.getStringExtra("cpf");
        String dataNascimento = dados.getStringExtra("dataNascimento");
        String complemento = dados.getStringExtra("complemento");


        EditText edNome = findViewById(R.id.edTextEditNomeUser);
        EditText edEmail = findViewById(R.id.edTextEditEmailUser);
        EditText edTelefone= findViewById(R.id.edTextEditTelefoneUser);
        EditText edEndereco = findViewById(R.id.edTextEditEnderecoUser);
        EditText edNumero = findViewById(R.id.edTextEditNumeroUser);
        EditText edBairro = findViewById(R.id.edTextEditBairroUser);
        EditText edCep = findViewById(R.id.edTextEditCepUser);
        EditText edCidade = findViewById(R.id.edTextEditCidadeUser);
        EditText edComplemento = findViewById(R.id.edTextEditComplementoUser);

         edNome.setText(nome);
         edEmail.setText(email);
         edTelefone.setText(telefone);
         edEndereco.setText(endereco);
         edNumero.setText(numero);
         edBairro.setText(bairro);
         edCep.setText(cep);
         edCidade.setText(cidade);
         edComplemento.setText(complemento);

         btSalvar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String url = "http://192.168.43.232:8080/api/hamburgueria/usuarios" +"/"+ id;
                 JSONObject dadosEnvio = new JSONObject();
                 try {

                     dadosEnvio.put("id", id);
                     dadosEnvio.put("nome", edNome.getText().toString());
                     dadosEnvio.put("email", edEmail.getText().toString());
                     dadosEnvio.put("telefone", edTelefone.getText().toString());
                     dadosEnvio.put("endereco", edEndereco.getText().toString());
                     dadosEnvio.put("numero", edNumero.getText().toString());
                     dadosEnvio.put("bairro", edBairro.getText().toString());
                     dadosEnvio.put("cep", edCep.getText().toString());
                     dadosEnvio.put("cidade", edCidade.getText().toString());
                     dadosEnvio.put("complemento", edComplemento.getText().toString());
                     dadosEnvio.put("dataNascimento", dataNascimento);

                     usuario = new Usuario(id, nome, cpf, email, telefone, dataNascimento
                             , senha, endereco, numero, complemento, bairro, cep, cidade);


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
                                     Toast.makeText(EditarDadosUsuario.this,
                                             "Atualizando dados, confirme dados de login", Toast.LENGTH_LONG).show();
                                     Intent it = new Intent(EditarDadosUsuario.this, LoginActivity.class);
                                    startActivity(it);
                                 }
                             }

                         }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Toast.makeText(EditarDadosUsuario.this,
                                 "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                         error.printStackTrace();
                     }
                 }
                 );
                 RequestQueue requisicao = Volley.newRequestQueue(EditarDadosUsuario.this);

                 requisicao.add(config);
             }
         });


         }

    }