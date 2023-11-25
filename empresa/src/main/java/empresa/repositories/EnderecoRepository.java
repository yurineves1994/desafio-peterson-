package empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import empresa.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
  boolean existsByCep(String cep);
}
