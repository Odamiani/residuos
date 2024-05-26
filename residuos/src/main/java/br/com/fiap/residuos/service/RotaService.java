package br.com.fiap.residuos.service;

import br.com.fiap.residuos.model.Rota;
import br.com.fiap.residuos.repository.RotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RotaService {

    @Autowired
    private RotaRepository rotaRepository;

    public List<Rota> listarTodasRotas() {
        return rotaRepository.findAll();
    }

    public Optional<Rota> buscarRotaPorId(Long id) {
        return rotaRepository.findById(id);
    }

    public Rota criarRota(Rota rota) {
        return rotaRepository.save(rota);
    }

    public Rota atualizarRota(Long id, Rota rotaAtualizada) {
        Rota rotaExistente = rotaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rota não encontrada"));
        rotaExistente.setDescricao(rotaAtualizada.getDescricao());
        // ... (atualizar outros campos da rota)
        return rotaRepository.save(rotaExistente);
    }

    public void excluirRota(Long id) {
        rotaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rota não encontrada"));
        rotaRepository.deleteById(id);
    }
}

