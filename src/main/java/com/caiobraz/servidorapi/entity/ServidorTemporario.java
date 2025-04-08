package com.caiobraz.servidorapi.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.caiobraz.servidorapi.entity.id.PessoaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SERVIDOR_TEMPORARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServidorTemporario {

    @EmbeddedId
    private PessoaId id;

    @MapsId("pessoa")
    @OneToOne
    @JoinColumn(name = "PES_ID")
    private Pessoa pessoa;

    @Column(name = "ST_DATA_ADMISSAO")
    private LocalDate dataAdmissao;

    @Column(name = "ST_DATA_DEMISSAO")
    private LocalDate dataDemissao;
}
