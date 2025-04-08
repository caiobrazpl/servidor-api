package com.caiobraz.servidorapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;
import com.caiobraz.servidorapi.controller.dto.Paginacao;
import com.caiobraz.servidorapi.controller.dto.ResponseListDTO;
import com.caiobraz.servidorapi.controller.dto.UnidadeDTO;
import com.caiobraz.servidorapi.entity.Unidade;
import com.caiobraz.servidorapi.service.UnidadeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseListDTO<UnidadeDTO>> findAll(Pageable pageable) {
        var list = this.unidadeService.list(pageable);
        var response = list.stream().map( x-> modelMapper.map(x, UnidadeDTO.class)).toList();

        return ResponseEntity.ok(new ResponseListDTO<>(response, new Paginacao(list)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDTO> findById(@PathVariable Long id) {
        var registro = this.unidadeService.get(id);
        var response = this.modelMapper.map(registro, UnidadeDTO.class);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UnidadeDTO dto) {
        Unidade entity = this.modelMapper.map(dto, Unidade.class);
        this.unidadeService.create(entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UnidadeDTO dto) {
        Unidade entity = this.modelMapper.map(dto, Unidade.class);
        this.unidadeService.update(id, entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.unidadeService.delete(id);

        return ResponseEntity.ok().build();
    }
}
