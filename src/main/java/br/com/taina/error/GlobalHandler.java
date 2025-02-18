package br.com.taina.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.taina.exception.CampoVazioException;
import br.com.taina.exception.ErroServidorException;
import br.com.taina.exception.FormatoInvalidoException;
import br.com.taina.exception.IdNotFoundException;
import br.com.taina.exception.NadaParaListarException;
import br.com.taina.exception.NenhumaAlteracaoException;
import br.com.taina.exception.CampoNotNullException;

/**
 * Classe responsável por capturar e tratar exceções globalmente na aplicação.
 * O {@link GlobalHandler} usa a anotação {@link ControllerAdvice} para interagir com o Spring Framework,
 * fornecendo um ponto centralizado para o tratamento de exceções em toda a aplicação.
 * Este handler intercepta exceções e retorna uma resposta
 * adequada ao cliente, informando sobre o erro ocorrido.
 */
@ControllerAdvice(basePackages = {
	    "br.com.taina.controller",  
	    "br.com.taina.service",    
	    "br.com.taina.validation",  
	    "br.com.taina.exception"    
	})
public class GlobalHandler {

	@ExceptionHandler(ErroServidorException.class)
	public ResponseEntity<ErrorResponse> handleErroServidor(ErroServidorException ex) {
	    ErrorResponse error = new ErrorResponse(500, ex.getMessage());
	    return ResponseEntity.status(500).body(error);
	}

	@ExceptionHandler(FormatoInvalidoException.class)
	public ResponseEntity<ErrorResponse> handleFormatoInvalido(FormatoInvalidoException ex) {
	    ErrorResponse error = new ErrorResponse(422, ex.getMessage());
	    return ResponseEntity.status(422).body(error);
	}

	@ExceptionHandler(CampoVazioException.class)
	public ResponseEntity<ErrorResponse> handleCampoVazio(CampoVazioException ex) {
	    ErrorResponse error = new ErrorResponse(400, ex.getMessage());
	    return ResponseEntity.status(400).body(error);
	}

	@ExceptionHandler(CampoNotNullException.class)
	public ResponseEntity<ErrorResponse> handleIdNotNull(CampoNotNullException ex) {
	    ErrorResponse error = new ErrorResponse(400, ex.getMessage());
	    return ResponseEntity.status(400).body(error);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleIdNotFound(IdNotFoundException ex) {
	    ErrorResponse error = new ErrorResponse(404, ex.getMessage());
	    return ResponseEntity.status(404).body(error);
	}

	@ExceptionHandler(NenhumaAlteracaoException.class)
	public ResponseEntity<ErrorResponse> handleNenhumaAlteracao(NenhumaAlteracaoException ex) {
	    ErrorResponse error = new ErrorResponse(400, ex.getMessage());
	    return ResponseEntity.status(400).body(error);
	}

	@ExceptionHandler(NadaParaListarException.class)
	public ResponseEntity<ErrorResponse> handleNadaParaListar(NadaParaListarException ex) {
	    ErrorResponse error = new ErrorResponse(400, ex.getMessage());
	    return ResponseEntity.status(400).body(error);
	}

}
