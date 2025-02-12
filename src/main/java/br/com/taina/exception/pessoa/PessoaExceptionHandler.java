package br.com.taina.exception.pessoa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.taina.exception.ErrorResponse;

@RestControllerAdvice
public class PessoaExceptionHandler {

	
	@ExceptionHandler(PessoaNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePessoaNaoEncontrada(PessoaNotFoundException ex){
		ErrorResponse error = new ErrorResponse(400, "Erro: " + ex.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
    @ExceptionHandler(NomeInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleNomeInvalido(NomeInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(400, "Erro: " + ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CidadeInvalidaException.class)
    public ResponseEntity<ErrorResponse> handleCidadeInvalida(CidadeInvalidaException ex) {
        ErrorResponse error = new ErrorResponse(400, "Erro: " + ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CepInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleCepInvalido(CepInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(400, "Erro: " + ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UfInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleUfInvalido(UfInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(400, "Erro: " + ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
