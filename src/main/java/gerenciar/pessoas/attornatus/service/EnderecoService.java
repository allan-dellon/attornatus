package gerenciar.pessoas.attornatus.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gerenciar.pessoas.attornatus.entidade.Endereco;
import gerenciar.pessoas.attornatus.repository.EnderecoRepository;
import jakarta.transaction.Transactional;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Transactional
	public Optional<Endereco> consultarEnderecoPorPessoaId(Long id) {
		return enderecoRepository.findById(id);
	}

	public List<Endereco> consultarEnderecos() {
		return enderecoRepository.findAll();
	}

}
