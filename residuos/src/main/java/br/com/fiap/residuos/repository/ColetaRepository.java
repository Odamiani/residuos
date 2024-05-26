package br.com.fiap.residuos.repository;

import br.com.fiap.residuos.model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColetaRepository extends JpaRepository<Coleta, Long> {
}
