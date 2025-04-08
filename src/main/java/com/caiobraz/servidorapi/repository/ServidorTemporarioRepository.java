package com.caiobraz.servidorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.ServidorTemporario;

@Repository
public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, Long> {
}
