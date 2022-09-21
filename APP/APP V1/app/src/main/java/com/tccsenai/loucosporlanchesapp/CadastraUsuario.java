package com.tccsenai.loucosporlanchesapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tccsenai.loucosporlanchesapp.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CadastraUsuario extends AppCompatActivity {
    RequestQueue requisicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_usuario);

        EditText nome = findViewById(R.id.edTNome);
        EditText email = findViewById(R.id.edTEmail);
        EditText cpf = findViewById(R.id.edTCPF);
        EditText telefone = findViewById(R.id.edTTelefone);
        EditText dataNascimento = findViewById(R.id.edTData);
        EditText endereco = findViewById(R.id.edTEndereco);
        EditText numero = findViewById(R.id.edTNumero);
        EditText complemento = findViewById(R.id.edTComplemento);
        EditText bairro = findViewById(R.id.edTBairro);
        EditText cidade = findViewById(R.id.edTCidade);
        EditText cep = findViewById(R.id.edTCEP);
        EditText senha = findViewById(R.id.edTSenha);

        Button btCadastrar = findViewById(R.id.btCadastrar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requisicao = Volley.newRequestQueue(CadastraUsuario.this);
                String url = "http://000.000.000.00:8080/api/hamburgueria/usuarios";

                StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                        response -> Toast.makeText(CadastraUsuario.this, "Sucesso!", Toast.LENGTH_LONG).show(),
                        error -> Toast.makeText(CadastraUsuario.this, "Erro!", Toast.LENGTH_LONG).show()){
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros = new HashMap<>();
                        parametros.put("nome", nome.getText().toString());
                        parametros.put("dataNascimento", dataNascimento.getText().toString());
                        parametros.put("email", email.getText().toString());
                        parametros.put("cpf", cpf.getText().toString());
                        parametros.put("telefone", telefone.getText().toString());
                        parametros.put("endereco", endereco.getText().toString());
                        parametros.put("numero", numero.getText().toString());
                        parametros.put("complemento", complemento.getText().toString());
                        parametros.put("bairro", bairro.getText().toString());
                        parametros.put("cep", cep.getText().toString());
                        parametros.put("cidade", cidade.getText().toString());
                        parametros.put("senha", senha.getText().toString());

                        return  parametros;

                    }

                };

                requisicao = Volley.newRequestQueue(CadastraUsuario.this);
                requisicao.add(stringRequest);


            }
        });

       
    }
}