package com.caiobraz.servidorapi.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.caiobraz.servidorapi.entity.Endereco;
import com.caiobraz.servidorapi.entity.Pessoa;
import com.caiobraz.servidorapi.repository.EnderecoRepository;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository repository;

    public Endereco create(Endereco endereco) {
        return this.repository.save(endereco);
    }

    public void update(Endereco enderecoAlterado, Endereco enderecoForm) {
        enderecoAlterado.setLogradouro(enderecoForm.getLogradouro());
        enderecoAlterado.setTipoLogradouro(enderecoForm.getTipoLogradouro());
        enderecoAlterado.setNumero(enderecoForm.getNumero());
        enderecoAlterado.setCidade(enderecoForm.getCidade());
        enderecoAlterado.setBairro(enderecoForm.getBairro());

        this.repository.save(enderecoAlterado);
    }

}
