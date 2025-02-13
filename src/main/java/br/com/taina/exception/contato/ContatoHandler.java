package br.com.taina.exception.contato;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.taina.exception.ErrorResponse;

@ControllerAdvice
public class ContatoHandler {

	
	@ExceptionHandler(ContatoNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleContatoNaoEncontrado(ContatoNotFoundException ex){
		ErrorResponse error = new ErrorResponse(400, "Erro: " + ex.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	@ExceptionHandler(TelefoneFormatoInvalidoException.class)
	public ResponseEntity<ErrorResponse> handleTelefoneFormatoInvalido(TelefoneFormatoInvalidoException ex){
		ErrorResponse error = new ErrorResponse(400, "Erro: " + ex.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	
	 @ExceptionHandler(ContatoNuloOuVazioException.class)
	    public ResponseEntity<ErrorResponse> handleContatoNuloOuVazio(ContatoNuloOuVazioException ex) {
	        ErrorResponse error = new ErrorResponse(400, "Erro: " + ex.getMessage());
	        return ResponseEntity.badRequest().body(error);
	    }
	 
    @ExceptionHandler(EmailFormatoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleEmailFormatoInvalido(EmailFormatoInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(400, "Erro: " + ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(TipoContatoNuloException.class)
    public ResponseEntity<ErrorResponse> handleContatoNulo(TipoContatoNuloException ex) {
        ErrorResponse error = new ErrorResponse(400, "Erro: " + ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(SemContatoAssociadoException.class)
    public ResponseEntity<ErrorResponse> handleSemContatoAssociado(SemContatoAssociadoException ex) {
        ErrorResponse error = new ErrorResponse(204, "Erro: " + ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
    
}
