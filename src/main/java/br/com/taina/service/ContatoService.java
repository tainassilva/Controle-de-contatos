package br.com.taina.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taina.dto.ContatoDTO;
import br.com.taina.exception.ErroServidorException;
import br.com.taina.exception.contato.ContatoNotFoundException;
import br.com.taina.exception.pessoa.PessoaNotFoundException;
import br.com.taina.model.Contato;
import br.com.taina.model.Pessoa;
import br.com.taina.repository.ContatoRepository;
import br.com.taina.repository.PessoaRepository;
import br.com.taina.validation.contato.ContatoValidation;

/**
 * Serviço responsável por gerenciar as regras de negócio relacionadas a entidade {@link Contato}.
 * Utiliza os repositórios {@link ContatoRepository} e {@link PessoaRepository} para realizar operações de CRUD
 * e validações associadas aos contatos.
 * 
 * <p>Além das operações CRUD padrões, este serviço também valida os dados dos contatos antes de persistir ou atualizar
 * na classe {@link br.br.com.taina.validation.contato.ContatoValidation}
 * e realiza o tratamento de exceções específicas do pacote {@link br.com.taina.exception.contato}</p>
 */
@Service
public class ContatoService {
	
    @Autowired
    ContatoRepository contatoRepository;
    
    @Autowired
    PessoaRepository pessoaRepository;
    
    @Autowired
    ContatoValidation contatoValidation;
    
    /**
     * Salva um novo contato, associando-o a uma pessoa existente.
     * 
     * @param contato:  O contato a ser salvo.
     * @return O contato salvo no banco de dados.
     * @throws PessoaNotFoundException Se a pessoa associada ao contato não for encontrada emite uma mensagem
     */
    public Contato save(Contato contato) {    

        // Busca a pessoa associada ao contato passado como parâmetro
        Optional<Pessoa> findPessoa = pessoaRepository.findById(contato.getPessoa().getIdPessoa());
        
        if (findPessoa.isPresent()) {
            // Realiza a validação do contato antes de associá-lo a pessoa
            contatoValidation.validarContato(contato);
            // Associa o contato a pessoa encontrada
            contato.setPessoa(findPessoa.get());
        } else {
            throw new PessoaNotFoundException("Pessoa com ID " + contato.getPessoa().getIdPessoa() + " não encontrada");
        }
        
        try {
            return contatoRepository.save(contato);
        } catch (Exception e) {
            // Lança uma exceção em caso de erro no servidor
        	throw new ErroServidorException(e.getMessage());
        }
    }

    /**
     * Busca um contato pelo ID e retorna um DTO com os dados do contato.
     * 
     * @param id O ID do contato a ser buscado.
     * @return O DTO contendo os dados do contato.
     * @throws ContatoNotFoundException Se o contato com o ID especificado não for encontrado.
     */
    public ContatoDTO findById(Long id) {

        Optional<Contato> contatoOpt = contatoRepository.findById(id);

        Contato contato = new Contato();
        
        if (contatoOpt.isPresent()) {
            // Se encontrado, obtém o Contato encontrado no optional
            contato = contatoOpt.get();
        } else {
            throw new ContatoNotFoundException("Contato com ID " + id + " não encontrado");
        }
        
        try {
            // Cria e retorna o DTO com as informações do contato
            return new ContatoDTO(contato.getIdContato(), contato.getTipoContato(), contato.getContato());
        } catch (Exception e) {
        	throw new ErroServidorException(e.getMessage());
        }
    }


    /**
     * Busca todos os contatos associados a uma pessoa específica, dado o ID da pessoa.
     * 
     * @param idPessoa O ID da pessoa a ser buscada.
     * @return Uma lista de contatos associados à pessoa.
     * @throws PessoaNotFoundException Se a pessoa com o ID especificado não for encontrada.
     */
    ///////Vamos refatorar isso ...
    public List<Contato> findAllByPessoaId(Long idPessoa) {
    	
        Optional<Pessoa> findPessoa = pessoaRepository.findById(idPessoa);
        
        if (findPessoa.isPresent()) {
            return contatoRepository.findByPessoa(findPessoa.get());
        } else {
            throw new PessoaNotFoundException("Pessoa com ID " +idPessoa+ "não encontrada");
        }
    }

    /**
     * Atualiza as informações de um contato existente, dado seu ID e os novos dados do contato.
     * 
     * @param id O ID do contato a ser atualizado.
     * @param contato Os novos dados do contato.
     * @return O contato atualizado.
     * @throws ContatoNotFoundException Se o contato com o ID especificado não for encontrado.
     */
    public Contato update(Long id, Contato contato) {
       
        Optional<Contato> findContato = contatoRepository.findById(id);
        
        Contato updContato = new Contato();
        
        if (findContato.isPresent()) {
            // Realiza a validação do contato antes de atualizar
            contatoValidation.validarContato(contato);
            
            // Se o contato for encontrado, pega a instância do banco e atualiza os dados
            updContato = findContato.get();
            updContato.setTipoContato(contato.getTipoContato()); 
            updContato.setContato(contato.getContato());
        } else {
            throw new ContatoNotFoundException("Contato com ID " + id + " não encontrado");
        }

        try {
            return contatoRepository.save(updContato); 
        } catch(Exception e) {
            throw new ErroServidorException(e.getMessage());
        }
    }


    /**
     * Deleta um contato do banco de dados, dado o seu ID.
     * 
     * @param id O ID do contato a ser deletado.
     * @throws ContatoNotFoundException Se o contato com o ID especificado não for encontrado para exclusão.
     */
    public void delete(Long id) {
    	
        Optional<Contato> contatoOpt = contatoRepository.findById(id);
        
        if (contatoOpt.isPresent()) {
            try {
                contatoRepository.deleteById(id);
            } catch (Exception e) {
            	throw new ErroServidorException(e.getMessage());
            }
        } else {
            throw new ContatoNotFoundException("Contato com ID " + id + " não encontrado para exclusão.");
        }
    }
}
