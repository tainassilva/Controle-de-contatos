package br.com.taina.dto;

import br.com.taina.contatosEnum.TipoContato;

public class ContatoDTO {
	
    private Long idContato;
    private TipoContato tipoContato;
    private String contato;

    
	public ContatoDTO(Long idContato, TipoContato tipoContato, String contato) {
		this.idContato = idContato;
		this.tipoContato = tipoContato;
		this.contato = contato;
	}
	public Long getIdContato() {
		return idContato;
	}
	public void setIdContato(Long idContato) {
		this.idContato = idContato;
	}
	public TipoContato getTipoContato() {
		return tipoContato;
	}
	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
    
}
