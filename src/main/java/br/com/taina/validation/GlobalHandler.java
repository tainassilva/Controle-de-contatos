package br.com.taina.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.taina.exception.CampoVazioException;
import br.com.taina.exception.ErroServidorException;
import br.com.taina.exception.FormatoInvalidoException;
import br.com.taina.exception.IdNotFoundException;
import br.com.taina.exception.NotNullException;

/**
 * Classe responsável por capturar e tratar exceções globalmente na aplicação.
 * O {@link GlobalHandler} usa a anotação {@link ControllerAdvice} para interagir com o Spring Framework,
 * fornecendo um ponto centralizado para o tratamento de exceções em toda a aplicação.
 * Este handler intercepta exceções do tipo {@link ErroServidorException} e retorna uma resposta
 * adequada ao cliente, informando sobre o erro ocorrido.
 */
@ControllerAdvice(basePackages = {
	    "br.com.taina.controller",  // Pacote onde estão os controladores
	    "br.com.taina.service",     // Pacote onde estão os serviços
	    "br.com.taina.validation",  // Pacote onde estão as validações
	    "br.com.taina.exception"    // Pacote onde estão as exceções, caso precise
	})
public class GlobalHandler {

    /**
     * Método que trata a exceção {@link ErroServidorException} e retorna uma resposta HTTP adequada.
     * 
     * @param ex A exceção que foi gerada no processo.
     * @return A resposta HTTP contendo o código de erro (500) e a mensagem associada à exceção.
     * A resposta é retornada com status 500 (erro interno do servidor).
     */
    @ExceptionHandler(ErroServidorException.class)
    public ResponseEntity<ErrorResponse> handleErroServidor(ErroServidorException ex) {
        ErrorResponse error = new ErrorResponse(500, ex.getMessage());
        return ResponseEntity.status(500).body(error);
    }
    
    /**
     * Método que trata a exceção {@link FormatoInvalidoException} e retorna uma resposta HTTP adequada.
     * 
     * @param ex A exceção que foi gerada no processo.
     * @return A resposta HTTP contendo o código de erro (500) e a mensagem associada à exceção.
     * A resposta é retornada com status 500 (erro interno do servidor).
     */
    @ExceptionHandler(FormatoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleFormatoInvalido(FormatoInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(422, ex.getMessage());
        return ResponseEntity.status(422).body(error);
    }
    
    /**
     * Método que trata a exceção {@link FormatoInvalidoException} e retorna uma resposta HTTP adequada.
     * 
     * @param ex A exceção que foi gerada no processo.
     * @return A resposta HTTP contendo o código de erro (500) e a mensagem associada à exceção.
     * A resposta é retornada com status 500 (erro interno do servidor).
     */
    @ExceptionHandler(CampoVazioException.class)
    public ResponseEntity<ErrorResponse> handleCampoVazio(CampoVazioException ex) {
        ErrorResponse error = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.status(400).body(error);
    }
    /**
     * Método que trata a exceção {@link FormatoInvalidoException} e retorna uma resposta HTTP adequada.
     * 
     * @param ex A exceção que foi gerada no processo.
     * @return A resposta HTTP contendo o código de erro (500) e a mensagem associada à exceção.
     * A resposta é retornada com status 500 (erro interno do servidor).
     */
    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<ErrorResponse> handleCNotNull(NotNullException ex) {
        ErrorResponse error = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.status(400).body(error);
    }
    
    /**
     * Método que trata a exceção {@link FormatoInvalidoException} e retorna uma resposta HTTP adequada.
     * 
     * @param ex A exceção que foi gerada no processo.
     * @return A resposta HTTP contendo o código de erro (500) e a mensagem associada à exceção.
     * A resposta é retornada com status 500 (erro interno do servidor).
     */
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIdNotFound(IdNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(404).body(error);
    }

}
