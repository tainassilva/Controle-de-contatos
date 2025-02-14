package br.com.taina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taina.model.Contato;
import br.com.taina.model.Pessoa;

/**
 * Repositório responsável por interagir com o banco de dados para realizar operações de CRUD na entidade {@link Contato}.
 * Extende a interface {@link JpaRepository}, que já fornece implementações prontas para operações básicas de banco de dados.
 * 
 * <p>O JpaRepository tem o parâmetro de dois valores:</p>
 * <ul>
 *   <li><b>Contato</b>: Define que o repositório vai gerenciar a entidade {@link Contato}.</li>
 *   <li><b>Long</b>: Define que o tipo da chave primária (ID) da entidade {@link Contato} será do tipo {@link Long}.</li>
 * </ul>
 * 
 * <p>Além das operações padrão do JpaRepository, este repositório define o método {@link #findByPessoa(Pessoa)} para buscar contatos 
 * associados a uma pessoa específica.</p>
 */
@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    /**
     * Busca todos os contatos associados a uma pessoa específica.
     * 
     * @param pessoa A pessoa associada aos contatos.
     * @return Uma lista de contatos associados à pessoa.
     * 
     * A ideia é fazer um inner join ... 
     */
    List<Contato> findByPessoa(Pessoa pessoa);
}
