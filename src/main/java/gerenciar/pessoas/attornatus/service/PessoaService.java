package gerenciar.pessoas.attornatus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import gerenciar.pessoas.attornatus.entidade.Pessoa;
import gerenciar.pessoas.attornatus.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
@Validated
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

	@Transactional
	public Optional<Pessoa> consultarPessoaPorId(Long id) {
		return pessoaRepository.findById(id);
	}

	@Transactional
	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}
	
	//método para atualizar uma pessoa
	@Transactional
	public ResponseEntity<Pessoa> editarPessoa(Long id, Pessoa editarPessoa) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
		if (pessoaOptional.isPresent()) {
			Pessoa pessoa = pessoaOptional.get();
			pessoa.setNome(editarPessoa.getNome());
			pessoa.setDataNascimento(editarPessoa.getDataNascimento());
			pessoa.setEndereco(editarPessoa.getEndereco());
			pessoaRepository.save(pessoa);
			return ResponseEntity.ok(pessoa);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
