package com.caiobraz.servidorapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CIDADE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CID_ID")
    private Long id;

    @Column(name = "CID_NOME")
    private String nome;

    @Column(name = "CID_UF", length = 2)
    private String uf;
}
