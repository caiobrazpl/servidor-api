package com.caiobraz.servidorapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.caiobraz.servidorapi.entity.id.UnidadeEnderecoId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UNIDADE_ENDERECO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UnidadeEnderecoId.class)
public class UnidadeEndereco {

    @Id
    @ManyToOne
    @JoinColumn(name = "UNID_ID")
    private Unidade unidade;

    @Id
    @ManyToOne
    @JoinColumn(name = "END_ID")
    private Endereco endereco;
}
