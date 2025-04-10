package com.caiobraz.servidorapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.caiobraz.servidorapi.entity.Unidade;
import com.caiobraz.servidorapi.repository.UnidadeRepository;
import com.caiobraz.servidorapi.service.exception.NotFoundException;

@RequiredArgsConstructor
@Service
public class UnidadeService {

    private final UnidadeRepository repository;
    private final EnderecoService enderecoService;
    private final UnidadeEnderecoService unidadeEnderecoService;

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Não encontrado");

    public Unidade get(Long id) {
        return repository.findById(id).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    }

    public Page<Unidade> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public void create(Unidade unidade) {
        var unidadeEndereco = unidade.getUnidadeEndereco();
        var endereco = this.enderecoService.create(unidadeEndereco.getEndereco());

        unidade.setUnidadeEndereco(null);
        var unidadeSave = this.repository.save(unidade);

        unidadeEndereco.setEndereco(endereco);
        unidadeEndereco.setUnidade(unidadeSave);
        this.unidadeEnderecoService.create(unidadeEndereco);
    }

    @Transactional
    public void update(Long id, Unidade unidade) {
        Unidade unidadeAlterado = this.get(id);
        unidadeAlterado.setSigla(unidade.getSigla());
        unidadeAlterado.setNome(unidade.getNome());

        this.repository.save(unidade);
        this.enderecoService.update(unidadeAlterado.getUnidadeEndereco().getEndereco(),
                unidade.getUnidadeEndereco().getEndereco());
    }

    @Transactional
    public void delete(Long id) {
        if (!this.repository.existsById(id)) {
            throw NOT_FOUND_EXCEPTION;
        }

        this.repository.deleteById(id);
    }
}
