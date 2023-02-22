package gerenciar.pessoas.attornatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gerenciar.pessoas.attornatus.entidade.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
