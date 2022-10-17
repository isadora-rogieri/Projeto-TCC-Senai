package com.tccsenai.loucosporlanchesapp.telas;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tccsenai.loucosporlanchesapp.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class CadastraUsuario extends AppCompatActivity {

    RequestQueue requisicao;

    EditText nome , email , cpf , telefone , dataNascimento , endereco , numero , complemento , bairro , cidade , cep , senha;
    Button btCadastrar;
    Calendar dataNascimentoC;
    Calendar calendario = Calendar.getInstance();
    int ano = calendario.get(Calendar.YEAR);
    int mes = calendario.get(Calendar.MONTH);
    int dia = calendario.get(Calendar.DAY_OF_MONTH);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_usuario);
        getSupportActionBar().hide();

        InicicarComponentes();


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nome.getText().toString().isEmpty() || email.getText().toString().isEmpty() || cpf.getText().toString().isEmpty()
                        || telefone.getText().toString().isEmpty() || dataNascimento.getText().toString().isEmpty() || endereco.getText().toString().isEmpty()
                        || numero.getText().toString().isEmpty() || complemento.getText().toString().isEmpty() || bairro.getText().toString().isEmpty()
                        || cidade.getText().toString().isEmpty() || cep.getText().toString().isEmpty() || senha.getText().toString().isEmpty()) {
                    Toast.makeText(CadastraUsuario.this, "PREENCHA TODOS OS CAMPOS ", Toast.LENGTH_LONG).show();
                }


                requisicao = Volley.newRequestQueue(CadastraUsuario.this);
                String url = "http://192.168.0.14:8080/api/hamburgueria/usuarios";


                StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                        response -> Toast.makeText(CadastraUsuario.this, "Sucesso!", Toast.LENGTH_LONG).show(),
                        error -> Toast.makeText(CadastraUsuario.this, "Erro!", Toast.LENGTH_LONG).show()){
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() {
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

    public void InicicarComponentes(){
        nome = findViewById(R.id.edTNome);
        email = findViewById(R.id.edTEmail);
        cpf = findViewById(R.id.edTCPF);
        telefone = findViewById(R.id.edTTelefone);
        dataNascimento = findViewById(R.id.edTData);
        dataNascimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    //Inserir codigo do calendario
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            CadastraUsuario.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    //Quando a data for alterada para alguma outra além da atual,
                                    //o código abaixo será executado

                                    //Atribuir a data selecionada no calendário para o objeto "dataNascimento"
                                    dataNascimentoC = new GregorianCalendar(year, month, dayOfMonth);

                                    //Verificar se a data escolhida não é uma data maior do que o dia atual
                                    if (dataNascimentoC.getTimeInMillis() > calendario.getTimeInMillis()) {
                                        Toast.makeText(CadastraUsuario.this, "Data de nascimento inválida", Toast.LENGTH_SHORT).show();
                                        dataNascimento.setText("");
                                    } else {
                                        //Escrever a data no EditText
                                        if(month<10) {
                                            if (dayOfMonth < 10) {
                                                dataNascimento.setText("0" + dayOfMonth + "/0" + month + "/" + year);
                                            } else {
                                                dataNascimento.setText(dayOfMonth + "/0" + month + "/" + year);
                                            }
                                        } else {
                                            dataNascimento.setText(dayOfMonth + "/" + month + "/" + year);
                                        }

                                    }
                                }
                            },
                            ano, mes, dia);
                    //Exibe o calendário
                    datePickerDialog.show();

                }

            }

        });

        endereco = findViewById(R.id.edTEndereco);
        numero = findViewById(R.id.edTNumero);
        complemento = findViewById(R.id.edTComplemento);
        bairro = findViewById(R.id.edTBairro);
        cidade = findViewById(R.id.edTCidade);
        cep = findViewById(R.id.edTCEP);
        senha = findViewById(R.id.edTSenha);
        btCadastrar = findViewById(R.id.btCadastrar);
    }
}