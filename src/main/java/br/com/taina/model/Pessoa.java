package br.com.taina.model;

import br.com.taina.enums.Estados;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;

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

   
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 150)
    private String endereco;

    @Column(length = 8)
    private String numeroCasa;

    @Column(length = 8)
    private String cep;

    @Column(length = 50)
    private String cidade;

    @Enumerated(EnumType.STRING)
    @Column(length = 2)
    private Estados uf;

    // Não carrega imediatamente a lista de contatos, apenas quando ela for acessada explicitamente...
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", targetEntity = Contato.class, fetch = FetchType.LAZY)
    private List<Contato> contatos;

    public Pessoa() {}


    public Pessoa(Long id, String nome, String endereco, String numeroCasa, String cep, String cidade, Estados uf, List<Contato> contatos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.numeroCasa = numeroCasa;
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
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", numeroCasa='" + numeroCasa + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf=" + uf +
                ", contatos=" + contatos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pessoa pessoa)) return false;
        return Objects.equals(getId(), pessoa.getId()) && Objects.equals(getNome(), pessoa.getNome()) && Objects.equals(getEndereco(), pessoa.getEndereco()) && Objects.equals(getNumeroCasa(), pessoa.getNumeroCasa()) && Objects.equals(getCep(), pessoa.getCep()) && Objects.equals(getCidade(), pessoa.getCidade()) && getUf() == pessoa.getUf() && Objects.equals(getContatos(), pessoa.getContatos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getEndereco(), getNumeroCasa(), getCep(), getCidade(), getUf(), getContatos());
    }
}