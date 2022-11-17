package com.tccsenai.loucosporlanchesapp.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.tccsenai.loucosporlanchesapp.R;

public class DadosUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_usuario);
        getSupportActionBar().hide();

        Intent dados = getIntent();
        String nome = dados.getStringExtra("nome");
        TextView edNome = findViewById(R.id.textVNomeUsuario);
        edNome.setText(nome);
        String cpf = dados.getStringExtra("cpf");
        TextView edCpf = findViewById(R.id.textVCpfUsuario);
        edCpf.setText(cpf);
        String email = dados.getStringExtra("email");
        TextView edEmail = findViewById(R.id.textVEmailUsuario);
        edEmail.setText(email);
        String telefone = dados.getStringExtra("telefone");
        TextView edTelefone = findViewById(R.id.textVTelefoneUsuario);
        edTelefone.setText(telefone);
        String dataNascimento = dados.getStringExtra("dataNascimento");
        TextView edDataNascimento = findViewById(R.id.textVDataNascUsuario);
        edDataNascimento.setText(dataNascimento);
        String endereco = dados.getStringExtra("endereco");
        TextView edEndereco = findViewById(R.id.textVEnderecoUsuario);
        edEndereco.setText(endereco);
        String numero = dados.getStringExtra("numero");
        TextView edNumero = findViewById(R.id.textVNumeroUsuario);
        edNumero.setText(numero);
        String complemento = dados.getStringExtra("complemento");
        TextView edComplemento = findViewById(R.id.textVComplementoUsuario);
        edComplemento.setText(complemento);
        String bairro = dados.getStringExtra("bairro");
        TextView edBairro = findViewById(R.id.textVBairroUsuario);
        edBairro.setText(bairro);
        String cep = dados.getStringExtra("cep");
        TextView edCep = findViewById(R.id.textVCepUsuario);
        edCep.setText(cep);
        String cidade = dados.getStringExtra("cidade");
        TextView edCidade = findViewById(R.id.textVCidadeUsuario);
        edCidade.setText(cidade);


    }
}