package br.com.fiap.residuos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_coletas")
@Getter
@Setter
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lixeira_id", nullable = false)
    @JsonIgnore
    private Lixeira lixeira;

    @ManyToOne
    @JoinColumn(name = "caminhao_lixo_id", nullable = false)
    @JsonIgnore
    private CaminhaoLixo caminhaoLixo;

    @ManyToOne
    @JoinColumn(name = "rota_id")
    @JsonIgnore
    private Rota rota;

    @NotNull
    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusColeta status;

    private Double quantidadeRecolhida;
}

