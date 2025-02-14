package br.com.taina.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.taina.exception.ErroServidorException;

/**
 * Classe responsável por capturar e tratar exceções globalmente na aplicação.
 * O {@link GlobalHandler} usa a anotação {@link ControllerAdvice} para interagir com o Spring Framework,
 * fornecendo um ponto centralizado para o tratamento de exceções em toda a aplicação.
 * Este handler intercepta exceções do tipo {@link ErroServidorException} e retorna uma resposta
 * adequada ao cliente, informando sobre o erro ocorrido.
 */
@ControllerAdvice
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
}
