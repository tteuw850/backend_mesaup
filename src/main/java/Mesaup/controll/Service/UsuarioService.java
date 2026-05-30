package Mesaup.controll.Service;

import Mesaup.controll.Entity.Usuario;

import Mesaup.controll.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario criarUsuario (Usuario usuario) {
        boolean existsByEmail = usuarioRepository
                .existsByEmail(usuario.getEmail());

        if (existsByEmail) {
            throw new RuntimeException(
                    "Email já cadastrado"
            );
        }
        return usuarioRepository.save(usuario);
    }


    public List<Usuario> listarUsuario() {
        List<Usuario> users = usuarioRepository.findAll();

        if (users.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado");
        }

        return users;
    }


    public Usuario buscarUsuarioPorEmail(String email) {
        Optional<Usuario> emailUsuario = usuarioRepository.findByEmail(email);
        if (emailUsuario.isEmpty()) {
            throw new RuntimeException("Nenhum usuário com o email solicitado foi encontrado");
        }
        return emailUsuario.orElse(null);
    }


    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        if (usuario == null) {
            throw new RuntimeException("Nenhum usuário com o id solicitado foi encontrado ");
        }
        return usuario;
    }

    @Transactional
    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario existente = buscarUsuarioPorId(id);
        if (existente == null) {
            throw new RuntimeException("Nenhum usuário encontrado");
        }
        existente.setNome(usuario.getNome());
        existente.setEmail(usuario.getEmail());
        return usuarioRepository.save(existente);
    }

    @Transactional
    public Usuario deletarUsuario(Long id) {
        Usuario existente = usuarioRepository.findById(id).get();
        if (existente == null) {
            throw new RuntimeException("Nenhum usuário encontrado");
        }
        usuarioRepository.delete(existente);
        return existente;
    }




}
