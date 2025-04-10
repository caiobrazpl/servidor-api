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
import com.caiobraz.servidorapi.controller.dto.ServidorTemporarioDTO;
import com.caiobraz.servidorapi.entity.ServidorTemporario;
import com.caiobraz.servidorapi.service.ServidorTemporarioService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servidores-temporarios")
public class ServidorTemporarioController {

    private final ServidorTemporarioService servidorTemporarioService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseListDTO<ServidorTemporarioDTO>> findAll(
            Pageable pageable) {

        var list = this.servidorTemporarioService.list(pageable);
        var response = list.stream().map(ServidorTemporarioDTO::new).toList();

        return ResponseEntity.ok(new ResponseListDTO<>(response, new Paginacao(list)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ServidorTemporarioDTO> findById(@PathVariable Long id) {
        var registro = this.servidorTemporarioService.get(id);
        var response = new ServidorTemporarioDTO(registro);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ServidorTemporarioDTO dto) {
        ServidorTemporario entity = dto.entity();
        this.servidorTemporarioService.create(entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ServidorTemporarioDTO dto) {
        ServidorTemporario entity = dto.entity();
        this.servidorTemporarioService.update(id, entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.servidorTemporarioService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/fotos")
    public ResponseEntity<String> uploadFoto(@PathVariable Long id, @RequestParam("arquivo") MultipartFile arquivo) {
        String urlTemporaria = this.servidorTemporarioService.uploadFoto(id, arquivo);

        return ResponseEntity.ok(urlTemporaria);
    }
}
