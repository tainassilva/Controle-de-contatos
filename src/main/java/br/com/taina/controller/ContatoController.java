package br.com.taina.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.taina.dto.ContatoDTO;
import br.com.taina.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Controlador responsável pelo gerenciamento de contatos de uma pessoa.
 * Fornece endpoints para criação, consulta, atualização, listagem e remoção de contatos.
 * 
 * Os status de erro HTTP são tratados na classe {@link br.com.taina.validation.contato.ContatoHandler},
 * que, caso sejam encontrados dados inadequados, utiliza as validações definidas na classe 
 * {@link br.com.taina.validation.ContatoValidation}, lançando exceções personalizadas do 
 * pacote {@link br.com.taina.validation.exception.contato}.
 */
@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    /**
     * Salva um novo contato para uma pessoa.
     *
     * @param contatoDTO Objeto contendo as informações do contato a ser salvo.
     * @return ResponseEntity contendo o contato salvo e o status 201 (Created).
     * 
     */
    @PostMapping
    @Operation(summary = "Salva um novo contato para uma pessoa.")
    public ResponseEntity<ContatoDTO> save(@RequestBody ContatoDTO contatoDTO) {
        ContatoDTO salvarContato = contatoService.save(contatoDTO);
        return ResponseEntity.status(201).body(salvarContato);
    }

    /**
     * Consulta um contato pelo ID.
     *
     * @param id Identificador do contato a ser consultado.
     * @return ResponseEntity contendo o contato encontrado ou HTTP status 404 se não for encontrado.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Consulta um contato pelo ID.")
    public ResponseEntity<ContatoDTO> findById(@PathVariable Long id) {
        ContatoDTO contatoDTO = contatoService.findById(id);
        return ResponseEntity.ok(contatoDTO);
    }

    /**
     * Lista todos os contatos de uma pessoa pelo seu ID.
     *
     * @param idPessoa Identificador da pessoa cujos contatos serão listados.
     * @return ResponseEntity contendo a lista de contatos encontrados.
     */
    @GetMapping("pessoa/{idPessoa}")
    @Operation(summary = "Lista todos os contatos de uma pessoa por ID.")
    public ResponseEntity<List<ContatoDTO>> findAllContactsPessoas(@PathVariable Long idPessoa) {
        List<ContatoDTO> findContato = contatoService.findAllByPessoaId(idPessoa);
        return ResponseEntity.ok(findContato);
    }

    /**
     * Atualiza um contato existente pelo ID.
     *
     * @param id Identificador do contato a ser atualizado.
     * @param contatoDTO Objeto contendo as novas informações do contato.
     * @return ResponseEntity contendo o contato atualizado ou HTTP status 404 caso não seja encontrado.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um contato existente por ID.")
    public ResponseEntity<ContatoDTO> update(@PathVariable Long id, @RequestBody ContatoDTO contatoDTO) {
        ContatoDTO updContato = contatoService.update(id, contatoDTO);
        return ResponseEntity.ok(updContato);
    }

    /**
     * Deleta um contato pelo ID.
     *
     * @param id Identificador do contato a ser removido.
     * @return ResponseEntity com HTTP status 204 (No Content) se a remoção for bem-sucedida.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um contato por ID.")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
