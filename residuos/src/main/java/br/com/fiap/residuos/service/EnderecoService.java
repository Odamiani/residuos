package br.com.fiap.residuos.service;

import br.com.fiap.residuos.model.Endereco;
import br.com.fiap.residuos.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> listarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco criarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizarEndereco(Long id, Endereco enderecoAtualizado) {
        Endereco enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
        enderecoExistente.setRegiao(enderecoAtualizado.getRegiao());
        enderecoExistente.setBairro(enderecoAtualizado.getBairro());
        enderecoExistente.setLogradouro(enderecoAtualizado.getLogradouro());
        enderecoExistente.setNumero(enderecoAtualizado.getNumero());
        enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());
        enderecoExistente.setCep(enderecoAtualizado.getCep());
        return enderecoRepository.save(enderecoExistente);
    }

    public void excluirEndereco(Long id) {
        enderecoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
        enderecoRepository.deleteById(id);
    }
}
