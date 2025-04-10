package com.caiobraz.servidorapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.caiobraz.servidorapi.entity.UnidadeEndereco;
import com.caiobraz.servidorapi.repository.UnidadeEnderecoRepository;

@RequiredArgsConstructor
@Service
public class UnidadeEnderecoService {

    private final UnidadeEnderecoRepository repository;

    public UnidadeEndereco create(UnidadeEndereco unidadeEndereco) {
        return this.repository.save(unidadeEndereco);
    }

    public Page<UnidadeEndereco> list(Pageable pageable, String nomeServidor) {
        return repository.list(pageable, nomeServidor);
    }
}
