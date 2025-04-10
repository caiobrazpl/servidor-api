package com.caiobraz.servidorapi.controller.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.caiobraz.servidorapi.entity.Lotacao;
import com.caiobraz.servidorapi.entity.Pessoa;
import com.caiobraz.servidorapi.entity.Unidade;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LotacaoDTO {

    private Long id;

    @NotNull
    private Long pessoaId;

    @NotNull
    private Long unidadeId;

    private LocalDate dataLotacao;

    private LocalDate dataRemocao;

    private String portaria;

    public LotacaoDTO(Lotacao lotacao) {
        this.id = lotacao.getId();
        this.pessoaId = lotacao.getPessoa().getId();
        this.unidadeId = lotacao.getUnidade().getId();
        this.dataLotacao = lotacao.getDataLotacao();
        this.dataRemocao = lotacao.getDataRemocao();
        this.portaria = lotacao.getPortaria();
    }

    public Lotacao entity() {
        var lotacao = new Lotacao();
        lotacao.setId(this.id);
        lotacao.setPortaria(this.portaria);
        lotacao.setDataLotacao(this.dataLotacao);
        lotacao.setDataRemocao(this.dataRemocao);
        lotacao.setPessoa(new Pessoa(this.pessoaId));
        lotacao.setUnidade(new Unidade(this.unidadeId));

        return lotacao;
    }
}
