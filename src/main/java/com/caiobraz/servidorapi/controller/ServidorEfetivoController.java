package com.caiobraz.servidorapi.controller;

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
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;
import com.caiobraz.servidorapi.controller.dto.Paginacao;
import com.caiobraz.servidorapi.controller.dto.ResponseListDTO;
import com.caiobraz.servidorapi.controller.dto.ServidorEfetivoDTO;
import com.caiobraz.servidorapi.entity.ServidorEfetivo;
import com.caiobraz.servidorapi.service.ServidorEfetivoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servidores-efetivos")
public class ServidorEfetivoController {

    private final ServidorEfetivoService servidorEfetivoService;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseListDTO<ServidorEfetivoDTO>> findAll(Pageable pageable) {
        var list = servidorEfetivoService.list(pageable);
        var response = list.stream().map( x-> modelMapper.map(x, ServidorEfetivoDTO.class)).toList();

        return ResponseEntity.ok(new ResponseListDTO<>(response, new Paginacao(list)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivoDTO> findById(@PathVariable Long id) {
        var registro = this.servidorEfetivoService.get(id);
        var response = this.modelMapper.map(registro, ServidorEfetivoDTO.class);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ServidorEfetivoDTO dto) {
        ServidorEfetivo entity = this.modelMapper.map(dto, ServidorEfetivo.class);
        this.servidorEfetivoService.create(entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ServidorEfetivoDTO dto) {
        ServidorEfetivo entity = this.modelMapper.map(dto, ServidorEfetivo.class);
        this.servidorEfetivoService.update(id, entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.servidorEfetivoService.delete(id);

        return ResponseEntity.ok().build();
    }
}
