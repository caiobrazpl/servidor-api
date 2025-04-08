package com.caiobraz.servidorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.PessoaEndereco;
import com.caiobraz.servidorapi.entity.id.PessoaEnderecoId;

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, PessoaEnderecoId> {
}
