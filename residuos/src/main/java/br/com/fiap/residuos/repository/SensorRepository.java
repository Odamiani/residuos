package br.com.fiap.residuos.repository;

import br.com.fiap.residuos.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    boolean existsByCodigo(String codigo);
}

