package br.com.fiap.residuos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_enderecos")
public class Endereco {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ENDERECOS"
    )
    @SequenceGenerator(
            name = "SEQ_ENDERECOS",
            sequenceName = "SEQ_ENDERECOS",
            allocationSize = 1
    )
    private Long id;
    private String regiao;
    private String bairro;
    private String logradouro;
    private Integer numero;
}
