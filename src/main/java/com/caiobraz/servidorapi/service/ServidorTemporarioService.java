package com.caiobraz.servidorapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.caiobraz.servidorapi.entity.ServidorTemporario;
import com.caiobraz.servidorapi.repository.ServidorTemporarioRepository;
import com.caiobraz.servidorapi.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ServidorTemporarioService {

    private final ServidorTemporarioRepository repository;
    private final PessoaService pessoaService;

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Não encontrado");

    public ServidorTemporario get(Long id) {
        return repository.findById(id).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    }

    public Page<ServidorTemporario> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void create(ServidorTemporario servidorEfetivo) {
        servidorEfetivo.setId(null);
        this.repository.save(servidorEfetivo);
    }

    public void update(Long id, ServidorTemporario servidorEfetivo) {
        if (!this.repository.existsById(id)) {
            throw NOT_FOUND_EXCEPTION;
        }

        var pessoa = pessoaService.get(id);
        servidorEfetivo.setPessoa(pessoa);

        this.repository.save(servidorEfetivo);
    }

    public void delete(Long id) {
        if (!this.repository.existsById(id)) {
            throw NOT_FOUND_EXCEPTION;
        }

        this.repository.deleteById(id);
    }
}
