package br.com.taina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taina.dto.PessoaMalaDiretaDTO;
import br.com.taina.exception.pessoa.ErroServidorException;
import br.com.taina.exception.pessoa.PessoaNotFoundException;
import br.com.taina.model.Pessoa;
import br.com.taina.repository.PessoaRepository;
import br.com.taina.validation.PessoaValidation;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;
    
    @Autowired
    PessoaValidation pessoaValidation;

    // CRUD - Create
    public Pessoa save(Pessoa pessoa) {
    	
         // Validação
    	 pessoaValidation.validarPessoa(pessoa);
        try {
            return pessoaRepository.save(pessoa);
        } catch (Exception e) {
            throw new ErroServidorException("Erro no servidor!");
        }
    }
    

    // CRUD - Read (leitura individual ou lista)
    
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }
    
    public Pessoa findById(Long id) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);
      
        if (pessoaOpt.isPresent()) {
          return pessoaOpt.get();
         }
        else {
        throw new PessoaNotFoundException("Pessoa com ID " + id + " não encontrada");
    }
}

    public PessoaMalaDiretaDTO findPessoaById(Long id) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);

        if (pessoaOpt.isPresent()) {
        	  return new PessoaMalaDiretaDTO(pessoaOpt.get());
        }
        throw new PessoaNotFoundException("Pessoa com ID " + id + " não encontrada");
    }

 // CRUD - Update
    public Pessoa update(Long id, Pessoa pessoa) {
        // Buscar a pessoa pelo ID
        Optional<Pessoa> findPessoa = pessoaRepository.findById(id);

        // Verifica se a pessoa foi encontrada
        if (findPessoa.isPresent()) {

            pessoaValidation.validarPessoa(pessoa);
            
            // Obtém a pessoa para atualização
            Pessoa updPessoa = findPessoa.get();

            // Atualiza os campos da pessoa
            updPessoa.setNome(pessoa.getNome());
            updPessoa.setEndereco(pessoa.getEndereco());
            updPessoa.setCep(pessoa.getCep());
            updPessoa.setCidade(pessoa.getCidade());
            updPessoa.setUf(pessoa.getUf());

            // Salva a pessoa no banco de dados
            return pessoaRepository.save(updPessoa);
        }

    	throw new PessoaNotFoundException("Pessoa com ID " + id + " não encontrada para a atualização!");

    }

    
    // CRUD - Delete
    public void delete(Long id) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);
        if (pessoaOpt.isPresent()) {
            pessoaRepository.deleteById(id);
        } else {
        	throw new PessoaNotFoundException("Pessoa com ID " + id + " não encontrada para exclusão");
        }
    }
}


