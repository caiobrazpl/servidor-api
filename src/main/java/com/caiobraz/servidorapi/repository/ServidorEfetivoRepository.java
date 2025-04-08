package com.caiobraz.servidorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.ServidorEfetivo;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {
}
