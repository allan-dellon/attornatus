package gerenciar.pessoas.attornatus.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gerenciar.pessoas.attornatus.entidade.Pessoa;
import gerenciar.pessoas.attornatus.repository.PessoaRepository;
import gerenciar.pessoas.attornatus.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	// Método para criar pessoa
	@RequestMapping(value = "/criarPessoa", method = RequestMethod.POST)
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
		try {
			Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);

			return new ResponseEntity<Pessoa>(pessoaCriada, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Pessoa>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Método para editar pessoa
	@RequestMapping(value = "/editarPessoa/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
		if (pessoaOptional.isPresent()) {
			Pessoa pessoa = pessoaOptional.get();
			pessoa.setNome(pessoaAtualizada.getNome());
			pessoa.setDataNascimento(pessoaAtualizada.getDataNascimento());
			pessoa.setEndereco(pessoaAtualizada.getEndereco());
			pessoaRepository.save(pessoa);
			return ResponseEntity.ok(pessoa);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Método para consultar uma pessoa por Id
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/consultarPessoaPorId/{id}", method = RequestMethod.GET)
	public Pessoa consultarPessoaPorId(@PathVariable("id") Long id) {
		return pessoaService.consultarPessoaPorId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}

	@RequestMapping(value = "/consultarPessoas", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> consultarPessoas() {
		List<Pessoa> listaPessoas = pessoaService.listarPessoas();
		return new ResponseEntity<List<Pessoa>>(listaPessoas, HttpStatus.OK);
	}

}
