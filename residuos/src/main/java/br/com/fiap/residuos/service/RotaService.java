package br.com.fiap.residuos.service;

import br.com.fiap.residuos.repository.RotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.residuos.model.Rota;

import java.util.List;

@Service
public class RotaService {

    @Autowired
    private RotaRepository rotaRepository;

    public Rota gravar(Rota rota){
        return rotaRepository.save(rota);
    }

    public List<Rota> listarTodasRotas(){
        return rotaRepository.findAll();
    }

    public Rota buscarPorId(Long id){
        var rotaOptional =  rotaRepository.findById(id);

        if(rotaOptional.isPresent()){
            return rotaOptional.get();
        } else {
            throw new RuntimeException("Rota não existe");
        }
    }
}
