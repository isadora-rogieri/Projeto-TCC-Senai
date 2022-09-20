package com.tccsenai.apihamburgueria.model;

import com.tccsenai.apihamburgueria.enums.TipoUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Usuario implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Nome obrigatório")
    @Size(min = 5, max = 100)
    protected String nome;
    @Column(nullable = false)
    @NotBlank(message = "CPF obrigatório")
    protected String cpf;
    @Column(nullable = false)
    @NotBlank(message = "Email obrigatório")
    protected String email;
    @Column(nullable = false)
    @NotBlank(message = "Telefone obrigatório")
    protected String telefone;
    @Column(nullable = false)
    @NotNull(message = "Data nascimento obrigatório")
    protected LocalDate dataNacimento;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected TipoUsuario tipoUsuario;
    @Column(nullable = false)
    @NotBlank(message = "Senha obrigatório")
    protected  String senha;
    @Column(nullable = false)
    @NotBlank(message = "Endereço obrigatório")
    protected  String endereco;
    @Column(nullable = false)
    @NotBlank(message = "Número obrigatório")
    protected  String numero;
    @Column
    protected  String complemento;
    @Column(nullable = false)
    @NotBlank(message = "Bairro obrigatório")
    protected  String bairro;
    @Column(nullable = false)
    @NotBlank(message = "CEP obrigatório")
    protected  String cep;
    @Column(nullable = false)
    @NotBlank(message = "Cidade obrigatório")
    protected  String cidade;


}
