package br.com.taina.validation.pessoa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.taina.exception.pessoa.CepInvalidoException;
import br.com.taina.exception.pessoa.CidadeInvalidaException;
import br.com.taina.exception.pessoa.NomeInvalidoException;
import br.com.taina.exception.pessoa.NomeNuloOuVazioException;
import br.com.taina.exception.pessoa.PessoaNotFoundException;
import br.com.taina.exception.pessoa.UfInvalidoException;
import br.com.taina.validation.ErrorResponse;

/**
 * Classe responsável por manipular exceções relacionadas a contatos.
 * Captura as exceções específicas e retorna respostas HTTP apropriadas.
 */
@ControllerAdvice
public class PessoaHandler {

    /**
     * Manipula a exceção quando uma pessoa não é encontrada.
     *
     * @param ex A exceção {@link PessoaNotFoundException} lançada quando a pessoa não existe no sistema.
     * @return Um {@link ResponseEntity} contendo o código de status 404 (Not Found) e uma mensagem de erro.
     */
    @ExceptionHandler(PessoaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePessoaNaoEncontrada(PessoaNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(404).body(error);
    }

    /**
     * Manipula a exceção quando o nome da pessoa é nulo ou vazio.
     *
     * @param ex A exceção {@link NomeNuloOuVazioException} lançada quando o nome não é informado.
     * @return Um {@link ResponseEntity} contendo o código de status 400 (Bad Request) e uma mensagem de erro.
     */
    @ExceptionHandler(NomeNuloOuVazioException.class)
    public ResponseEntity<ErrorResponse> handleNomeNuloOuVazio(NomeNuloOuVazioException ex) {
        ErrorResponse error = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Manipula a exceção quando o nome da pessoa é inválido.
     *
     * @param ex A exceção {@link NomeInvalidoException} lançada quando o nome contém caracteres inválidos.
     * @return Um {@link ResponseEntity} contendo o código de status 422 (Unprocessable Entity) e uma mensagem de erro.
     */
    @ExceptionHandler(NomeInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleNomeInvalido(NomeInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(422, ex.getMessage());
        return ResponseEntity.unprocessableEntity().body(error);
    }

    /**
     * Manipula a exceção quando a cidade informada é inválida.
     *
     * @param ex A exceção {@link CidadeInvalidaException} lançada quando a cidade não segue um formato adequado.
     * @return Um {@link ResponseEntity} contendo o código de status 400 (Bad Request) e uma mensagem de erro.
     */
    @ExceptionHandler(CidadeInvalidaException.class)
    public ResponseEntity<ErrorResponse> handleCidadeInvalida(CidadeInvalidaException ex) {
        ErrorResponse error = new ErrorResponse(400,  ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Manipula a exceção quando o CEP informado é inválido.
     *
     * @param ex A exceção {@link CepInvalidoException} lançada quando o CEP informado não é válido.
     * @return Um {@link ResponseEntity} contendo o código de status 422 (Unprocessable Entity) e uma mensagem de erro.
     */
    @ExceptionHandler(CepInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleCepInvalido(CepInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(422, ex.getMessage());
        return ResponseEntity.unprocessableEntity().body(error);
    }

    /**
     * Manipula a exceção quando a UF informada é inválida.
     *
     * @param ex A exceção {@link UfInvalidoException} lançada quando a UF não está dentro dos estados válidos do Brasil.
     * @return Um {@link ResponseEntity} contendo o código de status 400 (Bad Request) e uma mensagem de erro.
     */
    @ExceptionHandler(UfInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleUfInvalido(UfInvalidoException ex) {
        ErrorResponse error = new ErrorResponse(400, ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
