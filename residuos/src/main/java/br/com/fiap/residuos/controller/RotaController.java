package br.com.fiap.residuos.controller;

import br.com.fiap.residuos.model.Rota;
import br.com.fiap.residuos.service.RotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/rotas")
public class RotaController {

    @Autowired
    private RotaService rotaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Rota> listarTodos(){
        return rotaService.listarTodasRotas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rota gravar(@RequestBody Rota rota){
        return rotaService.gravar(rota);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rota buscar(@PathVariable Long id){
        return rotaService.buscarPorId(id);
    }
}
