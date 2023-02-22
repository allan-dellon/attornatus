package gerenciar.pessoas.attornatus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gerenciar.pessoas.attornatus.entidade.Endereco;
import gerenciar.pessoas.attornatus.service.EnderecoService;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	//Método para consultar um endereço pelo Id da pessoa cadastrada
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/consultarEnderecoPorPessoa/{id}", method = RequestMethod.GET)
	public Endereco consultarEnderecoPorPessoa(@PathVariable("id") Long id) {	
		return enderecoService.consultarEnderecoPorPessoaId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
	}
	
	//Método para consultar todos os endereços cadastrados no banco de dados
	@RequestMapping(value = "/consultarEnderecos", method = RequestMethod.GET)
	public ResponseEntity<List<Endereco>> consultarEnderecos() {
		List<Endereco> enderecos = enderecoService.consultarEnderecos();
		return new ResponseEntity<List<Endereco>>(enderecos, HttpStatus.OK);
	}
}

