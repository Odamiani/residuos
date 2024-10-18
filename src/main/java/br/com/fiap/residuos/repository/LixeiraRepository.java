package br.com.fiap.residuos.repository;

import br.com.fiap.residuos.model.Lixeira;
import br.com.fiap.residuos.model.StatusLixeira;
import br.com.fiap.residuos.model.TipoLixo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LixeiraRepository extends JpaRepository<Lixeira, Long> {

    boolean existsByCodigoIdentificacao(String codigoIdentificacao);

    @Query("SELECT l FROM Lixeira l WHERE (:data IS NULL OR l.dataCadastro <= :data) " +
            "AND (:regiao IS NULL OR l.endereco.regiao = :regiao) " +
            "AND (:bairro IS NULL OR l.endereco.bairro = :bairro) " +
            "AND (:status IS NULL OR l.status = :status) " +
            "AND (:tipoLixo IS NULL OR l.tipoLixo = :tipoLixo)")
    List<Lixeira> findLixeiras(@Param("data") LocalDate data,
                               @Param("regiao") String regiao,
                               @Param("bairro") String bairro,
                               @Param("status") StatusLixeira status,
                               @Param("tipoLixo") TipoLixo tipoLixo);

    // Método para paginação
    Page<Lixeira> findAll(Pageable pageable);
}
