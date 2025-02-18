package br.com.taina.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.taina.dto.PessoaDTO;
import br.com.taina.enums.Estados;
import br.com.taina.exception.CampoVazioException;
import br.com.taina.exception.FormatoInvalidoException;
import br.com.taina.exception.CampoNotNullException;

/**
 * Classe responsável pela validação dos campos do objeto {@link PessoaDTO}.
 * Contém métodos que validam os campos, garantindo que atendam aos critérios esperados.
 * 
 * As validações incluem:
 * - Nome: Não pode ser nulo, vazio ou conter caracteres inválidos.
 * - CEP: Deve seguir o formato XXXXX-XXX ou XXXXXXXX.
 * - Cidade: Deve conter apenas letras.
 * - UF: Deve ser um estado válido conforme o enum {@link Estados}.
 */
@Component
public class PessoaValidation {

    // Expressões regulares para validação de nome e CEP
    private static final String regexLetrasEspacos = "^[A-Za-záàãâéèêíóôúçÁÀÂÉÊÍÓÔÚÇ\\s]+$";
    private static final String regexCep = "^[0-9]{5}-?[0-9]{3}$";

   
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
    
    
    private boolean isCidadeInValida(String cidade) {
    	return cidade != null && !Pattern.matches(regexLetrasEspacos, cidade); 
    }

    /**
     * Valida os campos do objeto {@link PessoaDTO}.
     * Lança exceções personalizadas quando os dados não atendem aos critérios de validação.
     *
     * @param pessoaDTO O objeto contendo os dados da pessoa a ser validada.
     */
    public void validarPessoaDTO(PessoaDTO pessoaDTO) {

    	if(isNomeNulo(pessoaDTO.getNome())){
    		throw new CampoNotNullException("Campo nulo não permitido! Insira um nome.");
    	}
    	
    	if(isNomeVazio(pessoaDTO.getNome())){
    		throw new CampoVazioException("Campo vazio não permitido! Insira um nome.");
    	}
        if (isNomeInvalido(pessoaDTO.getNome())) {
            throw new FormatoInvalidoException("Nome inválido! Deve conter apenas letras.");
        }

        if (isCidadeInValida(pessoaDTO.getCidade())) {
            throw new FormatoInvalidoException("Cidade inválida! Deve conter apenas letras.");
        }

        if (isCepInvalido(pessoaDTO.getCep())) {
            throw new FormatoInvalidoException("CEP inválido! O formato correto é XXXXXXXX ou XXXXX-XXX (Apenas números).");
        }
        
        if (pessoaDTO.getUf() != null && !pessoaDTO.getUf().trim().isEmpty()) {
            try {
                Estados.valueOf(pessoaDTO.getUf().trim().toUpperCase()); // Tenta converter para enum 
            } catch (IllegalArgumentException e) {
                throw new FormatoInvalidoException("UF inválido! Insira um estado válido. Exemplo: SP.");
            }
        } else {
            pessoaDTO.setUf(null); // Garante que a UF aceite o valor null
        }
    }
}
