package br.com.fiap.residuos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_caminhoes_lixo")
@Getter
@Setter
public class CaminhaoLixo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String placa;

    @NotNull
    @Min(100)
    @Max(5000)
    private Double capacidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCaminhaoLixo status;

    @OneToMany(mappedBy = "caminhaoLixo")
    @JsonIgnore
    private List<Coleta> coletas;
}
