package gerenciar.pessoas.attornatus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/consultarEnderecoPorPessoa/{id}", method = RequestMethod.GET)
	public Endereco consultarEnderecoPorPessoa(@PathVariable("id") Long id) {	
		return enderecoService.consultarEnderecoPorPessoaId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
	}
}

