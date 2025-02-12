package br.com.taina.exception.contato;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.taina.exception.ErrorResponse;


@RestControllerAdvice
public class ContatoExceptionHandler {

	@ExceptionHandler(ContatoNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleContatoNaoEncontrado(ContatoNotFoundException ex){
		ErrorResponse error = new ErrorResponse(400, "Erro: " + ex.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
}
