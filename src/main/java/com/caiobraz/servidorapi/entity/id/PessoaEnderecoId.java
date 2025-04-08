package com.caiobraz.servidorapi.entity.id;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PessoaEnderecoId implements Serializable {

    private Long pessoa;
    private Long endereco;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaEnderecoId)) return false;
        PessoaEnderecoId that = (PessoaEnderecoId) o;
        return Objects.equals(pessoa, that.pessoa) &&
               Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pessoa, endereco);
    }
}
