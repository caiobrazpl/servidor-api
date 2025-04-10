package com.caiobraz.servidorapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.UnidadeEndereco;
import com.caiobraz.servidorapi.entity.id.UnidadeEnderecoId;

@Repository
public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEndereco, UnidadeEnderecoId> {

    @Query("SELECT ue FROM UnidadeEndereco ue " +
            "INNER JOIN Lotacao l on ue.unidade = l.unidade " +
            "WHERE UPPER(l.pessoa.nome) like CONCAT('%', CONCAT(UPPER(:nomeServidor), '%')) ")
    Page<UnidadeEndereco> list(Pageable pageable, String nomeServidor);
}
