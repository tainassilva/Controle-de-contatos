package br.com.taina.entity;

import br.com.taina.enums.TipoContato;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Representa um contato de uma pessoa mapeada para uma tabela no banco de dados 
 * e contém informações sobre o tipo e o valor do contato, além de uma referência à pessoa associada. Também temos a relação
 * de muitos para um , que recebe uma chave estrangeira da entidade Pessoa. Podemos dizer assim : 
 * <b>Vários contatos relacionado a uma pessoa </b>
 * 
 * <p>O contato pode ser de diferentes tipos, como telefone fixo , celular, e-mail e linkedIn definidos no {@link TipoContato}.
 * Esta classe é usada para armazenar e manipular as informações relacionadas ao contato de uma pessoa.</p>
 */
@Entity
@Schema(hidden = true)
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private TipoContato tipoContato; 

    
    @Column(nullable = false, length = 50)
    private String contato;
    
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Contato() {}

  
    public Contato(Long id, TipoContato tipoContato, String contato, Pessoa pessoa) {
		this.tipoContato = tipoContato;
		this.contato = contato;
		this.pessoa = pessoa;
	}

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
        return "Contato [id=" + id + "," +
                " tipoContato=" + tipoContato + "," +
                " contato=" + contato + "," +
                " pessoa=" + pessoa + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Contato contato1)) return false;
        return Objects.equals(getId(), contato1.getId()) && getTipoContato() == contato1.getTipoContato() && Objects.equals(getContato(), contato1.getContato()) && Objects.equals(getPessoa(), contato1.getPessoa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTipoContato(), getContato(), getPessoa());
    }
}
