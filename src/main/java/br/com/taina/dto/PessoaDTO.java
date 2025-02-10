package br.com.taina.dto;

import br.com.taina.model.Pessoa;

public record PessoaDTO(Long idPessoa, String nome, String malaDireta) {

    public PessoaDTO(Pessoa pessoa) {
        this(pessoa.getIdPessoa(), 
             pessoa.getNome(), 
             pessoa.getEndereco() + " – CEP: " + pessoa.getCep() + " – " + pessoa.getCidade() + "/" + pessoa.getUf());
    }
}
