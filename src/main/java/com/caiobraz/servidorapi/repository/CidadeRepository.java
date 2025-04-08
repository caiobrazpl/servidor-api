package com.caiobraz.servidorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
