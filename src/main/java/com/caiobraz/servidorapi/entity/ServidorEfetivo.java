package com.caiobraz.servidorapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.caiobraz.servidorapi.entity.id.PessoaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SERVIDOR_EFETIVO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorEfetivo {

    @EmbeddedId
    private PessoaId id;

    @MapsId("pessoa")
    @OneToOne
    @JoinColumn(name = "PES_ID")
    private Pessoa pessoa;

    @Column(name = "SE_MATRICULA")
    private String matricula;
}
