package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.Objects;

@Data
public class GravarModel {

    @Expose(serialize = false)
    private int moradorId;

    @Expose
    private String nome;

    @Expose
    private String telefone;

    @Expose
    private String email;

    @Expose
    private String senha;

    @Expose
    private String role;

}
