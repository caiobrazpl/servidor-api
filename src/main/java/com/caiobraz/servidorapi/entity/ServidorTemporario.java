package com.caiobraz.servidorapi.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SERVIDOR_TEMPORARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorTemporario {

    @Id
    @Column(name = "PES_ID")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "PES_ID")
    private Pessoa pessoa;

    @Column(name = "ST_DATA_ADMISSAO")
    private LocalDate dataAdmissao;

    @Column(name = "ST_DATA_DEMISSAO")
    private LocalDate dataDemissao;
}
