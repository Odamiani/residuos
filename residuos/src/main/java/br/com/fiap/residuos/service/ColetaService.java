package br.com.fiap.residuos.service;

import br.com.fiap.residuos.model.Coleta;
import br.com.fiap.residuos.model.StatusColeta;
import br.com.fiap.residuos.repository.ColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    public List<Coleta> listarTodasColetas() {
        return coletaRepository.findAll();
    }

    public Optional<Coleta> buscarColetaPorId(Long id) {
        return coletaRepository.findById(id);
    }

    public Coleta criarColeta(Coleta coleta) {
        coleta.setStatus(StatusColeta.AGENDADA);
        return coletaRepository.save(coleta);
    }

    public Coleta atualizarColeta(Long id, Coleta coletaAtualizada) {
        Coleta coletaExistente = coletaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coleta não encontrada"));

        // Atualiza os campos da coleta existente (exceto status, que é controlado pelos métodos iniciar/concluir)
        coletaExistente.setLixeira(coletaAtualizada.getLixeira());
        coletaExistente.setCaminhaoLixo(coletaAtualizada.getCaminhaoLixo());
        coletaExistente.setRota(coletaAtualizada.getRota());

        return coletaRepository.save(coletaExistente);
    }

    public void excluirColeta(Long id) {
        coletaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coleta não encontrada"));
        coletaRepository.deleteById(id);
    }

    public Coleta iniciarColeta(Long id) {
        Coleta coleta = coletaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coleta não encontrada"));
        if (coleta.getStatus() != StatusColeta.AGENDADA) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Coleta não pode ser iniciada");
        }
        coleta.setDataHoraInicio(LocalDateTime.now());
        coleta.setStatus(StatusColeta.EM_ANDAMENTO);
        return coletaRepository.save(coleta);
    }

    public Coleta concluirColeta(Long id, Double quantidadeRecolhida) {
        Coleta coleta = coletaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coleta não encontrada"));
        if (coleta.getStatus() != StatusColeta.EM_ANDAMENTO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Coleta não pode ser concluída");
        }
        coleta.setDataHoraFim(LocalDateTime.now());
        coleta.setQuantidadeRecolhida(quantidadeRecolhida);
        coleta.setStatus(StatusColeta.FINALIZADA);
        return coletaRepository.save(coleta);
    }
}
