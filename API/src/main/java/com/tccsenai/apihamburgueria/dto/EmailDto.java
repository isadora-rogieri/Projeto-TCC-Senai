package com.tccsenai.apihamburgueria.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class EmailDto {

    private String para ;
    private String assunto;
    private String mensagem;
}
