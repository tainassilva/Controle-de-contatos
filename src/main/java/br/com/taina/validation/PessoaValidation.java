package br.com.taina.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.taina.dto.PessoaDTO;
import br.com.taina.enums.Estados;
import br.com.taina.exception.CampoVazioException;
import br.com.taina.exception.FormatoInvalidoException;
import br.com.taina.exception.NotNullException;

@Component
public class PessoaValidation {

    private static final String regexLetrasEspacos = "^[A-Za-záàãâéèêíóôúçÁÀÂÉÊÍÓÔÚÇ\\s]+$";
    private static final String regexCep = "^[0-9]{5}-?[0-9]{3}$";

    // Método de validação para nome
    private boolean isNomeInvalido(String nome) {
        return !Pattern.matches(regexLetrasEspacos, nome);
    }
    private boolean isNomeVazio(String nome) {
    	return nome.trim().isEmpty(); 
    }
    private boolean isNomeNulo(String nome) {
    	return nome == null;
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
    
    public void validarPessoaDTO(PessoaDTO pessoaDTO) {
        // Verifica os erros e lança exceções específicas

    	if(isNomeNulo(pessoaDTO.getNome())){
    		throw new  NotNullException("Campo nulo não permitido! Insira um nome.");
    	}
    	
    	if(isNomeVazio(pessoaDTO.getNome())){
    		throw new CampoVazioException("Campo vazio não permitido! Insira um nome.");
    	}
        if (isNomeInvalido(pessoaDTO.getNome())) {
            throw new FormatoInvalidoException("Nome inválido! Deve conter apenas letras.");
        }

        if (isCidadeValida(pessoaDTO.getCidade())) {
            throw new FormatoInvalidoException("Cidade inválida! Deve conter apenas letras.");
        }

        if (isCepInvalido(pessoaDTO.getCep())) {
            throw new FormatoInvalidoException("CEP inválido! O formato correto é XXXXXXXX ou XXXXX-XXX.");
        }

        if (pessoaDTO.getUf() != null && !isUfValido(pessoaDTO.getUf().name())) {
            throw new FormatoInvalidoException("UF inválido! Digite um formato válido, exemplo: SP.");
        }
    }
}
