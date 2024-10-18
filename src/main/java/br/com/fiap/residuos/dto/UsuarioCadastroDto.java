package br.com.fiap.residuos.dto;

import br.com.fiap.residuos.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto (
        Long moradorId,
        @NotBlank(message = "O nome do usuário é obrigatorio!")
        String nome,

        @NotBlank(message = "O telefone para contato do usuário é obrigatorio!")
        String telefone,

        @NotBlank(message = "O e-mail do usuário é obrigatorio!")
        @Email(message = "O e-mail do usuário não é valido!")
        String email,

        @NotBlank(message = "A senha é obrigatoria")
        @Size(min = 8, max = 30, message = "A senha deve conter ao menos 8 caracteres e no maximo 30 caracteres")
        String senha,
        UsuarioRole role





){
}
