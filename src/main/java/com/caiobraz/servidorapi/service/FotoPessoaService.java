package com.caiobraz.servidorapi.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import com.caiobraz.servidorapi.entity.FotoPessoa;
import com.caiobraz.servidorapi.entity.Pessoa;
import com.caiobraz.servidorapi.repository.FotoPessoaRepository;

@RequiredArgsConstructor
@Service
public class FotoPessoaService {

    private final FotoPessoaRepository repository;
    private final FotoUploadService fotoUploadService;

    @Value("${minio.bucket-name}")
    private String bucket;

    public FotoPessoa create(Pessoa pessoa, MultipartFile file) {
        String hash = this.fotoUploadService.enviarFoto(file);

        FotoPessoa fotoPessoa = new FotoPessoa();
        fotoPessoa.setPessoa(pessoa);
        fotoPessoa.setHash(hash);
        fotoPessoa.setData(LocalDateTime.now());
        fotoPessoa.setBucket(this.bucket);

        return repository.save(fotoPessoa);
    }

    public void deleteFotos(Pessoa pessoa) {
        this.repository.deleteAllByPessoa(pessoa);
    }
}
