package br.com.taina.dto;

import br.com.taina.enums.Estados;
import io.swagger.v3.oas.annotations.media.Schema;

public class PessoaDTO {
	
	@Schema(hidden = true)
	private Long id;
	
	@Schema(description = "Nome da pessoa.", example = "Taina")
	private String nome;
	
	@Schema(description = "Endereço da pessoa.", example = "Rua Penha, 102")
	private String endereco;
	
	@Schema(description = "Cep da pessoa. Exemplo de formato:  XXXXX-XXX ou XXXXXXXX", example = "06700000")
	private String cep;
	
	@Schema(description = "Cidade da pessoa", example = "Cotia")
	private String cidade;
	
	@Schema(description = "Estado da pessoa (UF)", example = "SP")
	private Estados uf;
	
	public PessoaDTO() {}

	public PessoaDTO(Long id, String nome, String endereco, String cep, String cidade, Estados uf) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estados getUf() {
		return uf;
	}

	public void setUf(Estados uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "PessoasDTO [id=" + id + ", nome=" + nome + ", endereco=" + endereco + 
				           ", cep=" + cep + ", cidade=" + cidade + ", uf=" + uf + "]";
	}	
}
