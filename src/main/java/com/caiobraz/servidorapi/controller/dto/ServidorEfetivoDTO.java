package com.caiobraz.servidorapi.controller.dto;

import lombok.Data;

@Data
public class ServidorEfetivoDTO {
    private Integer id;
    private String nome;
    private String matricula;
    private Integer lotacaoId;
}
