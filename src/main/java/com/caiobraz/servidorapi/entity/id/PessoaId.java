package com.caiobraz.servidorapi.entity.id;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PessoaId implements Serializable {

    private Long pessoa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaId)) return false;
        PessoaId that = (PessoaId) o;
        return Objects.equals(pessoa, that.pessoa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pessoa);
    }
}
