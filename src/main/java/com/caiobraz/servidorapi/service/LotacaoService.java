package com.caiobraz.servidorapi.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.caiobraz.servidorapi.entity.Lotacao;
import com.caiobraz.servidorapi.repository.LotacaoRepository;
import com.caiobraz.servidorapi.service.exception.NotFoundException;

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

    @Transactional
    public void create(Lotacao lotacao) {
        lotacao.setDataLotacao(LocalDate.now());
        this.repository.save(lotacao);
    }

    @Transactional
    public void update(Long id, Lotacao lotacao) {
        var lotacaoAlterado = this.get(id);
        lotacaoAlterado.setPessoa(lotacao.getPessoa());
        lotacaoAlterado.setUnidade(lotacao.getUnidade());
        lotacaoAlterado.setDataLotacao(lotacao.getDataLotacao());
        lotacaoAlterado.setDataRemocao(lotacao.getDataRemocao());
        lotacaoAlterado.setPortaria(lotacao.getPortaria());

        this.repository.save(lotacao);
    }

    @Transactional
    public void delete(Long id) {
        if (!this.repository.existsById(id)) {
            throw NOT_FOUND_EXCEPTION;
        }

        this.repository.deleteById(id);
    }
}
