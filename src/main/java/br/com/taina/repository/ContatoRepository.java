package br.com.taina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
	
	  List<Contato> findContatosPessoaById(Long id); 
}

