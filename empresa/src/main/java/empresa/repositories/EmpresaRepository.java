package empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import empresa.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
  boolean existsByCnpj(String cnpj);

  boolean existsByEmail(String email);
}
