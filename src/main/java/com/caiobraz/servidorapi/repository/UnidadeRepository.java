package com.caiobraz.servidorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
}
