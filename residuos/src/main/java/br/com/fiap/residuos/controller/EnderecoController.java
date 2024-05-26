package br.com.fiap.residuos.controller;

import br.com.fiap.residuos.model.Endereco;
import br.com.fiap.residuos.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> listarTodosEnderecos() {
        return enderecoService.listarTodosEnderecos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) {
        return enderecoService.buscarEnderecoPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@RequestBody @Valid Endereco endereco) {
        Endereco novoEndereco = enderecoService.criarEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @RequestBody @Valid Endereco endereco) {
        try {
            Endereco enderecoAtualizado = enderecoService.atualizarEndereco(id, endereco);
            return ResponseEntity.ok(enderecoAtualizado);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Long id) {
        enderecoService.excluirEndereco(id);
        return ResponseEntity.noContent().build();
    }
}

