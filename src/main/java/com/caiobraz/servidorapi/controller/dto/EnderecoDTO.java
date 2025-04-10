package com.caiobraz.servidorapi.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.checkerframework.checker.units.qual.A;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.caiobraz.servidorapi.entity.Cidade;
import com.caiobraz.servidorapi.entity.Endereco;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoDTO {

    private Long id;

    @NotEmpty
    private String tipoLogradouro;

    @NotEmpty
    private String logradouro;

    private Integer numero;

    private String bairro;

    @NotNull
    private Long cidadeId;

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.tipoLogradouro = endereco.getTipoLogradouro();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cidadeId = endereco.getCidade().getId();
    }

    public Endereco toEntity() {
        var endereco = new Endereco();
        endereco.setLogradouro(this.logradouro);
        endereco.setTipoLogradouro(this.tipoLogradouro);
        endereco.setNumero(this.numero);
        endereco.setBairro(this.bairro);
        endereco.setCidade(new Cidade(this.cidadeId));

        return endereco;
    }
}
