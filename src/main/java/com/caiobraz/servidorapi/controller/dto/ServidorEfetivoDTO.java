package com.caiobraz.servidorapi.controller.dto;

import java.util.Optional;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.caiobraz.servidorapi.entity.ServidorEfetivo;
import com.caiobraz.servidorapi.util.IdadeUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServidorEfetivoDTO extends PessoaDTO {

    @NotEmpty
    private String matricula;

    private Integer idade;
    private String foto;
    private UnidadeDTO unidadeLotacao;

    public ServidorEfetivoDTO(ServidorEfetivo servidorEfetivo) {
        super(servidorEfetivo.getPessoa());
        this.matricula = servidorEfetivo.getMatricula();
        this.idade = IdadeUtils.calcularIdade(servidorEfetivo.getPessoa().getDataNascimento());
        this.foto = servidorEfetivo.getPessoa().getFotoPessoa().getUrlFoto();

        Optional.ofNullable(servidorEfetivo.getPessoa().getFotoPessoa()).ifPresent(f -> this.foto = f.getUrlFoto());

        Optional.ofNullable(servidorEfetivo.getUnidade()).ifPresent(unidadeLotacao ->
                this.unidadeLotacao = new UnidadeDTO(unidadeLotacao)
        );
    }

    public ServidorEfetivo entity() {
        var servidorEfetivo = new ServidorEfetivo();
        servidorEfetivo.setMatricula(this.matricula);
        servidorEfetivo.setPessoa(super.pessoa());

        return servidorEfetivo;
    }
}
