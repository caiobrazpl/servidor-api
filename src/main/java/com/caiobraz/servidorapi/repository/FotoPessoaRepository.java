package com.caiobraz.servidorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.FotoPessoa;

@Repository
public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Long> {
}
