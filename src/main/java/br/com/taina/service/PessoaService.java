package br.com.taina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taina.dto.PessoaMalaDiretaDTO;
import br.com.taina.exception.ErroServidorException;
import br.com.taina.exception.pessoa.PessoaNotFoundException;
import br.com.taina.model.Pessoa;
import br.com.taina.repository.PessoaRepository;
import br.com.taina.validation.pessoa.PessoaValidation;

import java.util.List;
import java.util.Optional;

/**
 * Serviço que oferece operações CRUD (Create, Read, Update, Delete) para a entidade {@link Pessoa}.
 * O serviço interage com o repositório {@link PessoaRepository} para realizar as operações no banco de dados.
 * As operações incluem salvar, buscar, atualizar e excluir registros de pessoas. 
 * Também valida os dados usando a classe {@link PessoaValidation} antes de executar as operações.
 */
@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PessoaValidation pessoaValidation;

    /**
     * Cria um novo registro de pessoa no banco de dados.
     * Valida os dados da pessoa antes de salvar.
     * 
     * @param pessoa A entidade {@link Pessoa} a ser salva.
     * @return A pessoa salva com os dados persistidos.
     * @throws ErroServidorException Caso ocorra um erro durante a operação de salvamento.
     */
    public Pessoa save(Pessoa pessoa) {
        // Valida os dados da pessoa antes de salvar
        pessoaValidation.validarPessoa(pessoa);
        try {
            return pessoaRepository.save(pessoa);
        } catch (Exception e) {
            throw new ErroServidorException(e.getMessage());
        }
    }
    /**
     * Retorna todas as pessoas registradas no banco de dados.
     * 
     * @return Lista de pessoas.
     * @throws ErroServidorException Caso ocorra um erro durante a consulta.
     */
    public List<Pessoa> findAll() {
    	try {
    		return pessoaRepository.findAll();
    	} catch (Exception e) {
            throw new ErroServidorException(e.getMessage());
        }
    }

    /**
     * Busca uma pessoa pelo ID. Se a pessoa não for encontrada, lança uma exceção.
     * 
     * @param id O ID da pessoa a ser buscada.
     * @return A pessoa encontrada.
     * @throws PessoaNotFoundException Se a pessoa com o ID informado não for encontrada.
     * @throws ErroServidorException Caso ocorra um erro durante a operação de consulta.
     */
    public Pessoa findById(Long id) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);
      
        if (pessoaOpt.isPresent()) {
        	try {
        		//Retorna a pessoa do id correspondente que entrou no parâmetro
        		return pessoaOpt.get();
        	}catch (Exception e) {
        		throw new ErroServidorException(e.getMessage());
        	}
        } else {
        	throw new PessoaNotFoundException("Pessoa com ID " + id + " não encontrada");
        }
    }

    /**
     * Busca uma pessoa pelo ID e retorna os dados no formato de DTO {@link PessoaMalaDiretaDTO}.
     * Se a pessoa não for encontrada, lança uma exceção.
     * 
     * @param id O ID da pessoa a ser buscada.
     * @return O DTO com as informações da pessoa.
     * @throws PessoaNotFoundException Se a pessoa com o ID informado não for encontrada.
     * @throws ErroServidorException Caso ocorra um erro durante a operação de consulta.
     */
    public PessoaMalaDiretaDTO findPessoaById(Long id) {
    	
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);

        if (pessoaOpt.isPresent()) {
            try {
                // Converte a entidade Pessoa para DTO e retorna
                return new PessoaMalaDiretaDTO(pessoaOpt.get());
            } catch (Exception e) {
                throw new ErroServidorException(e.getMessage());
            }
        } else {
            throw new PessoaNotFoundException("Pessoa com ID " + id + " não encontrada");
        }
    }


    /**
     * Atualiza os dados de uma pessoa no banco de dados.
     * Se a pessoa não for encontrada, lança uma exceção.
     * 
     * @param id O ID da pessoa a ser atualizada.
     * @param pessoa A entidade {@link Pessoa} com os novos dados.
     * @return A pessoa atualizada.
     * @throws PessoaNotFoundException Se a pessoa com o ID informado não for encontrada.
     * @throws ErroServidorException Caso ocorra um erro durante a operação de atualização.
     */
    public Pessoa update(Long id, Pessoa pessoa) {
        Optional<Pessoa> findPessoa = pessoaRepository.findById(id);
        
        Pessoa updPessoa = new Pessoa();
        
        if (findPessoa.isPresent()) {
            // Valida os dados da pessoa antes de atualizar
            pessoaValidation.validarPessoa(pessoa);
            updPessoa = findPessoa.get();
            // Atualiza os dados da pessoa
            updPessoa.setNome(pessoa.getNome());
            updPessoa.setEndereco(pessoa.getEndereco());
            updPessoa.setCep(pessoa.getCep());
            updPessoa.setCidade(pessoa.getCidade());
            updPessoa.setUf(pessoa.getUf());
        } else {
            throw new PessoaNotFoundException("Pessoa com ID " + id + " não encontrada para a atualização!");
        }

        try {
            // Salva a pessoa atualizada no banco de dados
            return pessoaRepository.save(updPessoa);
        } catch (Exception e) {
            throw new ErroServidorException(e.getMessage());
        }
    }
    /**
     * Exclui uma pessoa do banco de dados.
     * Se a pessoa não for encontrada, lança uma exceção.
     * 
     * @param id O ID da pessoa a ser excluída.
     * @throws PessoaNotFoundException Se a pessoa com o ID informado não for encontrada.
     * @throws ErroServidorException Caso ocorra um erro durante a operação de exclusão.
     */
    public void delete(Long id) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);
        if (pessoaOpt.isPresent()) {
        	try {
        		pessoaRepository.deleteById(id);
        	}catch (Exception e) {
        		throw new ErroServidorException(e.getMessage());
        	}   
        } else {
        	throw new PessoaNotFoundException("Pessoa com ID " + id + " não encontrada para exclusão");
        }
    }
}
