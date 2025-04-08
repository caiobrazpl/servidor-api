package com.caiobraz.servidorapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ENDERECO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "END_ID")
    private Long id;

    @Column(name = "END_TIPO_LOGRADOURO")
    private String tipoLogradouro;

    @Column(name = "END_LOGRADOURO")
    private String logradouro;

    @Column(name = "END_NUMERO")
    private Integer numero;

    @Column(name = "END_BAIRRO")
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "CID_ID")
    private Cidade cidade;
}
