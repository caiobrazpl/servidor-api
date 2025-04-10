package com.caiobraz.servidorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.FotoPessoa;
import com.caiobraz.servidorapi.entity.Pessoa;

@Repository
public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Long> {

    void deleteAllByPessoa(Pessoa pessoa);
}
