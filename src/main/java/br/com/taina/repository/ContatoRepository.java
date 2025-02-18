package br.com.taina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.taina.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    /**
     * Busca todos os contatos associados a uma pessoa específica.
     * 
     * @param pessoa A pessoa associada aos contatos.
     * @return Uma lista de contatos associados à pessoa.
     */

	    @Query("SELECT c FROM Contato c WHERE c.pessoa.id = :idPessoa")
	    List<Contato> findContatosPessoaById(@Param("idPessoa") Long Id);
	
}

