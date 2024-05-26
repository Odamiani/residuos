package br.com.fiap.residuos.controller;

import br.com.fiap.residuos.model.CaminhaoLixo;
import br.com.fiap.residuos.service.CaminhaoLixoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/caminhoes-lixo")
public class CaminhaoLixoController {

    @Autowired
    private CaminhaoLixoService caminhaoLixoService;

    @GetMapping
    public List<CaminhaoLixo> listarTodosCaminhoesLixo() {
        return caminhaoLixoService.listarTodosCaminhoesLixo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaminhaoLixo> buscarCaminhaoLixoPorId(@PathVariable Long id) {
        return caminhaoLixoService.buscarCaminhaoLixoPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Caminhão de lixo não encontrado"));
    }

    @PostMapping
    public ResponseEntity<CaminhaoLixo> criarCaminhaoLixo(@RequestBody @Valid CaminhaoLixo caminhaoLixo) {
        CaminhaoLixo novoCaminhaoLixo = caminhaoLixoService.criarCaminhaoLixo(caminhaoLixo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCaminhaoLixo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaminhaoLixo> atualizarCaminhaoLixo(@PathVariable Long id, @RequestBody @Valid CaminhaoLixo caminhaoLixo) {
        try {
            CaminhaoLixo caminhaoLixoAtualizado = caminhaoLixoService.atualizarCaminhaoLixo(id, caminhaoLixo);
            return ResponseEntity.ok(caminhaoLixoAtualizado);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCaminhaoLixo(@PathVariable Long id) {
        caminhaoLixoService.excluirCaminhaoLixo(id);
        return ResponseEntity.noContent().build();
    }
}
