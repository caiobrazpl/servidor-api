package com.caiobraz.servidorapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.caiobraz.servidorapi.entity.UnidadeEndereco;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeEnderecoDTO {

    private UnidadeDTO unidade;
    private EnderecoDTO endereco;

    public UnidadeEnderecoDTO(UnidadeEndereco unidadeEndereco) {
        this.unidade = new UnidadeDTO(unidadeEndereco.getUnidade());
        this.endereco = new EnderecoDTO(unidadeEndereco.getEndereco());
    }
}
