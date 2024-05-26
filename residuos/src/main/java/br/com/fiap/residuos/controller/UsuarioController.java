package br.com.fiap.residuos.controller;

import br.com.fiap.residuos.dto.UsuarioCadastroDto;
import br.com.fiap.residuos.dto.UsuarioExibicaoDto;
import br.com.fiap.residuos.model.Usuario;
import br.com.fiap.residuos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto salvar(@RequestBody @Valid UsuarioCadastroDto usuario){
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDto> litarTodos(){
        return usuarioService.listarTodos();
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<UsuarioExibicaoDto> buscarPorId(@PathVariable Long usuarioId){
        return ResponseEntity.ok(usuarioService.buscarPorId(usuarioId));
    }

    @GetMapping
    @RequestMapping(value = "/usuarios", params = "email")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto buscarPorEmail(@RequestParam String email){
        return usuarioService.buscarPorEmail(email);
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId){
        usuarioService.excluir(usuarioId);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@RequestBody Usuario usuario){
        return usuarioService.atualizar(usuario);
    }


}
