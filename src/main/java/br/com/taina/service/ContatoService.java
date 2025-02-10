package br.com.taina.service;

import java.util.List;
import java.util.Optional;
//import java.util.regex.Pattern;// pegar da classe pessoa para validar 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taina.model.Contato;
import br.com.taina.model.Pessoa;
import br.com.taina.repository.ContatoRepository;
import br.com.taina.repository.PessoaRepository;

@Service
public class ContatoService {
	
	@Autowired
	ContatoRepository contatoRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public Contato save(Contato contato) {        
	    // Verificar se o contato tem uma pessoa associada
	    if (contato.getPessoa() != null && contato.getPessoa().getIdPessoa() != null) {
	        // Buscar a pessoa no banco de dados
	        Optional<Pessoa> findPessoa = pessoaRepository.findById(contato.getPessoa().getIdPessoa());
	        
	        if (findPessoa.isEmpty()) {
	            // Se a pessoa não for encontrada, retorna null ou uma mensagem
	            System.out.println("Pessoa não encontrada");
	            return null;
	        } else {
	            // Se a pessoa existir, atualiza o contato com a pessoa encontrada
	            contato.setPessoa(findPessoa.get());
	            // Salvar o contato no banco (não é o pessoaRepository, e sim o contatoRepository)
	            return contatoRepository.save(contato);
	        }            
	    } else {
	        // Se não tiver pessoa associada ao contato
	        System.out.println("Pessoa associada não encontrada ou inválida");
	        return null;
	    }        
	}

	//CRUD - Read (leitura individual ou lista)
	public Optional<Contato> findById(Long id){
		//select * from produto where id = ?id
		return contatoRepository.findById(id); 
	}
	
	public List<Contato> findAllPessoa(Long idPessoa) {
	    // Buscar a pessoa pelo ID
		
	    Optional<Pessoa> findPessoa = pessoaRepository.findById(idPessoa);
	    
	    if (findPessoa.isPresent()) {
	        // Retorna todos os contatos da pessoa encontrada
	        return contatoRepository.findByPessoa(findPessoa.get());
	    } else {
	        // Se a pessoa não for encontrada, retorna uma lista vazia ou lança exceção
	        System.out.println("Pessoa não encontrada");
	        return List.of(); // Retorna lista vazia
	    }
	}

	public Contato update(Contato contato) {
		//Pesquisar se o estoque existe
		Optional<Contato> findContato= contatoRepository
				.findById(contato.getIdContato());
		//se existir, atualizar:
		if(findContato.isPresent()) {
			//variavel auxiliar
			Contato updContato = findContato.get();
			 updContato.setTipoContato(contato.getTipoContato());
	         updContato.setContato(contato.getContato());			//gravar no banco
			return contatoRepository.save(updContato);
		}
		return contatoRepository.save(contato);
	}
	
	public void delete(Long id) {
		contatoRepository.deleteById(id);
	}

}
