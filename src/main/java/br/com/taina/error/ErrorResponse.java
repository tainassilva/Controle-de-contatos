package br.com.taina.error;

/**
 * Classe que representa uma resposta de erro padronizada.
 * Contém um código de status HTTP e uma mensagem associada ao erro.
 */
public class ErrorResponse {
    
    private int statusCode;
    
    private String message;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
