package br.com.fiap.residuos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_lixeiras")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Lixeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "DATA_CADASTRO")
    private LocalDate dataCadastro;

    @Column(unique = true, name = "CODIGO_IDENTIFICACAO")
    private String codigoIdentificacao;

    @NotNull
    @Min(100)
    @Max(5000)
    private Double capacidade;

    @NotNull
    @Column (name = "NIVEL_PREENCHIMENTO")
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
    @JoinColumn(name = "ENDERECO_ID", referencedColumnName = "ID")
    private Endereco endereco;
}
