package com.caiobraz.servidorapi.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import com.caiobraz.servidorapi.entity.ServidorTemporario;
import com.caiobraz.servidorapi.repository.ServidorTemporarioRepository;
import com.caiobraz.servidorapi.service.exception.NotFoundException;

@RequiredArgsConstructor
@Service
public class ServidorTemporarioService {

    private final ServidorTemporarioRepository repository;
    private final PessoaService pessoaService;
    private final FotoPessoaService fotoPessoaService;
    private final FotoUploadService fotoUploadService;

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Não encontrado");

    public ServidorTemporario get(Long id) {
        return this.repository.findById(id).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    }

    public Page<ServidorTemporario> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public void create(ServidorTemporario servidorTemporario) {
        var pessoa = this.pessoaService.create(servidorTemporario.getPessoa());

        servidorTemporario.setPessoa(pessoa);

        this.repository.save(servidorTemporario);
    }

    @Transactional
    public void update(Long id, ServidorTemporario servidorTemporario) {
        var servidorEfetivoAlterado = this.get(id);
        servidorEfetivoAlterado.setDataAdmissao(servidorTemporario.getDataAdmissao());
        servidorEfetivoAlterado.setDataDemissao(servidorTemporario.getDataDemissao());

        this.repository.save(servidorEfetivoAlterado);

        this.pessoaService.update(servidorEfetivoAlterado.getPessoa(), servidorTemporario.getPessoa());
    }

    @Transactional
    public void delete(Long id) {
        if (!this.repository.existsById(id)) {
            throw NOT_FOUND_EXCEPTION;
        }

        this.repository.deleteById(id);
    }

    @Transactional
    public String uploadFoto(Long id, MultipartFile file) {
        ServidorTemporario servidorTemporario = this.get(id);

        this.fotoPessoaService.deleteFotos(servidorTemporario.getPessoa());
        var fotoPessoa = this.fotoPessoaService.create(servidorTemporario.getPessoa(), file);

        return fotoUploadService.buscarFoto(fotoPessoa);
    }
}
