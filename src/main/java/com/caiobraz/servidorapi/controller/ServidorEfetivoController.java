package com.caiobraz.servidorapi.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import com.caiobraz.servidorapi.controller.dto.Paginacao;
import com.caiobraz.servidorapi.controller.dto.ResponseListDTO;
import com.caiobraz.servidorapi.controller.dto.ServidorEfetivoDTO;
import com.caiobraz.servidorapi.entity.ServidorEfetivo;
import com.caiobraz.servidorapi.service.ServidorEfetivoService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servidores-efetivos")
public class ServidorEfetivoController {

    private final ServidorEfetivoService servidorEfetivoService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseListDTO<ServidorEfetivoDTO>> findAll(
            Pageable pageable,
            @RequestParam(name = "unid_id", required = false) Long unidadeId) {
        var list = servidorEfetivoService.list(pageable, unidadeId);
        var response = list.stream().map(ServidorEfetivoDTO::new).toList();

        return ResponseEntity.ok(new ResponseListDTO<>(response, new Paginacao(list)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivoDTO> findById(@PathVariable Long id) {
        var registro = this.servidorEfetivoService.get(id);
        var response = new ServidorEfetivoDTO(registro);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ServidorEfetivoDTO dto) {
        ServidorEfetivo entity = dto.entity();
        this.servidorEfetivoService.create(entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ServidorEfetivoDTO dto) {
        ServidorEfetivo entity = dto.entity();
        this.servidorEfetivoService.update(id, entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.servidorEfetivoService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/fotos")
    public ResponseEntity<String> uploadFoto(@PathVariable Long id, @RequestParam("arquivo") MultipartFile arquivo) {
        String urlTemporaria = this.servidorEfetivoService.uploadFoto(id, arquivo);

        return ResponseEntity.ok(urlTemporaria);
    }
}
