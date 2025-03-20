package br.com.taina.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taina.dto.contato.ContatoDTO;
import br.com.taina.enums.TipoContato;
import br.com.taina.exception.ErroServidorException;
import br.com.taina.exception.IdNotFoundException;
import br.com.taina.exception.NadaParaListarException;
import br.com.taina.exception.CampoNotNullException;
import br.com.taina.entity.Contato;
import br.com.taina.entity.Pessoa;
import br.com.taina.repository.ContatoRepository;
import br.com.taina.repository.PessoaRepository;
import br.com.taina.validation.ContatoValidation;

/**
 * Serviço responsável por gerenciar as regras de negócio relacionadas a entidade {@link Contato}.
 * Utiliza os repositórios {@link ContatoRepository} e {@link PessoaRepository} para realizar operações de CRUD
 * e validações associadas aos contatos.
 * 
 * <p>Além das operações CRUD padrões, este serviço também valida os dados dos contatos antes de persistir ou atualizar
 * na classe {@link br.com.taina.validation.ContatoValidation}
 * e realiza o tratamento de exceções específicas do pacote {@link br.com.taina.exception}.</p>
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
     * @param contatoDTO O contato a ser salvo.
     * @return O contato salvo no banco de dados.
     */
    public ContatoDTO save(ContatoDTO contatoDTO) {  
    	
    	if (contatoDTO.getIdPessoa() == null) {
            throw new CampoNotNullException("Erro! O idPessoa não pode ser nulo. Insira um id válido!");
        }
    	
        Pessoa pessoa = pessoaRepository.findById(contatoDTO.getIdPessoa())
                .orElseThrow(() -> new IdNotFoundException("Pessoa com ID " + contatoDTO.getIdPessoa() + " não encontrada"));

        contatoValidation.validarContato(contatoDTO);
        
        Contato novoContato = new Contato();
        novoContato.setTipoContato(TipoContato.valueOf(contatoDTO.getTipoContato().trim().toUpperCase()));
        novoContato.setContato(contatoDTO.getContato());
        novoContato.setPessoa(pessoa);
        pessoa.getContatos().add(novoContato);

        try {
            novoContato = contatoRepository.save(novoContato);
            pessoaRepository.save(pessoa);
            return new ContatoDTO(novoContato.getId(), novoContato.getTipoContato().name(), novoContato.getContato(), novoContato.getPessoa().getId());
        } catch (Exception e) {
            throw new ErroServidorException(e.getMessage());
        }
            }

    /**
     * Busca um contato pelo ID e retorna um DTO com os dados do contato.
     * 
     * @param id O ID do contato a ser buscado.
     * @return O DTO contendo os dados do contato.
     */
    public ContatoDTO findById(Long id) {
        if (id == null) {
            throw new CampoNotNullException("Erro! Campo ID não pode ser nulo.");
        }

        try {
            Contato contato = contatoRepository.findById(id)
                    .orElseThrow(() -> new IdNotFoundException("Contato com ID " + id + " não encontrado"));

            return new ContatoDTO(contato.getId(), contato.getTipoContato().name(), contato.getContato(), contato.getPessoa().getId());
        }catch (IdNotFoundException e) {
            throw new IdNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new ErroServidorException(e.getMessage());
        }
    }


    /**
     * Busca todos os contatos associados a uma pessoa específica.
     * 
     * @param idPessoa O ID da pessoa a ser buscada.
     * @return Lista de DTOs de contatos associados à pessoa.
     */
    public List<ContatoDTO> findAllByPessoaId(Long idPessoa) {
        if (idPessoa == null) {
            throw new CampoNotNullException("Erro! Campo ID não pode ser nulo.");
        }

        pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new IdNotFoundException("Pessoa com ID " + idPessoa + " não encontrada"));

        try {
            List<ContatoDTO> contatos = contatoRepository.findContatosPessoaById(idPessoa).stream()
                    .map(contato -> new ContatoDTO(contato.getId(), contato.getTipoContato().name(), contato.getContato(), contato.getPessoa().getId()))
                    .collect(Collectors.toList());

            if (contatos.isEmpty()) {
                throw new NadaParaListarException("Nenhum contato encontrado para a pessoa com ID " + idPessoa);
            }

            return contatos;
        } catch (NadaParaListarException e) {
            throw new NadaParaListarException(e.getMessage()); 
        } catch (Exception e) {
            throw new ErroServidorException(e.getMessage()); 
        }
    }


    /**
     * Atualiza as informações de um contato existente.
     * 
     * @param id O ID do contato a ser atualizado.
     * @param contatoDTO Os novos dados do contato.
     * @return O DTO do contato atualizado.
     */
    public ContatoDTO update(Long id, ContatoDTO contatoDTO) {
        if (id == null) {
            throw new CampoNotNullException("Erro! Campo ID não pode ser nulo.");
        }
        
        Contato contatoAtualizado = contatoRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Contato com ID " + id + " não encontrado"));
        
        contatoValidation.validarContato(contatoDTO);
        
        contatoAtualizado.setTipoContato(TipoContato.valueOf(contatoDTO.getTipoContato().trim().toUpperCase()));
        contatoAtualizado.setContato(contatoDTO.getContato());

        try {
            contatoAtualizado = contatoRepository.save(contatoAtualizado);
            return new ContatoDTO(contatoAtualizado.getId(), contatoAtualizado.getTipoContato().name(), contatoAtualizado.getContato(), contatoAtualizado.getPessoa().getId());

        } catch (Exception e) {
            throw new ErroServidorException(e.getMessage());
        }
        
    }

    /**
     * Deleta um contato do banco de dados pelo ID.
     * 
     * @param id O ID do contato a ser deletado.
     */
    public void delete(Long id) {
        if (id == null) {
            throw new CampoNotNullException("Erro! Campo ID não pode ser nulo.");
        }
        
        contatoRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Contato com ID " + id + " não encontrado para exclusão."));
        
        try {
            contatoRepository.deleteById(id);
        } catch (Exception e) {
            throw new ErroServidorException(e.getMessage());
        }
    }
}