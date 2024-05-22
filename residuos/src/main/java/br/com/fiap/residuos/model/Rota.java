package br.com.fiap.residuos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_rotas")
public class Rota {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ROTAS"
    )
    @SequenceGenerator(
            name = "SEQ_ROTAS",
            sequenceName = "SEQ_ROTAS",
            allocationSize = 1
    )
    private Long id;
    private String descricao;
    private Date data;
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}
