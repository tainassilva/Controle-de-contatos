package br.com.taina.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taina.dto.ContatoDTO;
import br.com.taina.exception.contato.ContatoNotFoundException;
import br.com.taina.exception.pessoa.PessoaNotFoundException;
import br.com.taina.model.Contato;
import br.com.taina.model.Pessoa;
import br.com.taina.repository.ContatoRepository;
import br.com.taina.repository.PessoaRepository;
import br.com.taina.validation.ContatoValidation;

@Service
public class ContatoService {
	
	@Autowired
	ContatoRepository contatoRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	ContatoValidation contatoValidation;
	
	
	public Contato save(Contato contato) {    
		
	        Optional<Pessoa> findPessoa = pessoaRepository.findById(contato.getPessoa().getIdPessoa());
	        
	        if (findPessoa.isPresent()) {
	        	
	        	contatoValidation.validarContato(contato);
	        	 // Se a pessoa existir, atualiza o contato com a pessoa encontrada
	            contato.setPessoa(findPessoa.get());
	            // Salvar o contato no banco (não é o pessoaRepository, e sim o contatoRepository)
	            return contatoRepository.save(contato);
	           
	        } else {
	        	 // Se a pessoa não for encontrada, retorna null ou uma mensagem
	        	throw new PessoaNotFoundException("Pessoa com ID " + findPessoa + " não encontrada");
	          
	        }
	}

	public ContatoDTO findById(Long id) {
	    Optional<Contato> contatoOpt = contatoRepository.findById(id);

	    if (contatoOpt.isPresent()) {
	        Contato contato = contatoOpt.get();
	        return new ContatoDTO(
	            contato.getIdContato(),
	            contato.getTipoContato(),
	            contato.getContato()
	        );
	    }else {
	    throw new ContatoNotFoundException("Contato com ID " + id + " não encontrado");
	    }
	}
	public List<Contato> findAllPessoa(Long idPessoa) {
	    // Buscar a pessoa pelo ID
		
	    Optional<Pessoa> findPessoa = pessoaRepository.findById(idPessoa);
	    
	    if (findPessoa.isPresent()) {
	        // Retorna todos os contatos da pessoa encontrada
	        return contatoRepository.findByPessoa(findPessoa.get());
	    } else {
	    	throw new PessoaNotFoundException("Pessoa não encontrada! Digite um id válido.");
	    }
	}
	
//	public List<Contato> findAllPessoa(Long idPessoa) {
//	    Pessoa pessoa = pessoaRepository.findById(idPessoa)
//	        .orElseThrow(() -> new PessoaNotFoundException("Pessoa não encontrada! Digite um ID válido."));
//
//	    List<Contato> contatos = contatoRepository.findByPessoa(pessoa);
//	    
//	    if (contatos.isEmpty()) {
//	        throw new SemContatoAssociadoException("Pessoa com nenhum contato associado!");
//	    }
//	    return contatos;
//	}

	public Contato update(Long id, Contato contato) {
	    // Buscar o contato pelo ID recebido na URL
	    Optional<Contato> findContato = contatoRepository.findById(id);
	    
	    if (findContato.isPresent()) {
	    	
	    	contatoValidation.validarContato(contato);
	        Contato updContato = findContato.get();
	        
	        updContato.setTipoContato(contato.getTipoContato());
	        updContato.setContato(contato.getContato());
	        
	        return contatoRepository.save(updContato); // UPDATE
	    }

	    throw new ContatoNotFoundException("Contato com ID " + id + " não encontrado");
	}
	
	   // CRUD - Delete
    public void delete(Long id) {
        Optional<Contato> contatoOpt = contatoRepository.findById(id);
        if (contatoOpt.isPresent()) {
        	contatoRepository.deleteById(id);
        } else {
           throw new ContatoNotFoundException("Contato com id " + id + " não encontrada para exclusão.");
        }
    }

}
