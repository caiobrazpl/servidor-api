package com.caiobraz.servidorapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.ServidorEfetivo;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {

    @Query("SELECT new ServidorEfetivo(se.id, se.matricula, se.pessoa, u)  " +
            "FROM ServidorEfetivo se " +
            "LEFT JOIN Lotacao l on l.pessoa.id = se.id " +
            "LEFT JOIN l.unidade u " +
            "WHERE (:unidadeId IS NULL OR u.id = :unidadeId) ")
    Page<ServidorEfetivo> list(Long unidadeId, Pageable pageable);
}
