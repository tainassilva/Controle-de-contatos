package br.com.taina.dto;

import br.com.taina.model.Pessoa;

/**
 * Record responsável por representar os dados de uma pessoa com informações para mala direta.
 * O record é uma classe imutável que gera automaticamente os métodos getter, 
 * por isso não há necessidade de setters. 
 * Este DTO é utilizado para retornar os dados concatenados . 
 */
public record PessoaMalaDiretaDTO(Long id, String nome, String malaDireta) {

    /**
     * Construtor que cria um objeto {@link PessoaMalaDiretaDTO} a partir da instância de {@link Pessoa}.
     * Concatena as informações de endereço, incluindo o CEP, cidade e UF, para formar a string 'malaDireta'.
     *
     * @param pessoa Objeto {@link Pessoa} contem as informações da pessoa.
     */
    public PessoaMalaDiretaDTO(Pessoa pessoa) {
        this(pessoa.getId(), 
             pessoa.getNome(), 
             pessoa.getEndereco() + " – CEP: " + pessoa.getCep() + " – " + pessoa.getCidade() + " / " + pessoa.getUf());
    }
}

