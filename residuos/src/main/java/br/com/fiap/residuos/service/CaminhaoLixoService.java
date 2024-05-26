package br.com.fiap.residuos.service;

import br.com.fiap.residuos.model.CaminhaoLixo;
import br.com.fiap.residuos.repository.CaminhaoLixoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CaminhaoLixoService {

    @Autowired
    private CaminhaoLixoRepository caminhaoLixoRepository;

    public List<CaminhaoLixo> listarTodosCaminhoesLixo() {
        return caminhaoLixoRepository.findAll();
    }

    public Optional<CaminhaoLixo> buscarCaminhaoLixoPorId(Long id) {
        return caminhaoLixoRepository.findById(id);
    }

    public CaminhaoLixo criarCaminhaoLixo(CaminhaoLixo caminhaoLixo) {
        if (caminhaoLixoRepository.existsByPlaca(caminhaoLixo.getPlaca())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Placa já cadastrada");
        }
        return caminhaoLixoRepository.save(caminhaoLixo);
    }

    public CaminhaoLixo atualizarCaminhaoLixo(Long id, CaminhaoLixo caminhaoLixoAtualizado) {
        CaminhaoLixo caminhaoLixoExistente = caminhaoLixoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Caminhão de lixo não encontrado"));

        if (!caminhaoLixoExistente.getPlaca().equals(caminhaoLixoAtualizado.getPlaca()) &&
                caminhaoLixoRepository.existsByPlaca(caminhaoLixoAtualizado.getPlaca())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Placa já cadastrada");
        }

        caminhaoLixoExistente.setPlaca(caminhaoLixoAtualizado.getPlaca());
        caminhaoLixoExistente.setCapacidade(caminhaoLixoAtualizado.getCapacidade());
        caminhaoLixoExistente.setStatus(caminhaoLixoAtualizado.getStatus());

        return caminhaoLixoRepository.save(caminhaoLixoExistente);
    }

    public void excluirCaminhaoLixo(Long id) {
        caminhaoLixoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Caminhão de lixo não encontrado"));
        caminhaoLixoRepository.deleteById(id);
    }
}
