package com.caiobraz.servidorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.UnidadeEndereco;
import com.caiobraz.servidorapi.entity.id.UnidadeEnderecoId;

@Repository
public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEndereco, UnidadeEnderecoId> {
}
