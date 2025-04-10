package com.caiobraz.servidorapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UNIDADE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UNID_ID")
    private Long id;

    @Column(name = "UNID_NOME")
    private String nome;

    @Column(name = "UNID_SIGLA")
    private String sigla;

    @OneToOne(mappedBy = "unidade")
    private UnidadeEndereco unidadeEndereco;

    public Unidade(Long id) {
        this.id = id;
    }
}
