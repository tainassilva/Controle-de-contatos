package br.com.taina.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taina.dto.PessoaDTO;
import br.com.taina.dto.PessoaMalaDiretaDTO;
import br.com.taina.exception.ErroServidorException;
import br.com.taina.exception.FormatoInvalidoException;
import br.com.taina.exception.IdNotFoundException;
import br.com.taina.exception.NotNullException;
import br.com.taina.model.Pessoa;
import br.com.taina.repository.PessoaRepository;
import br.com.taina.validation.PessoaValidation;

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
    public PessoaDTO save(PessoaDTO pessoaDTO) {
        try {
            // Valida os dados da pessoa
            pessoaValidation.validarPessoaDTO(pessoaDTO);

            // Converte o DTO para entidade
            Pessoa pessoa = new Pessoa();
            BeanUtils.copyProperties(pessoaDTO, pessoa);

            // Salva no banco
            pessoa = pessoaRepository.save(pessoa);

            // Retorna o DTO com os dados salvos
            return new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getEndereco(),
                                  pessoa.getCep(), pessoa.getCidade(), pessoa.getUf());
        } catch (FormatoInvalidoException e) {
            // Lança exceções específicas de validação
            throw new FormatoInvalidoException(e.getMessage());
        }  catch (NotNullException e) {
            // Lança exceções específicas de validação
            throw new NotNullException(e.getMessage());
        }catch (ErroServidorException e) {
            // Lança exceção genérica para qualquer outro erro
            throw new ErroServidorException("Erro ao salvar a pessoa: " + e.getMessage());
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
    public PessoaDTO findById(Long id) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);
      
        if (pessoaOpt.isPresent()) {
            try {
                Pessoa pessoa = pessoaOpt.get();
                PessoaDTO pessoaDTO = new PessoaDTO();
                
                pessoaDTO.setId(pessoa.getId());
                pessoaDTO.setNome(pessoa.getNome());
                pessoaDTO.setEndereco(pessoa.getEndereco());
                pessoaDTO.setCep(pessoa.getCep());
                pessoaDTO.setCidade(pessoa.getCidade());
                pessoaDTO.setUf(pessoa.getUf());
                
                return pessoaDTO;
            } catch (ErroServidorException e) {
                throw new ErroServidorException(e.getMessage());
            }
        } else {
            throw new IdNotFoundException("Pessoa com ID " + id + " não encontrada");
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
            throw new IdNotFoundException("Pessoa com ID " + id + " não encontrada");
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
    public PessoaDTO update(Long id, PessoaDTO pessoaDTO) {
        Optional<Pessoa> findPessoa = pessoaRepository.findById(id);
        
        if (findPessoa.isPresent()) {
            // Valida os dados da pessoa antes de atualizar
            pessoaValidation.validarPessoaDTO(pessoaDTO);

            Pessoa updPessoa = findPessoa.get();  // Agora o objeto `updPessoa` é o encontrado no banco

            // Atualiza os dados da pessoa com as informações do DTO
            updPessoa.setNome(pessoaDTO.getNome());
            updPessoa.setEndereco(pessoaDTO.getEndereco());
            updPessoa.setCep(pessoaDTO.getCep());
            updPessoa.setCidade(pessoaDTO.getCidade());
            updPessoa.setUf(pessoaDTO.getUf());
            
            try {
                // Salva a pessoa atualizada no banco
                updPessoa = pessoaRepository.save(updPessoa);  // Salva no banco para garantir que a atualização aconteça

                // Cria o DTO com as propriedades da pessoa atualizada
                PessoaDTO updPessoaDTO = new PessoaDTO();
                BeanUtils.copyProperties(updPessoa, updPessoaDTO);  // Copia as propriedades da pessoa para o DTO

                return updPessoaDTO;
            } catch (Exception e) {
                throw new ErroServidorException("Erro ao atualizar a pessoa: " + e.getMessage());
            }
        } else {
            throw new IdNotFoundException("Pessoa com ID " + id + " não encontrada para a atualização!");
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
        	throw new IdNotFoundException("Pessoa com ID " + id + " não encontrada para exclusão");
        }
    }
}
