package br.com.taina.dto.contato;

import br.com.taina.validation.constraint.TipoContatoValid;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * Classe DTO responsável por representar os dados de contato de uma pessoa.
 * 
 * Este DTO inclui apenas as informações relacionadas ao contato.
 * A classe não contém dados relacionados à pessoa, exceto o identificador (ID) da pessoa, utilizado
 * para realizar consultas ou associar o contato a entidade de pessoa no sistema.
 *
 * Essa classe também contém algumas annotations para validar os campos.
 */

public class ContatoDTO {
	
	@Schema(hidden = true)
	private Long id;
	
	@Schema(description = "Tipos de contato da pessoa.",
			example = "CELULAR")
	@NotBlank(message = "Erro! O tipo de contato não pode ser nulo ou vazio.")
	@TipoContatoValid
	private String tipoContato;
	
	@Schema(description = "Contato da pessoa", 
			example = "11974510719")
	@NotBlank(message= "Erro! O contato não pode ser nulo ou vazio.")
	private String contato;
	
	@Schema(description = "ID da pessoa que possui o contato",
			example = "1")
	@NotNull(message= "Erro! O campo idPessoa não pode ser nulo ou vazio.")
    private Long idPessoa;
    
    public ContatoDTO() {};

	public ContatoDTO(Long id, String tipoContato, String contato, Long idPessoa) {
		this.id = id;
		this.tipoContato = tipoContato;
		this.contato = contato;
		this.idPessoa = idPessoa;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	

	public Long getIdPessoa() {
		return idPessoa;
	}


	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	@Override
	public String toString() {
		return "ContatoDTO [idContato=" + id + ", tipoContato=" + tipoContato + ", contato=" + contato
				+ ", idPessoa=" + idPessoa + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ContatoDTO that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTipoContato(), that.getTipoContato()) && Objects.equals(getContato(), that.getContato()) && Objects.equals(getIdPessoa(), that.getIdPessoa());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getTipoContato(), getContato(), getIdPessoa());
	}
}
