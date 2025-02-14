package br.com.taina.validation.pessoa;

import br.com.taina.model.Pessoa;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.taina.enums.Estados;
import br.com.taina.exception.pessoa.CepInvalidoException;
import br.com.taina.exception.pessoa.CidadeInvalidaException;
import br.com.taina.exception.pessoa.NomeInvalidoException;
import br.com.taina.exception.pessoa.NomeNuloOuVazioException;
import br.com.taina.exception.pessoa.UfInvalidoException;

@Component
public class PessoaValidation {

    private static final String regexLetrasEspacos = "^[A-Za-záàãâéèêíóôúçÁÀÂÉÊÍÓÔÚÇ\\s]+$";
    private static final String regexCep = "^[0-9]{5}-?[0-9]{3}$";

    // Método de validação para nome
    private boolean isNomeInvalido(String nome) {
        return !Pattern.matches(regexLetrasEspacos, nome);
    }
    private boolean isNomeNuloOuVazio(String nome) {
    	return nome == null || nome.trim().isEmpty(); 
    }
    
    private boolean isCepInvalido(String cep) {
        return cep != null && !Pattern.matches(regexCep, cep);  // Só valida se o CEP não for nulo
    }
    
    private boolean isCidadeValida(String cidade) {
    	return cidade != null && !Pattern.matches(regexLetrasEspacos, cidade);
    }
    
    private boolean isUfValido(String uf) {
        try {
            return Estados.valueOf(uf.toUpperCase()) != null; // Valida no Enum
        } catch (IllegalArgumentException e) {
            return false; // Se não existir, retorna falso
        }
    }
    
    public void validarPessoa(Pessoa pessoa) {
        // Verifica os erros e lança exceções específicas

    	if(isNomeNuloOuVazio(pessoa.getNome())){
    		throw new NomeNuloOuVazioException("Campo vazio ou nulo não permitido! Insira um nome.");
    	}
        if (isNomeInvalido(pessoa.getNome())) {
            throw new NomeInvalidoException("Nome inválido! Deve conter apenas letras.");
        }

        if (isCidadeValida(pessoa.getCidade())) {
            throw new CidadeInvalidaException("Cidade inválida! Deve conter apenas letras.");
        }

        if (isCepInvalido(pessoa.getCep())) {
            throw new CepInvalidoException("CEP inválido! O formato correto é XXXXXXXX ou XXXXX-XXX.");
        }

        if (pessoa.getUf() != null && !isUfValido(pessoa.getUf().name())) {
            throw new UfInvalidoException("UF inválido! Digite um formato válido, exemplo: SP.");
        }
    }
}
