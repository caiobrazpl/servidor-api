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
@Table(name = "FOTO_PESSOA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FotoPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FP_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PES_ID")
    private Pessoa pessoa;

    @Column(name = "FP_DATA")
    private LocalDate data;

    @Column(name = "FP_BUCKET")
    private String bucket;

    @Column(name = "FP_HASH")
    private String hash;
}
