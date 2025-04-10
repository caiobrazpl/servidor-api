package com.caiobraz.servidorapi.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.caiobraz.servidorapi.entity.PessoaEndereco;
import com.caiobraz.servidorapi.repository.PessoaEnderecoRepository;

@RequiredArgsConstructor
@Service
public class PessoaEnderecoService {

    private final PessoaEnderecoRepository repository;

    public PessoaEndereco create(PessoaEndereco pessoa) {
        return this.repository.save(pessoa);
    }
}
