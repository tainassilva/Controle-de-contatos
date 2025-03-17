package br.com.taina.error;

import br.com.taina.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

	// Trata exceções de validação (@NotBlank, @Pattern, @Size, etc)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getAllErrors().stream()
				.map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
				.collect(Collectors.toList());
		ErrorResponse errorResponse = new ErrorResponse(400, errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	// Trata ConstraintViolationException (validações diretas nos métodos, tipo @Cep e @UF)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
		List<String> errors = ex.getConstraintViolations().stream()
				.map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());
		ErrorResponse errorResponse = new ErrorResponse(400, errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(ErroServidorException.class)
	public ResponseEntity<ErrorResponse> handleErroServidor(ErroServidorException ex) {
		ErrorResponse error = new ErrorResponse(500, Collections.singletonList(ex.getMessage()));
		return ResponseEntity.status(500).body(error);
	}

	@ExceptionHandler(FormatoInvalidoException.class)
	public ResponseEntity<ErrorResponse> handleFormatoInvalido(FormatoInvalidoException ex) {
		ErrorResponse error = new ErrorResponse(422, Collections.singletonList(ex.getMessage()));
		return ResponseEntity.status(422).body(error);
	}

	@ExceptionHandler(CampoVazioException.class)
	public ResponseEntity<ErrorResponse> handleCampoVazio(CampoVazioException ex) {
		ErrorResponse error = new ErrorResponse(400, Collections.singletonList(ex.getMessage()));
		return ResponseEntity.status(400).body(error);
	}

	@ExceptionHandler(CampoNotNullException.class)
	public ResponseEntity<ErrorResponse> handleCampoNotNull(CampoNotNullException ex) {
		ErrorResponse error = new ErrorResponse(400, Collections.singletonList(ex.getMessage()));
		return ResponseEntity.status(400).body(error);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleIdNotFound(IdNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(404, Collections.singletonList(ex.getMessage()));
		return ResponseEntity.status(404).body(error);
	}

	@ExceptionHandler(NenhumaAlteracaoException.class)
	public ResponseEntity<ErrorResponse> handleNenhumaAlteracao(NenhumaAlteracaoException ex) {
		ErrorResponse error = new ErrorResponse(400, Collections.singletonList(ex.getMessage()));
		return ResponseEntity.status(400).body(error);
	}

	@ExceptionHandler(NadaParaListarException.class)
	public ResponseEntity<ErrorResponse> handleNadaParaListar(NadaParaListarException ex) {
		ErrorResponse error = new ErrorResponse(400, Collections.singletonList(ex.getMessage()));
		return ResponseEntity.status(400).body(error);
	}
}
