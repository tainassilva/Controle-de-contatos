package br.com.taina.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taina.dto.ContatoDTO;
import br.com.taina.enums.TipoContato;
import br.com.taina.exception.ErroServidorException;
import br.com.taina.exception.IdNotFoundException;
import br.com.taina.model.Contato;
import br.com.taina.model.Pessoa;
import br.com.taina.repository.ContatoRepository;
import br.com.taina.repository.PessoaRepository;
import br.com.taina.validation.ContatoValidation;

/**
 * Serviço responsável por gerenciar as regras de negócio relacionadas a entidade {@link Contato}.
 * Utiliza os repositórios {@link ContatoRepository} e {@link PessoaRepository} para realizar operações de CRUD
 * e validações associadas aos contatos.
 * 
 * <p>Além das operações CRUD padrões, este serviço também valida os dados dos contatos antes de persistir ou atualizar
 * na classe {@link br.com.taina.validation.br.com.taina.validation.contato.ContatoValidation}
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
    public ContatoDTO save(ContatoDTO contatoDTO) {    

        // Busca a pessoa associada ao contato passado como parâmetro
        Optional<Pessoa> findPessoa = pessoaRepository.findById(contatoDTO.getIdPessoa());

        if (findPessoa.isPresent()) {
            Pessoa pessoa = findPessoa.get(); // Obtém a pessoa

            // Valida o contato
            contatoValidation.validarContato(contatoDTO);

            // Converte para Enum
            TipoContato tipoContato = TipoContato.fromString(contatoDTO.getTipoContato());

            // Cria e configura o novo contato
            Contato novoContato = new Contato();
            novoContato.setTipoContato(tipoContato);
            novoContato.setContato(contatoDTO.getContato());
            novoContato.setPessoa(pessoa);

            // Adiciona o contato à lista da pessoa
            pessoa.getContatos().add(novoContato);

            // Salva no banco de dados
            novoContato = contatoRepository.save(novoContato);
            pessoaRepository.save(pessoa);

            // Retorna o DTO do contato salvo
            return new ContatoDTO(novoContato.getTipoContato().name(), novoContato.getContato(), novoContato.getPessoa().getId());
        } else {
            throw new IdNotFoundException("Pessoa com ID " + contatoDTO.getIdPessoa() + " não encontrada");
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

        if (contatoOpt.isPresent()) {
            Contato contato = contatoOpt.get(); // Obtém o contato do Optional

            // Cria o DTO e preenche os dados corretamente
            ContatoDTO contatoDTO = new ContatoDTO();
            contatoDTO.setId(contato.getId()); 
            contatoDTO.setTipoContato(contato.getTipoContato().name()); 
            contatoDTO.setContato(contato.getContato());
            contatoDTO.setIdPessoa(contato.getPessoa().getId());

            return contatoDTO; 
        } else {
            throw new IdNotFoundException("Contato com ID " + id + " não encontrado");
        }
    }




    /**
     * Busca todos os contatos associados a uma pessoa específica, dado o ID da pessoa.
     * 
     * @param idPessoa O ID da pessoa a ser buscada.
     * @return Uma lista de contatos associados à pessoa.
     * @throws PessoaNotFoundException Se a pessoa com o ID especificado não for encontrada.
     */
    public List<ContatoDTO> findAllByPessoaId(Long idPessoa) {
        Optional<Pessoa> findPessoa = pessoaRepository.findById(idPessoa);

        if (findPessoa.isPresent()) {
            List<Contato> contatosPessoa = contatoRepository.findByPessoaId(idPessoa); // Buscar contatos no banco
            List<ContatoDTO> contatosDTO = new ArrayList<>();

            for (Contato contato : contatosPessoa) {
                ContatoDTO contatoDTO = new ContatoDTO();
                contatoDTO.setId(contato.getId());
                contatoDTO.setIdPessoa(contato.getPessoa().getId());
                contatoDTO.setContato(contato.getContato());
                contatoDTO.setTipoContato(contato.getTipoContato().name());
                
                contatosDTO.add(contatoDTO); // Adiciona na lista correta
            }

            return contatosDTO;
        } else {
            throw new IdNotFoundException("Pessoa com ID " + idPessoa + " não encontrada");
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
    public ContatoDTO update(Long id, ContatoDTO contatoDTO) {

        Optional<Contato> findContato = contatoRepository.findById(id);

        if (findContato.isPresent()) {
            // Obtém o contato existente
            Contato contatoAtualizado = findContato.get();

            // Valida o contato antes de atualizar
            contatoValidation.validarContato(contatoDTO);

            // Converte para Enum, garantindo que o tipo seja válido
            TipoContato tipoContato = TipoContato.fromString(contatoDTO.getTipoContato());

            // Atualiza os dados do contato
            contatoAtualizado.setTipoContato(tipoContato);
            contatoAtualizado.setContato(contatoDTO.getContato());

            // Salva a atualização no banco de dados
            contatoAtualizado = contatoRepository.save(contatoAtualizado);

            // Retorna um DTO atualizado
            return new ContatoDTO(
                contatoAtualizado.getTipoContato().name(),
                contatoAtualizado.getContato(),
                contatoAtualizado.getPessoa().getId()
            );
        } else {
            throw new IdNotFoundException("Contato com ID " + id + " não encontrado");
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
            throw new IdNotFoundException("Contato com ID " + id + " não encontrado para exclusão.");
        }
    }
}
