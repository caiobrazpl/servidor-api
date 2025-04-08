package com.caiobraz.servidorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
