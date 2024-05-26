package br.com.fiap.residuos.model;

        import jakarta.persistence.*;
        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.NotNull;
        import lombok.Getter;
        import lombok.Setter;

@Entity
@Table(name = "tbl_enderecos")
@Getter
@Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String regiao;

    private String bairro;

    @NotBlank
    private String logradouro;

    @NotNull
    private Integer numero;

    private String complemento;

    private String cep;
}
