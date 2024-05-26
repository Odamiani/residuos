package br.com.fiap.residuos.controller;

import br.com.fiap.residuos.model.Sensor;
import br.com.fiap.residuos.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sensores")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public List<Sensor> listarTodosSensores() {
        return sensorService.listarTodosSensores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> buscarSensorPorId(@PathVariable Long id) {
        return sensorService.buscarSensorPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Sensor> criarSensor(@RequestBody @Valid Sensor sensor) {
        Sensor novoSensor = sensorService.criarSensor(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSensor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> atualizarSensor(@PathVariable Long id, @RequestBody @Valid Sensor sensor) {
        try {
            Sensor sensorAtualizado = sensorService.atualizarSensor(id, sensor);
            return ResponseEntity.ok(sensorAtualizado);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirSensor(@PathVariable Long id) {
        sensorService.excluirSensor(id);
        return ResponseEntity.noContent().build();
    }
}

