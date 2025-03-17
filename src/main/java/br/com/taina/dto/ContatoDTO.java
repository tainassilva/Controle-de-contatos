package br.com.taina.dto;

import br.com.taina.validation.constraint.TipoContatoValid;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Classe DTO responsável por representar os dados de contato de uma pessoa.
 * 
 * Este DTO inclui apenas as informações relacionadas ao contato.
 * A classe não contém dados relacionados à pessoa, exceto o identificador (ID) da pessoa, utilizado
 * para realizar consultas ou associar o contato a entidade de pessoa no sistema.
 */

public class ContatoDTO {
	
	@Schema(hidden = true)
	// O campo 'id' será incluído apenas se não for nulo. Como o valor de 'id' nunca será nulo,
	// essa anotação previne erros de serialização, garantindo que o campo seja corretamente
	// tratado durante o processo de conversão para JSON.
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;
	
	@Schema(description = "Tipos de contato da pessoa.",
			example = "CELULAR")
	@NotBlank(message = "Erro! O tipo de contato não pode ser nulo ou vazio.")
	private String tipoContato;
	
	@Schema(description = "Contato da pessoa", 
			example = "11974510719")
	@NotBlank(message= "Erro! O contato não pode ser nulo ou vazio.")
	@TipoContatoValid(message= "Erro! Insira um contato válido.")
	private String contato;
	
	@Schema(description = "ID da pessoa que possui o contato",
			example = "1")
	@Pattern(regexp = "^\\d+$\n", message= "Erro! Campo IDPessoa aceita apenas números.")
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
}
