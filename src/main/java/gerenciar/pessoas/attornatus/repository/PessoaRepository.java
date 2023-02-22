package gerenciar.pessoas.attornatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gerenciar.pessoas.attornatus.entidade.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
}
