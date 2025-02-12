package br.com.taina.validation;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.taina.contatosEnum.TipoContato;
import br.com.taina.exception.contato.ContatoNuloOuVazioException;
import br.com.taina.model.Contato;

public class ContatoValidation {

	/**
	 * Ver um jeito de transformar o enum em string e comparar que nem na classe pessoaValidator ... 
	 */ 
	private static final String regexCelularETelefoneFixo = "^\\(?\\d{2}\\)?\\s?9\\d{4}-\\d{4}$";
	 
	@Autowired
	TipoContato tipoContato;
	
	
	private boolean isTelefoneValido(String telefone) {
		 return !Pattern.matches(regexCelularETelefoneFixo, telefone);
	}
	
	private boolean isContatoNuloOuVazio(String contato) {
		return contato == null || contato.trim().isEmpty();  
	}
	
	public void validarContato(Contato contato) {
		if(isContatoNuloOuVazio(contato.getContato())) {
			throw new ContatoNuloOuVazioException("Erro! O contato não pode ser nulo ou vazio. Insira um contato.");
		}
	}
}
