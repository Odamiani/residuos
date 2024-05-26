package br.com.fiap.residuos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_sensores")
@Getter
@Setter
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String codigo;

    @NotNull
    @Min(0)
    @Max(100)
    private Double nivelAtual;

    @ManyToOne
    @JoinColumn(name = "lixeira_id", nullable = false)
    private Lixeira lixeira;
}
