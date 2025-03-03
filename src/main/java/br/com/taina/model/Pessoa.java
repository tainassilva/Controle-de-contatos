package br.com.taina.model;

import br.com.taina.enums.Estados;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Representa uma pessoa mapeada para uma tabela no banco de dados. Contém informações pessoais, como nome, endereço, 
 * cidade, estado e contatos associados à pessoa. A relação entre pessoa e contatos é um relacionamento um-para-muitos
 * ou seja, uma pessoa pode ter múltiplos contatos.
 *
 * <p>A pessoa pode ter uma lista de contatos associados, que são armazenados na tabela relacionada. Cada contato pode 
 * ter um tipo específico ( telefone fixo ,celular, e-mail e linkedIn), conforme definido no {@link TipoContato}.</p>
 */
@Entity
@Schema(hidden = true)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    @Column(nullable = false)
    private String nome;

    private String endereco;
    private String cep;
    private String cidade;

    @Enumerated(EnumType.STRING)
    private Estados uf;

    @JsonIgnore // Ignora o campo durante a serialização para evitar erros de processamento. 
    // O campo é excluído porque o PessoaDTO não contém uma lista de contatos, apenas os dados da pessoa.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", targetEntity = Contato.class)
    private List<Contato> contatos;

    public Pessoa() {}
    

    public Pessoa(Long id, String nome, String endereco, String cep, String cidade, Estados uf,
			List<Contato> contatos) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.contatos = contatos;
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
    
    

    public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", cep=" + cep + ", cidade=" + cidade
				+ ", uf=" + uf + "]";
	}
}