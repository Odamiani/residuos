package br.com.fiap.residuos.service;

import br.com.fiap.residuos.model.Sensor;
import br.com.fiap.residuos.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public List<Sensor> listarTodosSensores() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> buscarSensorPorId(Long id) {
        return sensorRepository.findById(id);
    }

    public Sensor criarSensor(Sensor sensor) {
        if (sensorRepository.existsByCodigo(sensor.getCodigo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código do sensor já cadastrado");
        }
        return sensorRepository.save(sensor);
    }

    public Sensor atualizarSensor(Long id, Sensor sensorAtualizado) {
        Sensor sensorExistente = sensorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor não encontrado"));

        if (!sensorExistente.getCodigo().equals(sensorAtualizado.getCodigo()) &&
                sensorRepository.existsByCodigo(sensorAtualizado.getCodigo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código do sensor já cadastrado");
        }

        sensorExistente.setCodigo(sensorAtualizado.getCodigo());
        sensorExistente.setNivelAtual(sensorAtualizado.getNivelAtual());
        sensorExistente.setLixeira(sensorAtualizado.getLixeira());

        return sensorRepository.save(sensorExistente);
    }

    public void excluirSensor(Long id) {
        sensorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor não encontrado"));
        sensorRepository.deleteById(id);
    }
}

