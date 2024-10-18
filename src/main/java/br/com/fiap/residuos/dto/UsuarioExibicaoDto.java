package br.com.fiap.residuos.dto;

import br.com.fiap.residuos.model.Usuario;
import br.com.fiap.residuos.model.UsuarioRole;

public record UsuarioExibicaoDto(
        Long moradorId,
        String nome,
        String telefone,
        String email,
        UsuarioRole role
) {
    public UsuarioExibicaoDto(Usuario usuario){
        this(
                usuario.getMoradorId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getRole()
        );
    }

}
