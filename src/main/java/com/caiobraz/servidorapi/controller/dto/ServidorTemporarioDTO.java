package com.caiobraz.servidorapi.controller.dto;

import lombok.Data;

@Data
public class ServidorTemporarioDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private Integer lotacaoId;
}
