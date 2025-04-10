package com.caiobraz.servidorapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.caiobraz.servidorapi.entity.ServidorEfetivo;
import com.caiobraz.servidorapi.repository.ServidorEfetivoRepository;
import com.caiobraz.servidorapi.service.exception.NotFoundException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServidorEfetivoService {

    private final ServidorEfetivoRepository repository;
    private final PessoaService pessoaService;
    private final FotoPessoaService fotoPessoaService;
    private final FotoUploadService fotoUploadService;

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Não encontrado");

    public ServidorEfetivo get(Long id) {
        return repository.findById(id).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    }

    public Page<ServidorEfetivo> list(Pageable pageable, Long unidadeId) {
        var list = repository.list(unidadeId, pageable);
        list.stream().forEach(this::carregarFoto);

        return list;
    }

    private void carregarFoto(ServidorEfetivo servidorEfetivo) {
        var fotoServidorEfetivo = servidorEfetivo.getPessoa().getFotoPessoa();
        if (fotoServidorEfetivo == null) return;
        try {
            var urlFoto = fotoUploadService.buscarFoto(fotoServidorEfetivo);
            fotoServidorEfetivo.setUrlFoto(urlFoto);
        } catch (Exception e) {
            log.error("Não foi possível carregar a foto");
            log.error(e.getMessage(), e);
        }
    }

    @Transactional
    public void create(ServidorEfetivo servidorEfetivo) {
        var pessoa = this.pessoaService.create(servidorEfetivo.getPessoa());

        servidorEfetivo.setPessoa(pessoa);

        this.repository.save(servidorEfetivo);
    }

    @Transactional
    public void update(Long id, ServidorEfetivo servidorEfetivo) {
        var servidorEfetivoAlterado = this.get(id);
        servidorEfetivoAlterado.setMatricula(servidorEfetivo.getMatricula());

        this.repository.save(servidorEfetivoAlterado);

        this.pessoaService.update(servidorEfetivoAlterado.getPessoa(), servidorEfetivo.getPessoa());
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
        ServidorEfetivo servidorEfetivo = this.get(id);

        this.fotoPessoaService.deleteFotos(servidorEfetivo.getPessoa());
        var fotoPessoa = this.fotoPessoaService.create(servidorEfetivo.getPessoa(), file);

        return this.fotoUploadService.buscarFoto(fotoPessoa);
    }
}
