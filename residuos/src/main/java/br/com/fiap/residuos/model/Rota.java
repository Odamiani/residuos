package br.com.fiap.residuos.model;

        import jakarta.persistence.*;
        import jakarta.validation.constraints.NotBlank;
        import com.fasterxml.jackson.annotation.JsonIgnore;
        import lombok.Getter;
        import lombok.Setter;

        import java.util.List;

@Entity
@Table(name = "tbl_rotas")
@Getter
@Setter
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String descricao;

    @OneToMany(mappedBy = "rota")
    @JsonIgnore // Evita serialização em loop infinito
    private List<Coleta> coletas;

    // Construtores, getters e setters (omitidos para simplificar o exemplo)
}
