package br.com.taina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taina.model.Pessoa;

/**
 * Repositório responsável por interagir com o banco de dados para realizar operações de CRUD na entidade {@link Pessoa}.
 * Extende a interface {@link JpaRepository}, que já fornece implementações prontas para operações básicas de banco de dados.
 * 
 *  <p>O JpaRepository tem parâmetro de dois valores:</p>
 * <ul>
 *   <li><b>Pessoa</b>: Define que o repositório vai gerenciar a entidade {@link Pessoa}.</li>
 *   <li><b>Long</b>: Define que o tipo da chave primária (ID) da entidade {@link Pessoa} será do tipo {@link Long}.</li>
 * </ul>
 * <p>Este repositório herda todas as operações básicas de CRUD do JpaRepository e pode ser estendido para adicionar métodos personalizados 
 * relacionados à entidade {@link Pessoa}.</p>
 *  
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // Não são necessários métodos adicionais, pois o JpaRepository já oferece as operações básicas.
}
