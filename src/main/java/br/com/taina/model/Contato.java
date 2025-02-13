package br.com.taina.model;

import br.com.taina.contatosEnum.TipoContato;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long idContato;
	
	 @Enumerated(EnumType.STRING)
	 @Column(nullable = false)
	 private TipoContato tipoContato; 
	
	@Column(nullable = false)
	private String contato;
	
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;    
    
    public Contato() {}

    public Contato(Long idContato, TipoContato tipoContato, String contato) {
		this.idContato = idContato;
		this.tipoContato = tipoContato;
		this.contato = contato;
		
	}
    

	public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

	public Long getIdContato() {
		return idContato;
	}

	public void setIdContato(Long idContato) {
		this.idContato = idContato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	
	

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Contato [id=" + idContato + ", tipoContato=" + tipoContato + ", contato=" + contato + ", pessoa=" + pessoa
				+ "]";
	}
	
	
	
}
