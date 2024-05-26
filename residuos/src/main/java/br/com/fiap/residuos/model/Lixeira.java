package br.com.fiap.residuos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_lixeiras")
@Getter
@Setter
public class Lixeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(unique = true)
    private String codigoIdentificacao;

    @NotNull
    @Min(100)
    @Max(5000)
    private Double capacidade;

    @NotNull
    @Min(0)
    @Max(100)
    private Double nivelPreenchimento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusLixeira status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoLixo tipoLixo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @OneToMany(mappedBy = "lixeira", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Sensor> sensores;

    @OneToMany(mappedBy = "lixeira")
    @JsonIgnore
    private List<Coleta> coletas;


}
