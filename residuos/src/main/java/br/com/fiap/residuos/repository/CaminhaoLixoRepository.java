package br.com.fiap.residuos.repository;

import br.com.fiap.residuos.model.CaminhaoLixo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaminhaoLixoRepository extends JpaRepository<CaminhaoLixo, Long> {

    boolean existsByPlaca(String placa);
}
