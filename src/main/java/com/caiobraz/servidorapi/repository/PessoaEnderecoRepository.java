package com.caiobraz.servidorapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caiobraz.servidorapi.entity.PessoaEndereco;
import com.caiobraz.servidorapi.entity.id.PessoaEnderecoId;

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, PessoaEnderecoId> {

    Optional<PessoaEndereco> findByPessoa_Id(Long idPessoa);

}
