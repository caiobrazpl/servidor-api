package com.caiobraz.servidorapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SERVIDOR_EFETIVO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorEfetivo {

    @Id
    @Column(name = "PES_ID")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "PES_ID")
    private Pessoa pessoa;

    @Column(name = "SE_MATRICULA")
    private String matricula;

    @Transient
    private Unidade unidade;

    public ServidorEfetivo(Long id, String matricula, Pessoa pessoa, Unidade unidade) {
        this.id = id;
        this.pessoa = pessoa;
        this.matricula = matricula;
        this.unidade = unidade;
    }
}
