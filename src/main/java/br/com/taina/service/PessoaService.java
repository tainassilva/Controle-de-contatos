package br.com.taina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	//CRUD - Create
	public Pessoa save(Pessoa pessoa) {
			//regra de negócio:
			//-Se o campo nome está preenchido
		// Se o nome for nulo ou estiver vazio (após remover os espaços)
		if(pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
		    System.out.println("Nome não pode ser nulo ou vazio! Insira um nome");
		    return null;
		}

		// Expressão regular para garantir que o nome contém apenas letras e espaços
		String regex = "^[A-Za-záàãâéèêíóôúçÁÀÂÉÊÍÓÔÚÇ\\s]+$";

		if (!Pattern.matches(regex, pessoa.getNome())) {
		    throw new IllegalArgumentException("Nome deve conter apenas letras e espaços!");
		}

			try {			
				return pessoaRepository.save(pessoa);
			}catch (Exception e) {
				System.out.println("Erro ao inserir pessoa " + 
			                       pessoa.toString() + ": " + e.getMessage());
				return null;
			}
		}
	
	//CRUD - Read (leitura individual ou lista)
		public Optional<Pessoa> findById(Long id){
			//select * from produto where id = ?id
			return pessoaRepository.findById(id); 
		}
		
		public List<Pessoa> findAll(){
			//select * from produto
			return pessoaRepository.findAll();
		}
		
		public PessoaDTO findPessoaById(Long id) {
	        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);

	        if (pessoaOpt.isEmpty()) {
	            return null;
	        }

	        // Retorna diretamente o PessoaDTO com os dados necessários
	        return new PessoaDTO(pessoaOpt.get());
	    }
		
		//CRUD - Update
		public Pessoa update(Pessoa pessoa) {
			//Regra de negócio:
			// - Verificar se o produto existe; Se existir, atualizar, 
			//   caso não, insere novo.
			// ------------------------------------
			// - Pesquisar produto:
			Optional<Pessoa> findPessoa = pessoaRepository.findById(pessoa.getIdPessoa());
			
			//se o produto existir, atualizar:
			if(findPessoa.isPresent()) {
				//crio um novo objeto de produtos e lanço os dados
				// do objeto que veio da Internet (parametro).
				Pessoa updPessoa = findPessoa.get(); //setId
				updPessoa.setNome(pessoa.getNome()); //veio por parametro
				updPessoa.setEndereco(pessoa.getEndereco());
				updPessoa.setCep(pessoa.getCep());
				updPessoa.setCidade(pessoa.getCidade());
				updPessoa.setUf(pessoa.getUf());

				return pessoaRepository.save(updPessoa); //UPDATE
			}
			return pessoaRepository.save(pessoa); //INSERT		
		}
		
		//CRUD - Delete
		public void delete(Long id) {
		    Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);
		    if (pessoaOpt.isPresent()) {
		        // Aqui, a exclusão do Contato será feita automaticamente se você usar CascadeType.ALL
		        pessoaRepository.deleteById(id);
		    } else {
		        System.out.println("Pessoa não encontrada");
		    }
	}
		
		
}
