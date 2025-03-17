package br.com.taina.error;

import java.util.List;

/**
 * Classe que representa uma resposta de erro padronizada.
 * Contém um código de status HTTP e uma lista de mensagens associadas aos erros.
 */
public class ErrorResponse {
    private int statusCode;
    private List<String> erros;

    public ErrorResponse(int statusCode, List<String> erros) {
        this.statusCode = statusCode;
        this.erros = erros;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
