package com.caiobraz.servidorapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.caiobraz.servidorapi.entity.Unidade;
import com.caiobraz.servidorapi.repository.UnidadeRepository;
import com.caiobraz.servidorapi.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UnidadeService {

    private final UnidadeRepository repository;

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Não encontrado");

    public Unidade get(Long id) {
        return repository.findById(id).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    }

    public Page<Unidade> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void create(Unidade unidade) {
        unidade.setId(null);
        this.repository.save(unidade);
    }

    public void update(Long id, Unidade unidade) {
        if (!this.repository.existsById(id)) {
            throw NOT_FOUND_EXCEPTION;
        }
        unidade.setId(id);

        this.repository.save(unidade);
    }

    public void delete(Long id) {
        if (!this.repository.existsById(id)) {
            throw NOT_FOUND_EXCEPTION;
        }

        this.repository.deleteById(id);
    }
}
