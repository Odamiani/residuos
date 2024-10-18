package br.com.fiap.residuos.service;

import br.com.fiap.residuos.dto.UsuarioCadastroDto;
import br.com.fiap.residuos.dto.UsuarioExibicaoDto;
import br.com.fiap.residuos.exception.UsuarioNaoEncontradoException;
import br.com.fiap.residuos.model.Usuario;
import br.com.fiap.residuos.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto salvarUsuario(UsuarioCadastroDto usuarioDTO){

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioExibicaoDto(usuarioSalvo);

    }

    public UsuarioExibicaoDto buscarPorId(Long id){
        Optional<Usuario> usuarioOptional =
                usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            return new UsuarioExibicaoDto(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não existe no banco de dados!");
        }
    }

    public UsuarioExibicaoDto buscarPorEmail(String email){
        Usuario usuarioOptional =
                (Usuario) usuarioRepository.findByEmail(email);
        if (usuarioOptional != null){
            return new UsuarioExibicaoDto(usuarioOptional);
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não existe no banco de dados!");
        }
    }


    public List<UsuarioExibicaoDto> listarTodos(){
        return usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioExibicaoDto::new)
                .toList();
    }

    public void excluir(Long id){
        Optional<Usuario> usuarioOptional =
                usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("Produto não encontrado!");
        }
    }

    public Usuario atualizar(Usuario usuario){
        Optional<Usuario> usuarioOptional =
                usuarioRepository.findById(usuario.getMoradorId());

        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

        if (usuarioOptional.isPresent()){
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

}
