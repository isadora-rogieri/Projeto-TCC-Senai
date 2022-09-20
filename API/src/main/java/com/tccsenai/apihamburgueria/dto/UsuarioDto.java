package com.tccsenai.apihamburgueria.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioDto {

    protected String nome;

    protected String cpf;

    protected String email;

    protected String telefone;

    protected LocalDate dataNacimento;

    protected  String senha;

    protected  String endereco;

    protected  String numero;

    protected  String complemento;

    protected  String bairro;

    protected  String cep;

    protected  String cidade;
}
