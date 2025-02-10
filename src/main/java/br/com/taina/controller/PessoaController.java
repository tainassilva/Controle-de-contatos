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

import br.com.taina.dto.PessoaDTO;
import br.com.taina.model.Pessoa;
import br.com.taina.service.PessoaService;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {
	
	@Autowired
	PessoaService pessoaService;


	@PostMapping 
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
	Pessoa pessoas = pessoaService.save(pessoa);
	if(pessoas == null) {
		return ResponseEntity.badRequest().build(); 
	}else {
		return ResponseEntity.ok(pessoas); 
	}
}
	@GetMapping("/{id}") 
	public ResponseEntity<Optional<Pessoa>> findById(@PathVariable Long id){
		Optional<Pessoa> pessoa= pessoaService.findById(id);
		if(pessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(pessoa); 
		}
	}
	
	@GetMapping 
	public ResponseEntity<List<Pessoa>> findAll(){
		List<Pessoa> pessoas = pessoaService.findAll();
		if(pessoas == null)
			return ResponseEntity.badRequest().build();
		if(pessoas.size() == 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoas);
	}
	 @GetMapping("maladireta/{id}")
	 public ResponseEntity<PessoaDTO> getPessoaById(@PathVariable Long id) {
	        PessoaDTO pessoaDTO = pessoaService.findPessoaById(id);

	        if (pessoaDTO == null) {
	            return ResponseEntity.notFound().build();
	        }

	        return ResponseEntity.ok(pessoaDTO);
	    }
	
	@PutMapping
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa){
		Pessoa updPessoa = pessoaService.update(pessoa);
		if(updPessoa == null) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(updPessoa); 
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		pessoaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
