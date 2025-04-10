package com.caiobraz.servidorapi.service;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.lang3.StringUtils;
import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.caiobraz.servidorapi.entity.FotoPessoa;
import com.caiobraz.servidorapi.service.exception.BusinessException;
import com.caiobraz.servidorapi.service.exception.NotFoundException;

@Slf4j
@RequiredArgsConstructor
@Service
public class FotoUploadService {

    @Value("${minio.bucket-name}")
    private String bucket;

    private final MinioClient minioClient;

    private static final String PASTA_FOTOS = "fotos/";
    private static final String CONTENT_TYPE_FOTOS = "image/jpeg";
    private static final String TYPE_FOTOS = ".jpeg";

    public String enviarFoto(MultipartFile arquivo) {
        try {
            this.criarBucketSeNaoExiste(this.bucket);
            String hash = UUID.randomUUID().toString().toUpperCase();

            PutObjectArgs data = PutObjectArgs.builder()
                    .bucket(this.bucket)
                    .object(PASTA_FOTOS + hash.concat(TYPE_FOTOS))
                    .contentType(CONTENT_TYPE_FOTOS)
                    .stream(arquivo.getInputStream(), arquivo.getSize(), -1)
                    .build();

            ObjectWriteResponse response = this.minioClient.putObject(data);
            return hash;
        } catch (Exception e) {
            log.error("Erro ao enviar Foto", e);
            return null;
        }
    }

    public void deletarArquivo(FotoPessoa fotoPessoa) {
        if (StringUtils.isEmpty(fotoPessoa.getHash())) {
            throw new NotFoundException("erro.naoEncontrado");
        }

        try {
            RemoveObjectArgs removeObjects = RemoveObjectArgs.builder()
                    .bucket(this.bucket)
                    .object(PASTA_FOTOS + fotoPessoa.getHash().concat(TYPE_FOTOS))
                    .build();
            minioClient.removeObject(removeObjects);
        } catch (Exception e) {
            log.error("Erro ao deletar Foto", e);
        }
    }

    public String buscarFoto(FotoPessoa fotoPessoa) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(fotoPessoa.getBucket())
                            .object(PASTA_FOTOS + fotoPessoa.getHash().concat(TYPE_FOTOS))
                            .expiry(5, TimeUnit.MINUTES)
                            .build()
            );

        } catch (Exception e) {
            log.error("Erro ao buscar arquivo", e);
            throw new NotFoundException(fotoPessoa.getHash());
        }
    }

    private void criarBucketSeNaoExiste(String bucket) {
        try {
            boolean found = this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());

            if (!found) {
                this.minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }
        } catch (Exception ex) {
            log.error("Impossível criar bucket com nome {}", bucket);

            throw new BusinessException("Serviço do minio indisponível no momento.");
        }
    }
}
