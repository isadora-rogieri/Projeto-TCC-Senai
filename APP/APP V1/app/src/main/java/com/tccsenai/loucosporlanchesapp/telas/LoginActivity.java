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

public class LoginActivity extends AppCompatActivity {

    Usuario u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        EditText edCPF = findViewById(R.id.edCPFLogin);
        EditText edSenha = findViewById(R.id.edSenhaLogin);
        Button btLogar = findViewById(R.id.btlogin);
        Button btProdutos = findViewById(R.id.btListaProdutos4);
        Button bthome = findViewById(R.id.btHome4);

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://192.168.43.232:8080/api/hamburgueria/usuarios/login" +"/"+ edCPF.getText().toString() +"/" + edSenha.getText().toString() ;

                JsonObjectRequest config = new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if(response.has("id")){
                                Toast.makeText(LoginActivity.this,
                                        "Logado", Toast.LENGTH_SHORT).show();

                                try {

                                    String nome = response.getString("nome");
                                    String cpfUsuario = response.getString("cpf");
                                    String email = response.getString("email");
                                    String telefone = response.getString("telefone");
                                    String dataNascimento = response.getString("dataNascimento");
                                    String senha = response.getString("senha");
                                    String endereco = response.getString("endereco");
                                    String numero = response.getString("numero");
                                    String complemento = response.getString("complemento");
                                    String bairro = response.getString("bairro");
                                    String cep = response.getString("cep");
                                    String cidade = response.getString("cidade");

                                    int id = response.getInt("id");
                                    u = new Usuario(id, nome, cpfUsuario, email, telefone, dataNascimento
                                    , senha, endereco, numero, complemento, bairro, cep, cidade);
                                    dadosUsuario(u);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                }
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,
                                "Erro ao logar" , Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }


                );
                RequestQueue requisicao = Volley.newRequestQueue(LoginActivity.this);

                requisicao.add(config);

            }
        });


        btProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, ListaProdutos.class);
                startActivity(it);
            }
        });

        bthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });



    }

    //enviando dados do usuario para tela de dados
    private void dadosUsuario(Usuario u) {
        Intent it = new Intent(this, DadosUsuario.class);
        it.putExtra("id", u.getId());
        it.putExtra("nome", u.getNome());
        it.putExtra("cpf", u.getCpf());
        it.putExtra("email", u.getEmail());
        it.putExtra("senha", u.getSenha());
        it.putExtra("telefone", u.getTelefone());
        it.putExtra("dataNascimento", u.getDataNascimento());
        it.putExtra("endereco", u.getEndereco());
        it.putExtra("numero", u.getNumero());
        it.putExtra("complemento", u.getComplemento());
        it.putExtra("bairro", u.getBairro());
        it.putExtra("cep", u.getCep());
        it.putExtra("cidade", u.getCidade());
        startActivity(it);
    }

}