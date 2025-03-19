package br.com.taina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taina.dto.PessoaDTO;
import br.com.taina.dto.PessoaMalaDiretaDTO;
import br.com.taina.enums.Estados;
import br.com.taina.exception.ErroServidorException;
import br.com.taina.exception.IdNotFoundException;
import br.com.taina.exception.NadaParaListarException;
import br.com.taina.exception.CampoNotNullException;
import br.com.taina.model.Pessoa;
import br.com.taina.repository.PessoaRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço que oferece operações CRUD (Create, Read, Update, Delete) para a entidade {@link Pessoa}.
 * O serviço interage com o repositório {@link PessoaRepository} para realizar as operações no banco de dados.
 * As operações incluem salvar, buscar, atualizar e excluir registros de pessoas
 */
@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    /**
     * Cria um novo registro de pessoa no banco de dados.
     * 
     * Valida os dados da pessoa antes de salvar e cria a entidade {@link Pessoa} a partir do DTO {@link PessoaDTO}.
     * Depois, persiste a pessoa no banco e retorna o DTO com os dados salvos
     */
    public PessoaDTO save(PessoaDTO pessoaDTO) {

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(pessoaDTO.getNome());
            pessoa.setEndereco(pessoaDTO.getEndereco());
            pessoa.setNumeroCasa(pessoaDTO.getNumeroCasa());
            pessoa.setCep(pessoaDTO.getCep());
            pessoa.setCidade(pessoaDTO.getCidade());

            if (pessoaDTO.getUf() != null) {
                pessoa.setUf(Estados.valueOf(pessoaDTO.getUf().toUpperCase()));
            } else {
                pessoa.setUf(null);
            }

            try {
            pessoa = pessoaRepository.save(pessoa);

            return new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getEndereco(),
                    pessoa.getNumeroCasa(),
                    pessoa.getCep(), pessoa.getCidade(), 
                    pessoa.getUf() != null ? pessoa.getUf().name() : null);

        } catch (ErroServidorException e) {
            throw new ErroServidorException("Erro ao salvar a pessoa: " + e.getMessage());
        }
    }

    /**
     * Retorna todas as pessoas registradas no banco de dados.
     * 
     * @return Lista de {@link PessoaDTO} com as informações das pessoas cadastradas.
     */
    public List<PessoaDTO> findAll() {
    	try {
            List<Pessoa> pessoas = pessoaRepository.findAll();

            if (pessoas.isEmpty()) {
                throw new NadaParaListarException("Nenhuma pessoa encontrada para listar.");
            }

            // Mapeando as propriedades de pessoa para pessoaDTO e adicionando em uma lista de pessoas 
            return pessoas.stream().map(pessoa -> new PessoaDTO(
                            pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getEndereco(),
                            pessoa.getNumeroCasa(),
                            pessoa.getCep(),
                            pessoa.getCidade(),
                            pessoa.getUf() != null ? pessoa.getUf().name() : null // Converte o Enum 'uf' para String
                            													 // Se o  uf for nulo retorna null, se não for, converte 
                    ))
                    .collect(Collectors.toList());

        } catch (ErroServidorException e) {
            throw new ErroServidorException(e.getMessage());
        }
    }


    /**
     * Busca uma pessoa pelo ID. Se a pessoa não for encontrada, lança uma exceção.
     * 
     * @param id O ID da pessoa a ser buscada.
     * @return O DTO {@link PessoaDTO} com os dados da pessoa encontrada.
     */
    public PessoaDTO findById(Long id) {
    	 if (id == null) {
             throw new CampoNotNullException("Erro! Campo ID não pode ser nulo.");
         }
        try {
            // Verifica se a pessoa existe e mapeia para o DTO
            return pessoaRepository.findById(id)
                .map(pessoa -> new PessoaDTO(
                        pessoa.getId(),
                        pessoa.getNome(),
                        pessoa.getEndereco(),
                        pessoa.getNumeroCasa(),
                        pessoa.getCep(),
                        pessoa.getCidade(),
                        pessoa.getUf() != null ? pessoa.getUf().name() : null 
                ))
                .orElseThrow(() -> new IdNotFoundException("Pessoa com ID " + id + " não encontrada"));
        } catch (ErroServidorException e) {
            throw new ErroServidorException(e.getMessage());
        }
    }


    /**
     * Busca uma pessoa pelo ID e retorna os dados no formato de DTO {@link PessoaMalaDiretaDTO}.
     * Se a pessoa não for encontrada, lança uma exceção.
     * 
     * @param id O ID da pessoa a ser buscada.
     * @return O DTO {@link PessoaMalaDiretaDTO} com as informações da pessoa.
     */
    public PessoaMalaDiretaDTO findPessoaById(Long id) {
        if (id == null) {
            throw new CampoNotNullException("Erro! Campo ID não pode ser nulo.");
        }

        try {
            Pessoa pessoa = pessoaRepository.findById(id)
                    .orElseThrow(() -> new IdNotFoundException("Pessoa com ID " + id + " não encontrada"));

            return new PessoaMalaDiretaDTO(pessoa);
        } catch (ErroServidorException e) {
            throw new ErroServidorException(e.getMessage());
        }
    }

    /**
     * Atualiza os dados de uma pessoa no banco de dados.
     * Se a pessoa não for encontrada, lança uma exceção.
     * 
     * @param id O ID da pessoa a ser atualizada.
     * @param pessoaDTO DTO com os novos dados da pessoa.
     * @return O DTO {@link PessoaDTO} com os dados atualizados.
     */
    public PessoaDTO update(Long id, PessoaDTO pessoaDTO) {
            if (id == null) {
                throw new CampoNotNullException("Erro! Campo ID não pode ser nulo.");
            }

            Pessoa pessoa = pessoaRepository.findById(id)
                    .orElseThrow(() -> new IdNotFoundException("Pessoa com ID " + id + " não encontrada para atualização!"));


            pessoa.setNome(pessoaDTO.getNome());
            pessoa.setEndereco(pessoaDTO.getEndereco());
            pessoa.setCep(pessoaDTO.getCep());
            pessoa.setCidade(pessoaDTO.getCidade());

            // Permitir UF nula
            if (pessoaDTO.getUf() != null && !pessoaDTO.getUf().trim().isEmpty()) {
                pessoa.setUf(Estados.valueOf(pessoaDTO.getUf().trim().toUpperCase()));
            } else {
                pessoa.setUf(null);
            }

            try {
            pessoa = pessoaRepository.save(pessoa);

            return new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getEndereco(),
                                 pessoa.getNumeroCasa(),
                                 pessoa.getCep(), pessoa.getCidade(),
                                 pessoa.getUf() != null ? pessoa.getUf().name() : null);

        } catch (ErroServidorException e) {
            throw new ErroServidorException(e.getMessage());
        }
    }



    /**
     * Exclui uma pessoa do banco de dados.
     * Se a pessoa não for encontrada, lança uma exceção.
     * 
     * @param id O ID da pessoa a ser excluída.
     */
    public void delete(Long id) {
        if (id == null) {
            throw new CampoNotNullException("Erro! Campo ID não pode ser nulo.");
        }
       
            Pessoa pessoa = pessoaRepository.findById(id)
                    .orElseThrow(() -> new IdNotFoundException("Pessoa com ID " + id + " não encontrada para exclusão"));
            try {
            pessoaRepository.delete(pessoa);
        } catch (Exception e) {
            throw new ErroServidorException("Erro ao excluir a pessoa: " + e.getMessage());
        }
    }
}
