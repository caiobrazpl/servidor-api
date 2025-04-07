package com.caiobraz.servidorapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/testes")
public class TesteController {

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    ResponseEntity<String> teste() {
        return ResponseEntity.ok("Ok");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin")
    ResponseEntity<String> testeAdmin() {
        return ResponseEntity.ok("Ok");
    }
}
