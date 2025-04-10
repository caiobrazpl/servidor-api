package com.caiobraz.servidorapi.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.caiobraz.servidorapi.entity.Pessoa;
import com.caiobraz.servidorapi.repository.PessoaRepository;
import com.caiobraz.servidorapi.service.exception.NotFoundException;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository repository;
    private final PessoaEnderecoService pessoaEnderecoService;
    private final EnderecoService enderecoService;

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("Não encontrado");

    public Pessoa get(Long id) {
        return repository.findById(id).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    }

    public Pessoa create(Pessoa pessoa) {
        var pessoaEndereco = pessoa.getPessoaEndereco();
        var endereco = this.enderecoService.create(pessoaEndereco.getEndereco());
        var pessoaSave = this.repository.save(pessoa);

        pessoaEndereco.setEndereco(endereco);
        pessoaEndereco.setPessoa(pessoaSave);
        this.pessoaEnderecoService.create(pessoaEndereco);
        pessoaSave.setPessoaEndereco(null);

        return pessoaSave;
    }

    public void update(Pessoa pessoaAlterado, Pessoa pessoaForm) {
        pessoaAlterado.setPai(pessoaForm.getPai());
        pessoaAlterado.setNome(pessoaForm.getNome());
        pessoaAlterado.setMae(pessoaForm.getMae());
        pessoaAlterado.setDataNascimento(pessoaForm.getDataNascimento());
        pessoaAlterado.setSexo(pessoaForm.getSexo());

        this.repository.save(pessoaAlterado);
        this.enderecoService.update(pessoaAlterado.getPessoaEndereco().getEndereco(),
                pessoaForm.getPessoaEndereco().getEndereco());
    }
}
