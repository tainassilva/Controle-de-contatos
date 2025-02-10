package br.com.taina.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
	private Long idPessoa;
	
	@Column(nullable = false)
	private String nome;
	
	private String endereco;
	private String cep;
	private String cidade;
	private String uf;
	
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy="pessoa",
			targetEntity = Contato.class)
    private List<Contato> contatos;

	
	public Pessoa() {
	}
	
	public Pessoa(Long idPessoa, String nome, String endereco, String cep, String cidade, String uf,
			List<Contato> contatos) {
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.endereco = endereco;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.contatos = contatos;
	}
	public Long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
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
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome + ", endereco=" + endereco + ", cep=" + cep
				+ ", cidade=" + cidade + ", uf=" + uf + ", contatos=" + contatos + "]";
	}
	
	
	
	
}
