package Conexiones.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Conexiones.model.Usuario;
import Conexiones.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrar(String nombre, String usuario, String password) {

        if (usuarioRepository.findByUsuario(usuario).isPresent()) {
            throw new RuntimeException("Usuario ya existente");
        }

        Usuario nuevo = new Usuario(
                nombre,
                usuario,
                encoder.encode(password)
        );

        return usuarioRepository.save(nuevo);
    }

    public Usuario login(String usuario, String password) {

        Usuario usuarioDb = usuarioRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!encoder.matches(password, usuarioDb.getContrasenya())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuarioDb;
    }
}

