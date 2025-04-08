package com.caiobraz.servidorapi.service;

import org.springframework.stereotype.Service;

import com.caiobraz.servidorapi.entity.Pessoa;
import com.caiobraz.servidorapi.repository.PessoaRepository;
import com.caiobraz.servidorapi.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository repository;

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Não encontrado");

    public Pessoa get(Long id) {
        return repository.findById(id).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    }

}
