package com.caiobraz.servidorapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.caiobraz.servidorapi.entity.id.PessoaEnderecoId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PESSOA_ENDERECO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PessoaEnderecoId.class)
public class PessoaEndereco {

    @Id
    @ManyToOne
    @JoinColumn(name = "PES_ID")
    private Pessoa pessoa;

    @Id
    @ManyToOne
    @JoinColumn(name = "END_ID")
    private Endereco endereco;
}
