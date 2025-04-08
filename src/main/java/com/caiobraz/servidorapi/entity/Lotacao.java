package com.caiobraz.servidorapi.entity;

import java.time.LocalDate;
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
@Table(name = "LOTACAO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PES_ID")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "UNID_ID")
    private Unidade unidade;

    @Column(name = "LOT_DATA_LOTACAO")
    private LocalDate dataLotacao;

    @Column(name = "LOT_DATA_REMOCAO")
    private LocalDate dataRemocao;

    @Column(name = "LOT_PORTARIA")
    private String portaria;
}
