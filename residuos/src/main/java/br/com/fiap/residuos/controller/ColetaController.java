package br.com.fiap.residuos.controller;

import br.com.fiap.residuos.model.Coleta;
import br.com.fiap.residuos.service.ColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/coletas")
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    @GetMapping
    public List<Coleta> listarTodasColetas() {
        return coletaService.listarTodasColetas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coleta> buscarColetaPorId(@PathVariable Long id) {
        return coletaService.buscarColetaPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coleta não encontrada"));
    }

    @PostMapping
    public ResponseEntity<Coleta> criarColeta(@RequestBody @Valid Coleta coleta) {
        Coleta novaColeta = coletaService.criarColeta(coleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaColeta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coleta> atualizarColeta(@PathVariable Long id, @RequestBody @Valid Coleta coleta) {
        try {
            Coleta coletaAtualizada = coletaService.atualizarColeta(id, coleta);
            return ResponseEntity.ok(coletaAtualizada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirColeta(@PathVariable Long id) {
        coletaService.excluirColeta(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/iniciar")
    public ResponseEntity<Coleta> iniciarColeta(@PathVariable Long id) {
        Coleta coletaIniciada = coletaService.iniciarColeta(id);
        return ResponseEntity.ok(coletaIniciada);
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Coleta> concluirColeta(@PathVariable Long id, @RequestParam Double quantidadeRecolhida) {
        Coleta coletaConcluida = coletaService.concluirColeta(id, quantidadeRecolhida);
        return ResponseEntity.ok(coletaConcluida);
    }
}
