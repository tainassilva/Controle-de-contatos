package br.com.taina.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

//import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

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
	private String tipoContato;
	
	@Schema(description = "Contato da pessoa", 
			example = "11974510719")
	private String contato;
	
	@Schema(description = "ID da pessoa que possui o contato",
			example = "1")
    private Long idPessoa;
    
    public ContatoDTO() {};
 

	public ContatoDTO(String tipoContato, String contato, Long idPessoa) {
		this.tipoContato = tipoContato;
		this.contato = contato;
		this.idPessoa = idPessoa;
	}
	

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
