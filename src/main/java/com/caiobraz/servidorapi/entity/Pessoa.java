package com.caiobraz.servidorapi.entity;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PESSOA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PES_ID")
    private Long id;

    @Column(name = "PES_NOME")
    private String nome;

    @Column(name = "PES_DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "PES_SEXO")
    private String sexo;

    @Column(name = "PES_MAE")
    private String mae;

    @Column(name = "PES_PAI")
    private String pai;

    @OneToOne(mappedBy = "pessoa")
    private PessoaEndereco pessoaEndereco;

    @OneToOne(mappedBy = "pessoa")
    private FotoPessoa fotoPessoa;

    public Pessoa(Long id) {
        this.id = id;
    }
}
