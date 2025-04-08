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
import com.caiobraz.servidorapi.controller.dto.LotacaoDTO;
import com.caiobraz.servidorapi.controller.dto.Paginacao;
import com.caiobraz.servidorapi.controller.dto.ResponseListDTO;
import com.caiobraz.servidorapi.entity.Lotacao;
import com.caiobraz.servidorapi.service.LotacaoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lotacoes")
public class LotacaoController {

    private final LotacaoService lotacaoService;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseListDTO<LotacaoDTO>> findAll(Pageable pageable) {
        var list = lotacaoService.list(pageable);
        var response = list.stream().map( x-> modelMapper.map(x, LotacaoDTO.class)).toList();

        return ResponseEntity.ok(new ResponseListDTO<>(response, new Paginacao(list)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<LotacaoDTO> findById(@PathVariable Long id) {
        var registro = this.lotacaoService.get(id);
        var response = this.modelMapper.map(registro, LotacaoDTO.class);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody LotacaoDTO dto) {
        Lotacao entity = this.modelMapper.map(dto, Lotacao.class);
        this.lotacaoService.create(entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody LotacaoDTO dto) {
        Lotacao entity = this.modelMapper.map(dto, Lotacao.class);
        this.lotacaoService.update(id, entity);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.lotacaoService.delete(id);

        return ResponseEntity.ok().build();
    }
}
