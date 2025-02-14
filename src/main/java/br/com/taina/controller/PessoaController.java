package br.com.taina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.taina.dto.PessoaMalaDiretaDTO;
import br.com.taina.model.Pessoa;
import br.com.taina.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Controlador responsável pelo gerenciamento de pessoas.
 * Fornece endpoints para criação, consulta, atualização, listagem e remoção de pessoas.
 * 
 * Os status de erro HTTP são tratados na classe {@link br.com.taina.validation.pessoa.PessoaHandler},
 * que caso sejam encontrados dados inadequados, utiliza as validações definidas na classe 
 * {@link br.com.taina.validation.pessoa.PessoaValidation}, lançando exceções personalizadas do 
 * pacote {@link br.com.taina.validation.exception.pessoa}.
 */

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {
    
    @Autowired
    private PessoaService pessoaService;

    /**
     * Cadastra uma nova pessoa.
     *
     * @param pessoa Objeto contendo as informações da pessoa a ser cadastrada.
     * @return ResponseEntity contendo a pessoa cadastrada e o status 201 (Created).
     * 
     * Em caso de erros HTTP, pode ser devido a falhas no servidor ou validações definidas 
     * na classe {@link br.com.taina.validation.pessoa.PessoaValidation}.
     */
    @PostMapping
    @Operation(summary = "Cadastro de uma nova pessoa")
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
        Pessoa pessoaCadastrada = pessoaService.save(pessoa);
        return ResponseEntity.status(201).body(pessoaCadastrada);
    }

    /**
     * Lista todas as pessoas cadastradas.
     *
     * @return ResponseEntity contendo a lista de pessoas cadastradas
     */
    @GetMapping
    @Operation(summary = "Lista todas as pessoas cadastradas")
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> pessoas = pessoaService.findAll();
        return ResponseEntity.ok(pessoas);
    }

    /**
     * Consulta uma pessoa por ID.
     *
     * @param id Identificador da pessoa a ser consultada.
     * @return ResponseEntity contendo a pessoa encontrada ou HTTP status 404 se não for encontrada.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Consulta uma pessoa pelo ID")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    /**
     * Exibe uma pessoa por ID, com informações para mala direta.
     *
     * @param id Identificador da pessoa a ser consultada.
     * @return ResponseEntity contendo as informações para mala direta da pessoa.
     */
    @GetMapping("maladireta/{id}")
    @Operation(summary = "Exibe uma pessoa com informações para mala direta")
    public ResponseEntity<PessoaMalaDiretaDTO> getPessoaById(@PathVariable Long id) {
        PessoaMalaDiretaDTO pessoaMalaDiretaDTO = pessoaService.findPessoaById(id);
        return ResponseEntity.ok(pessoaMalaDiretaDTO);
    }

    /**
     * Atualiza as informações de uma pessoa existente.
     *
     * @param id Identificador da pessoa a ser atualizada.
     * @param pessoa Objeto contendo as novas informações da pessoa.
     * @return ResponseEntity contendo a pessoa atualizada ou status 404 se não for encontrada.
     * 
     * Em caso de erros HTTP, pode ser devido a falhas no servidor ou validações definidas na 
     * classe {@link br.com.taina.validation.pessoa.PessoaValidation}, que são as mesmas aplicadas 
     * ao salvar uma pessoa.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza as informações de uma pessoa")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.update(id, pessoa);

        return ResponseEntity.ok(pessoaAtualizada);
    }

    /**
     * Deleta uma pessoa pelo ID.
     *
     * @param id Identificador da pessoa a ser removida.
     * @return ResponseEntity com status 204 (No Content) se a remoção for bem-sucedida.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma pessoa pelo ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
