package br.com.taina.model;

import br.com.taina.enums.TipoContato;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

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

    /**
     * Identificador único do contato.
     * Esse campo é gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    /**
     * Tipo de contato, que pode ser definido conforme os Enums no {@link TipoContato}.
     * Esse campo é obrigatório.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoContato tipoContato; 

    /**
     * Valor do contato: número de telefone fixo ou celular, endereço de e-mail ou linkedIn.
     * Este campo é obrigatório.
     */
    @Column(nullable = false)
    private String contato;

    /**
     * Pessoa associada a este contato. Relacionamento muitos-para-um com a entidade {@link Pessoa}.
     * Vários coontatos pertence a uma pessoa específica.
     */
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Contato() {}

  
    public Contato(Long id, TipoContato tipoContato, String contato, Pessoa pessoa) {
		this.tipoContato = tipoContato;
		this.contato = contato;
		this.pessoa = pessoa;
	}


	// Getters e Setters

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

    /**
     * Representação em formato de string do objeto Contato.
     * 
     * @return String contendo informações sobre o contato, como ID, tipo e valor do contato, e a pessoa associada.
     */
    @Override
    public String toString() {
        return "Contato [id=" + id + ", tipoContato=" + tipoContato + ", contato=" + contato + ", pessoa=" + pessoa + "]";
    }
}
