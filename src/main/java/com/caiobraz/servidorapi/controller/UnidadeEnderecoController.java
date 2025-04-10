package com.caiobraz.servidorapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.caiobraz.servidorapi.controller.dto.Paginacao;
import com.caiobraz.servidorapi.controller.dto.ResponseListDTO;
import com.caiobraz.servidorapi.controller.dto.UnidadeEnderecoDTO;
import com.caiobraz.servidorapi.service.UnidadeEnderecoService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/unidade-enderecos")
public class UnidadeEnderecoController {

    private final UnidadeEnderecoService unidadeEnderecoService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<ResponseListDTO<UnidadeEnderecoDTO>> findAll(
            Pageable pageable,
            @RequestParam(name = "nome_servidor", required = false) String nomeServidor) {
        var list = unidadeEnderecoService.list(pageable, nomeServidor);
        var response = list.stream().map(UnidadeEnderecoDTO::new).toList();

        return ResponseEntity.ok(new ResponseListDTO<>(response, new Paginacao(list)));
    }

}
