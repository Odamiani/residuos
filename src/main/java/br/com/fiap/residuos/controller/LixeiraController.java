package br.com.fiap.residuos.controller;

import br.com.fiap.residuos.model.Lixeira;
import br.com.fiap.residuos.service.LixeiraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/lixeiras")
public class LixeiraController {

    @Autowired
    private LixeiraService lixeiraService;

    @GetMapping
    public List<Lixeira> listarTodasLixeiras() {
        return lixeiraService.listarTodasLixeiras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lixeira> buscarLixeiraPorId(@PathVariable Long id) {
        return lixeiraService.buscarLixeiraPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lixeira não encontrada"));
    }

    @PostMapping
    public ResponseEntity<Lixeira> criarLixeira(@RequestBody @Valid Lixeira lixeira) {
        Lixeira novaLixeira = lixeiraService.criarLixeira(lixeira);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaLixeira);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lixeira> atualizarLixeira(@PathVariable Long id, @RequestBody @Valid Lixeira lixeira) {
        try {
            Lixeira lixeiraAtualizada = lixeiraService.atualizarLixeira(id, lixeira);
            return ResponseEntity.ok(lixeiraAtualizada);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLixeira(@PathVariable Long id) {
        lixeiraService.excluirLixeira(id);
        return ResponseEntity.noContent().build();
    }
}
