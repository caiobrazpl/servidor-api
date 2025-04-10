package com.caiobraz.servidorapi.controller.dto;

import java.util.Optional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.caiobraz.servidorapi.entity.Unidade;
import com.caiobraz.servidorapi.entity.UnidadeEndereco;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UnidadeDTO {

    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sigla;

    @Valid
    @NotNull
    private EnderecoDTO endereco;

    public UnidadeDTO(Unidade unidade) {
        this.id = unidade.getId();
        this.nome = unidade.getNome();
        this.sigla = unidade.getSigla();
        Optional.ofNullable(unidade.getUnidadeEndereco()).ifPresent(endereco -> {
            this.endereco = new EnderecoDTO(unidade.getUnidadeEndereco().getEndereco());
        });
    }

    public Unidade entity() {
        var unidade = new Unidade();
        unidade.setNome(this.nome);
        unidade.setSigla(this.sigla);

        unidade.setUnidadeEndereco(new UnidadeEndereco(unidade, this.endereco.toEntity()));

        return unidade;
    }
}
