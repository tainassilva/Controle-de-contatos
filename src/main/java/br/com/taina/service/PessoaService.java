package br.com.taina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taina.contatosEnum.Estados;
import br.com.taina.dto.PessoaDTO;
import br.com.taina.model.Pessoa;
import br.com.taina.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    // Expressão regular para garantir que o nome contém apenas letras e espaços
    String regexLetrasEspacos = "^[A-Za-záàãâéèêíóôúçÁÀÂÉÊÍÓÔÚÇ\\s]+$";

    String regexCep = "^[0-9]{5}-?[0-9]{3}$";
    
    String regexUf = "^[A-Z]{2}$";


    // CRUD - Create
    public Pessoa save(Pessoa pessoa) {
        // Validação de nome
    	   // Valida o nome antes de fazer a atualização
        if (isNomeInvalido(pessoa.getNome())) {
            //throw new IllegalArgumentException("Nome inválido! Nome deve ser preenchido corretamente.");
            System.out.println("Nome inválido! Digite apenas letras");
            return null;
        }
        
        if(isNomeInvalido(pessoa.getCidade())) {
        	System.out.println("Endereço inválido! Digite um endereço válido");
        	return null;
        }
        
        if(!isCepValido(pessoa.getCep())) {
        	 //throw new IllegalArgumentException("Nome inválido! Nome deve ser preenchido corretamente.");
            System.out.println("O formato do CEP está incorreto. O formato correto é XXXXXXXX ou XXXXX-XXX.");
            return null;
        }
        
        if(!isUfValido(pessoa.getUf().name())) {
        	System.out.println("UF inválido, digite um formato válido : Ex: SP");
        	return null;
        }

        try {
            return pessoaRepository.save(pessoa);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar pessoa: " + e.getMessage(), e);
        }
    }

    // CRUD - Read (leitura individual ou lista)
    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }
    

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public PessoaDTO findPessoaById(Long id) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);

        if (!pessoaOpt.isPresent()) {
            //throw new IllegalArgumentException("Pessoa com ID " + id + " não encontrada!");
        	System.out.println("Pessoa com ID " + id + "não encontrada");
        }

        return new PessoaDTO(pessoaOpt.get());
    }

 // CRUD - Update
    public Pessoa update(Long id, Pessoa pessoa) {
        // Buscar a pessoa pelo ID
        Optional<Pessoa> findPessoa = pessoaRepository.findById(id);

        // Verifica se a pessoa foi encontrada
        if (!findPessoa.isPresent()) {
        	System.out.println("Pessoa com ID " + id + "não encontrada para atualização!");
            //throw new IllegalArgumentException("Pessoa com ID " + id + " não encontrada para atualização.");
        	return null;
        }

        // Obtém a pessoa para atualização
        Pessoa updPessoa = findPessoa.get();

        // Valida o nome antes de fazer a atualização
        if (isNomeInvalido(pessoa.getNome())) {
            //throw new IllegalArgumentException("Nome inválido! Nome deve ser preenchido corretamente.");
            System.out.println("Nome inválido! Digite apenas letras");
            return null;
        }
        
        if(isNomeInvalido(pessoa.getCidade())) {
        	System.out.println("Endereço inválido! Digite um endereço válido");
        	return null;
        }
        
        
        if(!isCepValido(pessoa.getCep())) {
        	 //throw new IllegalArgumentException("Nome inválido! Nome deve ser preenchido corretamente.");
            System.out.println("O formato do CEP está incorreto. O formato correto é XXXXXXXX ou XXXXX-XXX.");
            return null;
        }
        
        if(!isUfValido(pessoa.getUf().name())) {
        	System.out.println("UF inválido, digite um formato válido : Ex: SP");
        	return null;
        }

        // Atualiza os campos da pessoa
        updPessoa.setNome(pessoa.getNome());
        updPessoa.setEndereco(pessoa.getEndereco());
        updPessoa.setCep(pessoa.getCep());
        updPessoa.setCidade(pessoa.getCidade());
        updPessoa.setUf(pessoa.getUf());

        // Salva a pessoa no banco de dados
        return pessoaRepository.save(updPessoa);
    }

    
    // CRUD - Delete
    public void delete(Long id) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);
        if (pessoaOpt.isPresent()) {
            pessoaRepository.deleteById(id);
        } else {
        	System.out.println("Pessoa com id " + id + "não encontrada para exclusão");
           // throw new IllegalArgumentException("Pessoa com ID " + id + " não encontrada para exclusão.");
        }
    }

    // Método de validação para nome
    private boolean isNomeInvalido(String nome) {
        return nome == null || nome.trim().isEmpty() || !Pattern.matches(regexLetrasEspacos, nome);
    }
    
    private boolean isCepValido(String cep) {
        return Pattern.matches(regexCep, cep);  // Retorna true se o CEP for válido, false caso contrário
    }
    
    
    private boolean isUfValido(String uf) {
        try {
            Estados.valueOf(uf.toUpperCase()); // Verifica se existe no Enum
            return true;
        } catch (IllegalArgumentException e) {
            return false; // Se não existir, retorna falso
        }
    }

}

