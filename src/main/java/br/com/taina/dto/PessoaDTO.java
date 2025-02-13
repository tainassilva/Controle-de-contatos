package br.com.taina.dto;

import java.util.List;

import br.com.taina.contatosEnum.Estados;
import br.com.taina.model.Pessoa;


public class PessoaDTO {

	    private Long idPessoa;
	    private String nome;
	    private String endereco;
	    private String cep;
	    private String cidade;
	    private Estados uf;
	    private List<ContatoDTO> contatos;

	    public PessoaDTO(Pessoa pessoa, List<ContatoDTO> contatos) {
	        this.idPessoa = pessoa.getIdPessoa();
	        this.nome = pessoa.getNome();
	        this.endereco = pessoa.getEndereco();
	        this.cep = pessoa.getCep();
	        this.cidade = pessoa.getCidade();
	        this.uf = pessoa.getUf();
	        this.contatos = contatos;
	    
}

	    // Getters e Setters
	


}

