package com.caiobraz.servidorapi.controller.dto;

import java.time.LocalDate;
import java.util.Optional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.caiobraz.servidorapi.entity.Pessoa;
import com.caiobraz.servidorapi.entity.PessoaEndereco;

@Data
@NoArgsConstructor
public class PessoaDTO {

    private Long id;

    @NotEmpty
    private String nome;

    private String sexo;

    private String mae;

    private String pai;

    private LocalDate dataNascimento;

    @Valid
    @NotNull
    private EnderecoDTO endereco;

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.sexo = pessoa.getSexo();
        this.mae = pessoa.getMae();
        this.pai = pessoa.getPai();
        this.dataNascimento = pessoa.getDataNascimento();
        Optional.ofNullable(pessoa.getPessoaEndereco()).ifPresent(endereco ->
                this.endereco = new EnderecoDTO(endereco.getEndereco())
        );
    }

    public Pessoa pessoa() {
        var pessoa = new Pessoa();
        pessoa.setNome(this.nome);
        pessoa.setSexo(this.sexo);
        pessoa.setMae(this.mae);
        pessoa.setPai(this.pai);
        pessoa.setDataNascimento(this.dataNascimento);
        pessoa.setPessoaEndereco(new PessoaEndereco(pessoa, this.endereco.toEntity()));

        return pessoa;
    }
}
