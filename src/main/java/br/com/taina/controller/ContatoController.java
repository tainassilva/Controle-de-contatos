package br.com.taina.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.taina.dto.ContatoDTO;
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

  
            return ResponseEntity.status(201).body(salvarContato);
   
    }
	
	@GetMapping("/{id}")
	@Operation(summary= "Este endpoint é para consultar um contato por ID")
	public ResponseEntity<ContatoDTO> findById(@PathVariable Long id) {
	    ContatoDTO contatoDTO = contatoService.findById(id);
	    return ResponseEntity.ok(contatoDTO); 
	}

	
	@GetMapping("pessoa/{id}")
	@Operation(summary= "Este endpoint é para listar todos os contato de uma pessoa")
	public ResponseEntity<List<Contato>> findAllPessoa(@PathVariable Long id) {
	    List<Contato> findContato = contatoService.findAllPessoa(id);
	  
	    return ResponseEntity.ok(findContato);
	}


	
	@PutMapping("/{id}")
	@Operation(summary = "Este endpoint é para alterar um contato existente")
	public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato contato) {
	    Contato updContato = contatoService.update(id, contato);
	   
	    return ResponseEntity.ok(updContato);
	}

	@DeleteMapping("/{id}")
	@Operation(summary= "Este endpoint deleta um contato por ID")
	public ResponseEntity<?> delete(@PathVariable Long id){
		contatoService.delete(id);
		return ResponseEntity.noContent().build();
}
}
