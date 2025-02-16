package br.com.taina.enums;

/**
 * Enum que representa os tipos de contato permitidos, evitando a inserção de tipos de contatos inválidos.
 */
public enum TipoContato {

	TELEFONE_FIXO, CELULAR, EMAIL, LINKEDIN;
	
	    public static TipoContato fromString(String value) {
	        for (TipoContato tipo : TipoContato.values()) {
	            if (tipo.name().equalsIgnoreCase(value)) {
	                return tipo;
	            }
	        }
	        throw new IllegalArgumentException("Tipo de contato inválido: " + value);
	    }
	}


