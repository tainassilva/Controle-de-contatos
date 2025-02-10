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

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

	@Autowired
	ContatoService contatoService;
	
	@PostMapping
    public ResponseEntity<Contato> save(@RequestBody Contato contato) {
        // Chama o serviço para salvar o contato
        Contato savedContato = contatoService.save(contato);

        if (savedContato != null) {
            // Se o contato for salvo com sucesso, retorna o contato com status 201 Created
            return ResponseEntity.status(201).body(savedContato);
        } else {
            // Se não conseguir salvar (por exemplo, pessoa não encontrada), retorna erro 400 Bad Request
            return ResponseEntity.badRequest().build();
        }
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Contato>> findById(@PathVariable Long id){
		Optional<Contato> findContato = contatoService.findById(id);
		if(findContato == null)
			return ResponseEntity.badRequest().build(); //400
		return ResponseEntity.ok(findContato); //200
	}
	
	@GetMapping("pessoa/{id}")
	public ResponseEntity<List<Contato>> findAllPessoa(@PathVariable Long id) {
	    List<Contato> findContato = contatoService.findAllPessoa(id);
	    
	    // Verificar se a lista está vazia
	    if (findContato.isEmpty()) {
	        // Retorna 204 No Content quando não encontrar nenhum contato
	        return ResponseEntity.noContent().build(); // 204
	    }
	    
	    // Retorna 200 OK com a lista de contatos
	    return ResponseEntity.ok(findContato); // 200
	}


	
	@PutMapping
	public ResponseEntity<Contato> update(@RequestBody Contato contato){
		Contato updContato = contatoService.update(contato);
		if(updContato == null)
			return ResponseEntity.badRequest().build(); //400
		return ResponseEntity.ok(updContato); //200
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		contatoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
}
}
