package com.caiobraz.servidorapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.caiobraz.servidorapi.entity.id.PessoaEnderecoId;

@Entity
@Table(name = "PESSOA_ENDERECO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PessoaEnderecoId.class)
public class PessoaEndereco {

    @Id
    @OneToOne
    @JoinColumn(name = "PES_ID")
    private Pessoa pessoa;

    @Id
    @ManyToOne
    @JoinColumn(name = "END_ID")
    private Endereco endereco;
}
