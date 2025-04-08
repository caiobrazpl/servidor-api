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
import com.caiobraz.servidorapi.controller.dto.ServidorTemporarioDTO;
import com.caiobraz.servidorapi.entity.ServidorTemporario;
import com.caiobraz.servidorapi.service.ServidorTemporarioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servidores-temporarios")
public class ServidorTemporarioController {

    private final ServidorTemporarioService servidorTemporarioService;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseListDTO<ServidorTemporarioDTO>> findAll(Pageable pageable) {
        var list = this.servidorTemporarioService.list(pageable);
        var response = list.stream().map( x-> modelMapper.map(x, ServidorTemporarioDTO.class)).toList();

        return ResponseEntity.ok(new ResponseListDTO<>(response, new Paginacao(list)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ServidorTemporarioDTO> findById(@PathVariable Long id) {
        var registro = this.servidorTemporarioService.get(id);
        var response = this.modelMapper.map(registro, ServidorTemporarioDTO.class);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ServidorTemporarioDTO dto) {
        ServidorTemporario entity = this.modelMapper.map(dto, ServidorTemporario.class);
        this.servidorTemporarioService.create(entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ServidorTemporarioDTO dto) {
        ServidorTemporario entity = this.modelMapper.map(dto, ServidorTemporario.class);
        this.servidorTemporarioService.update(id, entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.servidorTemporarioService.delete(id);

        return ResponseEntity.ok().build();
    }
}
