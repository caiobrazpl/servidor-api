package com.caiobraz.servidorapi.controller.dto;

import lombok.Data;

@Data
public class LotacaoDTO {
    private Integer id;
    private String descricao;
    private Integer unidadeId;
    private String tipoLotacao;
}
