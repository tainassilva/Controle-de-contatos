package br.com.taina.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.taina.dto.PessoaMalaDiretaDTO;
import br.com.taina.model.Pessoa;
import br.com.taina.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {
	
	@Autowired
	PessoaService pessoaService;


	@PostMapping 
	@Operation(summary= "Este endpoint é para cadastrar uma pessoa")
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
	Pessoa pessoas = pessoaService.save(pessoa);

		return ResponseEntity.ok(pessoas); 
}

	@GetMapping 
	@Operation(summary= "Este endpoint é para listar todas as pessoas cadastradas.")
	public ResponseEntity<List<Pessoa>> findAll(){
		List<Pessoa> pessoas = pessoaService.findAll();
		if(pessoas == null)
			return ResponseEntity.badRequest().build();
		if(pessoas.size() == 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping("/{id}") 
	@Operation(summary = "Este endpoint é para consultar uma pessoa por ID")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
	    Pessoa pessoa = pessoaService.findById(id); // Aqui você pega a pessoa retornada pelo serviço
	    return ResponseEntity.ok(pessoa); // Retorna a pessoa no corpo da resposta
	}

	
	 @GetMapping("maladireta/{id}")
	 @Operation(summary= "Este endpoint é para exibir um usuário por ID com mala direta.")
	 public ResponseEntity<PessoaMalaDiretaDTO> getPessoaById(@PathVariable Long id) {
	        PessoaMalaDiretaDTO pessoaMalaDiretaDTO = pessoaService.findPessoaById(id);
	        return ResponseEntity.ok(pessoaMalaDiretaDTO);
	    }
	
	 @PutMapping("/{id}")
	 @Operation(summary = "Este endpoint é para atualizar uma pessoa por ID.")
	 public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
	     Pessoa updPessoa = pessoaService.update(id, pessoa);

	     if (updPessoa == null) {
	         return ResponseEntity.notFound().build();
	     }

	     return ResponseEntity.ok(updPessoa);
	 }


	@DeleteMapping("/{id}")
	@Operation(summary= "Este endpoint é para deletar um usuário por id.")
	public ResponseEntity<?> delete(@PathVariable Long id){
		pessoaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
