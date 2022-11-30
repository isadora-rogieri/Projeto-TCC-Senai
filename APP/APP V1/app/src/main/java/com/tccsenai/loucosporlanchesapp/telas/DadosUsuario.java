package com.tccsenai.loucosporlanchesapp.telas;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tccsenai.loucosporlanchesapp.R;
import com.tccsenai.loucosporlanchesapp.model.Usuario;

public class DadosUsuario extends AppCompatActivity {

    int id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_usuario);
        getSupportActionBar().hide();
        Button btEditar = findViewById(R.id.btEditarDadosUsuario);

        //recuperando os dados da tela de login e de cadastro
        Intent dados = getIntent();
        id = dados.getIntExtra("id",0);
        String senha = dados.getStringExtra("senha");
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

        Usuario usuario = new Usuario(id, nome, cpf, email, telefone, dataNascimento
                , senha, endereco, numero, complemento, bairro, cep, cidade);

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             dadosUsuario(usuario);

            }
        });
    }


    //enviando os dados para tela de editar dados
    private void dadosUsuario(Usuario u) {
        Intent it = new Intent(this, EditarDadosUsuario.class);
        it.putExtra("id", u.getId());
        it.putExtra("nome", u.getNome());
        it.putExtra("cpf", u.getCpf());
        it.putExtra("senha", u.getSenha());
        it.putExtra("email", u.getEmail());
        it.putExtra("telefone", u.getTelefone());
        it.putExtra("endereco", u.getEndereco());
        it.putExtra("numero", u.getNumero());
        it.putExtra("complemento", u.getComplemento());
        it.putExtra("bairro", u.getBairro());
        it.putExtra("cep", u.getCep());
        it.putExtra("cidade", u.getCidade());

        startActivity(it);
    }

}