package com.caiobraz.servidorapi.entity.id;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UnidadeEnderecoId implements Serializable {

    private Long unidade;
    private Long endereco;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnidadeEnderecoId)) return false;
        UnidadeEnderecoId that = (UnidadeEnderecoId) o;
        return Objects.equals(unidade, that.unidade) &&
               Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unidade, endereco);
    }
}
