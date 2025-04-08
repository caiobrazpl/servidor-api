package com.caiobraz.servidorapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.caiobraz.servidorapi.entity.Lotacao;
import com.caiobraz.servidorapi.repository.LotacaoRepository;
import com.caiobraz.servidorapi.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LotacaoService {

    private final LotacaoRepository repository;

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Não encontrado");

    public Lotacao get(Long id) {
        return repository.findById(id).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    }

    public Page<Lotacao> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void create(Lotacao lotacao) {
        lotacao.setId(null);
        this.repository.save(lotacao);
    }

    public void update(Long id, Lotacao lotacao) {
        if (!this.repository.existsById(id)) {
            throw NOT_FOUND_EXCEPTION;
        }
        lotacao.setId(id);

        this.repository.save(lotacao);
    }

    public void delete(Long id) {
        if (!this.repository.existsById(id)) {
            throw NOT_FOUND_EXCEPTION;
        }

        this.repository.deleteById(id);
    }
}
