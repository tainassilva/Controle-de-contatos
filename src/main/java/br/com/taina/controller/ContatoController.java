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

import br.com.taina.model.Contato;
import br.com.taina.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

	@Autowired
	ContatoService contatoService;
	
	@PostMapping
	@Operation(summary= "Este endpoint é para salvar um contato à uma pessoa")
    public ResponseEntity<Contato> save(@RequestBody Contato contato) {
       
        Contato salvarContato = contatoService.save(contato);

        if (salvarContato != null) {
            return ResponseEntity.status(201).body(salvarContato);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
	
	@GetMapping("/{id}")
	@Operation(summary= "Este endpoint é para consultar um contato por ID")
	public ResponseEntity<Optional<Contato>> findById(@PathVariable Long id){
		Optional<Contato> findContato = contatoService.findById(id);
		if(findContato == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(findContato); 
	}
	
	@GetMapping("pessoa/{id}")
	@Operation(summary= "Este endpoint é para listar todos os contato de uma pessoa")
	public ResponseEntity<List<Contato>> findAllPessoa(@PathVariable Long id) {
	    List<Contato> findContato = contatoService.findAllPessoa(id);
	  
	    if (findContato.isEmpty()) {
	        return ResponseEntity.noContent().build(); 
	    }
	    
	    return ResponseEntity.ok(findContato);
	}


	
	@PutMapping("/{id}")
	@Operation(summary = "Este endpoint é para alterar um contato existente")
	public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato contato) {
	    Contato updContato = contatoService.update(id, contato);
	    
	    if (updContato == null) {
	        return ResponseEntity.notFound().build(); // Retorna 404 se o contato não for encontrado
	    }
	    
	    return ResponseEntity.ok(updContato);
	}

	@DeleteMapping("/{id}")
	@Operation(summary= "Este endpoint deleta um contato por ID")
	public ResponseEntity<?> delete(@PathVariable Long id){
		contatoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}
