package br.com.fiap.residuos.controller;

import br.com.fiap.residuos.model.Rota;
import br.com.fiap.residuos.service.RotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/rotas")
public class RotaController {

    @Autowired
    private RotaService rotaService;

    @GetMapping
    public List<Rota> listarTodasRotas() {
        return rotaService.listarTodasRotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rota> buscarRotaPorId(@PathVariable Long id) {
        return rotaService.buscarRotaPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rota não encontrada"));
    }

    @PostMapping
    public ResponseEntity<Rota> criarRota(@RequestBody @Valid Rota rota) {
        Rota novaRota = rotaService.criarRota(rota);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaRota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rota> atualizarRota(@PathVariable Long id, @RequestBody @Valid Rota rota) {
        try {
            Rota rotaAtualizada = rotaService.atualizarRota(id, rota);
            return ResponseEntity.ok(rotaAtualizada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRota(@PathVariable Long id) {
        rotaService.excluirRota(id);
        return ResponseEntity.noContent().build();
    }
}

