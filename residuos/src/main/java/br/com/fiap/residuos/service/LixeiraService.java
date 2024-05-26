package br.com.fiap.residuos.service;

import br.com.fiap.residuos.model.Lixeira;
import br.com.fiap.residuos.repository.LixeiraRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LixeiraService {

    @Autowired
    private LixeiraRepository lixeiraRepository;

    public List<Lixeira> listarTodasLixeiras() {
        return lixeiraRepository.findAll();
    }

    public Optional<Lixeira> buscarLixeiraPorId(Long id) {
        return lixeiraRepository.findById(id);
    }

    public Lixeira criarLixeira(Lixeira lixeira) {
        // Validações (opcional) - exemplo: verificar se o código de identificação já existe
        if (lixeiraRepository.existsByCodigoIdentificacao(lixeira.getCodigoIdentificacao())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código de identificação já existe");
        }

        return lixeiraRepository.save(lixeira);
    }

    public Lixeira atualizarLixeira(Long id, Lixeira lixeiraAtualizada) {
        Lixeira lixeiraExistente = lixeiraRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lixeira não encontrada"));

        // Validações (opcional) - exemplo: verificar se o novo código de identificação já existe em outra lixeira
        if (!lixeiraExistente.getCodigoIdentificacao().equals(lixeiraAtualizada.getCodigoIdentificacao()) &&
                lixeiraRepository.existsByCodigoIdentificacao(lixeiraAtualizada.getCodigoIdentificacao())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código de identificação já existe");
        }

        // Atualiza os campos da lixeira existente
        lixeiraExistente.setCodigoIdentificacao(lixeiraAtualizada.getCodigoIdentificacao());
        lixeiraExistente.setCapacidade(lixeiraAtualizada.getCapacidade());
        lixeiraExistente.setNivelPreenchimento(lixeiraAtualizada.getNivelPreenchimento());
        lixeiraExistente.setStatus(lixeiraAtualizada.getStatus());
        lixeiraExistente.setTipoLixo(lixeiraAtualizada.getTipoLixo());
        lixeiraExistente.setEndereco(lixeiraAtualizada.getEndereco());
        lixeiraExistente.setSensores(lixeiraAtualizada.getSensores());

        return lixeiraRepository.save(lixeiraExistente);
    }

    public void excluirLixeira(Long id) {
        lixeiraRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lixeira não encontrada"));
        lixeiraRepository.deleteById(id);
    }
}
