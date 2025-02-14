package br.com.taina.model;

import br.com.taina.enums.Estados;
import jakarta.persistence.*;
import java.util.List;

/**
 * Representa uma pessoa mapeada para uma tabela no banco de dados. Contém informações pessoais, como nome, endereço, 
 * cidade, estado e contatos associados à pessoa. A relação entre pessoa e contatos é um relacionamento um-para-muitos
 * ou seja, uma pessoa pode ter múltiplos contatos.
 *
 * <p>A pessoa pode ter uma lista de contatos associados, que são armazenados na tabela relacionada. Cada contato pode 
 * ter um tipo específico ( telefone fixo ,celular, e-mail e linkedIn), conforme definido no {@link TipoContato}.</p>
 */
@Entity
public class Pessoa {

    /**
     * Identificador único da pessoa.
     * Esse campo é gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    /**
     * Nome da pessoa.
     * Esse campo é obrigatório.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * Endereço da pessoa.
     * Este campo é opcional.
     */
    private String endereco;

    /**
     * Código postal (CEP) da pessoa.
     * Este campo é opcional.
     */
    private String cep;

    /**
     * Cidade onde a pessoa reside.
     * Este campo é opcional.
     */
    private String cidade;

    /**
     * Estado (UF) onde a pessoa reside, representado pelo enum {@link Estados}.
     * Este campo é opcional.
     */
    @Enumerated(EnumType.STRING)
    private Estados uf;

    /**
     * Lista de contatos associados a esta pessoa.
     * Relacionamento um-para-muitos com a entidade {@link Contato}.
     * Cada pessoa pode ter múltiplos contatos.
     * 
     * A anotação {@link OneToMany} indica que este é um relacionamento um-para-muitos,
     * ou seja, uma pessoa pode ter vários contatos. O parâmetro "cascade = CascadeType.ALL" 
     * garante que todas as operações de persistência (como salvar, atualizar, deletar) realizadas
     * na entidade {@link Pessoa} sejam automaticamente propagadas para os contatos associados.
     * O parâmetro "mappedBy = 'pessoa'" informa que o lado "muitos" (Contato) é o responsável 
     * pela referência ao lado "um" (Pessoa). Ou seja, o atributo "pessoa" na entidade Contato
     * mapeia essa relação. O parâmetro "targetEntity = Contato.class" define explicitamente 
     * a entidade alvo do relacionamento.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", targetEntity = Contato.class)
    private List<Contato> contatos;

    public Pessoa() {}
    
    public Pessoa(Long idPessoa, String nome, String endereco, String cep, String cidade, Estados uf, List<Contato> contatos) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.contatos = contatos;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
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

    @Override
    public String toString() {
        return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome + ", endereco=" + endereco + ", cep=" + cep
                + ", cidade=" + cidade + ", uf=" + uf + ", contatos=" + contatos + "]";
    }
}
