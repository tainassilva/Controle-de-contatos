package br.com.taina.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.taina.dto.ContatoDTO;
import br.com.taina.model.Contato;
import br.com.taina.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Controlador responsável pelo gerenciamento de contatos de uma pessoa.
 * Fornece endpoints para criação, consulta, atualização, listagem e remoção de contatos.
 * 
 * Os status de erro HTTP são tratados na classe {@link br.com.taina.validation.contato.ContatoHandler},
 * que caso sejam encontrados dados inadequados, utiliza as validações definidas na classe 
 * {@link br.com.taina.validation.contato.ContatoValidation}, lançando exceções personalizadas do 
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
     * @param contato Objeto contendo as informações do contato a ser salvo.
     * @return ResponseEntity contendo o contato salvo e o status 201 (Created).
     * 
     * Em caso de erros HTTP, pode ser devido a falhas no servidor ou validações definidas 
     * na classe {@link br.com.taina.validation.contato.ContatoValidation}.
     */
    @PostMapping
    @Operation(summary = "Salva um novo contato para uma pessoa")
    public ResponseEntity<Contato> save(@RequestBody Contato contato) {
        Contato salvarContato = contatoService.save(contato);
        return ResponseEntity.status(201).body(salvarContato);
    }

    /**
     * Consulta um contato pelo ID.
     *
     * @param id Identificador do contato a ser consultado.
     * @return ResponseEntity contendo o contato encontrado ou HTTP status 404 se não for encontrado.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Consulta um contato pelo ID")
    public ResponseEntity<ContatoDTO> findById(@PathVariable Long id) {
        ContatoDTO contatoDTO = contatoService.findById(id);
        return ResponseEntity.ok(contatoDTO);
    }

    /**
     * Lista todos os contatos de uma pessoa pelo seu ID.
     *
     * @param id Identificador da pessoa cujos contatos serão listados.
     * @return ResponseEntity contendo a lista de contatos encontrados.
     * 
     * Em caso de erros HTTP, pode ser devido a falhas no servidor ou validações definidas na 
     * classe {@link br.com.taina.validation.contato.ContatoValidation}, que são as mesmas aplicadas 
     * ao salvar um contato.
     */
    @GetMapping("pessoa/{id}")
    @Operation(summary = "Lista todos os contatos de uma pessoa")
    public ResponseEntity<List<Contato>> findAllPessoa(@PathVariable Long id) {
        List<Contato> findContato = contatoService.findAllByPessoaId(id);
        return ResponseEntity.ok(findContato);
    }

    /**
     * Atualiza um contato existente pelo ID.
     *
     * @param id Identificador do contato a ser atualizado.
     * @param contato Objeto contendo as novas informações do contato.
     * @return ResponseEntity contendo o contato atualizado ou HTTP status 404 caso não for encontrado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um contato existente")
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato contato) {
        Contato updContato = contatoService.update(id, contato);
        return ResponseEntity.ok(updContato);
    }

    /**
     * Deleta um contato pelo ID.
     *
     * @param id Identificador do contato a ser removido.
     * @return ResponseEntity com HTTP status 204 (No Content) se a remoção for bem-sucedida
     * @return ResponseEntity contendo o contato atualizado ou HTTP status 404 caso não for encontrado

     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um contato pelo ID")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
