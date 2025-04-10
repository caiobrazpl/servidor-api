package com.caiobraz.servidorapi.controller.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.caiobraz.servidorapi.entity.ServidorTemporario;
import com.caiobraz.servidorapi.util.IdadeUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServidorTemporarioDTO extends PessoaDTO {

    @NotNull
    private LocalDate dataAdmissao;

    private LocalDate dataDemissao;

    public ServidorTemporarioDTO(ServidorTemporario servidorTemporario) {
        super(servidorTemporario.getPessoa());

        this.dataAdmissao = servidorTemporario.getDataAdmissao();
        this.dataDemissao = servidorTemporario.getDataDemissao();
    }

    public ServidorTemporario entity() {
        ServidorTemporario servidorTemporario = new ServidorTemporario();
        servidorTemporario.setDataDemissao(this.dataDemissao);
        servidorTemporario.setDataAdmissao(this.dataAdmissao);
        servidorTemporario.setPessoa(super.pessoa());

        return servidorTemporario;
    }
}
