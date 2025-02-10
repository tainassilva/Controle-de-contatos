package br.com.taina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taina.model.Contato;
import br.com.taina.model.Pessoa;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
	List <Contato> findByPessoa(Pessoa pessoa);
}
