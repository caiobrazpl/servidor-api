package com.caiobraz.servidorapi.controller.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Getter;

@Getter
public class ResponseListDTO<T> {

    private final List<T> dados;
    private Paginacao paginacao;

    public ResponseListDTO(List<T> dados) {
        this.dados = dados;
    }

    public ResponseListDTO(List<T> dados, Paginacao paginacao) {
        this.dados = dados;
        this.paginacao = paginacao;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
