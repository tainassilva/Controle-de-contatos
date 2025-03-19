package br.com.taina.dto;

import br.com.taina.validation.constraint.AllowsOnlyLettersAndSpaces;
import br.com.taina.validation.constraint.CepValid;
import br.com.taina.validation.constraint.NullNotBlank;
import br.com.taina.validation.constraint.UFValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.Objects;


/**
 * Classe DTO responsável por representar os dados de uma pessoa, sem incluir a lista de contatos.
 * Neste momento, não é necessário expor a lista de contatos da pessoa.
 *
 *  Essa classe também contém algumas annotations para validar os campos.
 */

public class PessoaDTO {

	@Schema(hidden = true)
	private Long id;

	@Schema(description = "Nome da pessoa.", example = "Taina")
	@NotBlank(message = "Erro! O campo nome não pode ser vazio ou nulo.")
	@AllowsOnlyLettersAndSpaces(message = "Erro! Campo nome aceita apenas letras e espaços.")
	private String nome;

	@Schema(description = "Endereço da pessoa.", example = "Rua Penha")
	@AllowsOnlyLettersAndSpaces(message = "Erro! Campo endereço aceita apenas letras e espaços.")
	@NullNotBlank(message = "Erro! O campo endereço não pode ser vazio.")
	@Size(min = 5, max = 100, message = "O campo endereço deve conter entre 5 e 100 caracteres.")
	private String endereco;

	@Schema(description = "Número da casa da pessoa com complemento.", example = "12345")
	@NullNotBlank(message = "Erro! O campo número não pode ser vazio.")
	@Size(min = 1, max = 8, message= "Erro! O campo numero da casa aceita apenas 8 números")
	@Pattern(regexp = "^[0-9]+$", message = "Erro! O campo número da casa deve conter apenas números.")
	private String numeroCasa;

	@NullNotBlank(message = "Erro! O campo cep não pode ser vazio.")
	@Schema(description = "Cep da pessoa. Exemplo de formato:  XXXXX-XXX ou XXXXXXXX", example = "06700000")
	@CepValid
	private String cep;

	@Schema(description = "Cidade da pessoa", example = "Cotia")
	@NullNotBlank(message = "Erro! O campo cidade não pode ser vazio.")
	@AllowsOnlyLettersAndSpaces(message = "Erro! Campo cidade aceita apenas letras e espaços.")
	private String cidade;

	@Schema(description = "Estado da pessoa (UF)", example = "SP")
	@NullNotBlank(message = "Erro! O campo UF não pode ser vazio.")
	@UFValid
	private String uf;

	public PessoaDTO() {}


	public PessoaDTO(Long id, String nome, String endereco, String numeroCasa, String cep, String cidade, String uf) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.numeroCasa = numeroCasa;
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

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
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
		return "PessoasDTO [id=" + id + ", nome=" + nome + ", endereco=" + endereco +
				           ", cep=" + cep + ", cidade=" + cidade + ", uf=" + uf + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof PessoaDTO pessoaDTO)) return false;
        return Objects.equals(getId(), pessoaDTO.getId()) && Objects.equals(getNome(), pessoaDTO.getNome()) && Objects.equals(getEndereco(), pessoaDTO.getEndereco()) && Objects.equals(getNumeroCasa(), pessoaDTO.getNumeroCasa()) && Objects.equals(getCep(), pessoaDTO.getCep()) && Objects.equals(getCidade(), pessoaDTO.getCidade()) && Objects.equals(getUf(), pessoaDTO.getUf());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getNome(), getEndereco(), getNumeroCasa(), getCep(), getCidade(), getUf());
	}
}