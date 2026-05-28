package Conexiones.service;

import java.util.logging.Logger;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Conexiones.controller.UsuarioAuthController;
import Conexiones.dto.ChangePasswordRequest;
import Conexiones.dto.RegisterRequest;
import Conexiones.dto.UsuarioResponse;
import Conexiones.exception.UsuarioNoEncontradoException;
import Conexiones.model.Usuario;
import Conexiones.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private static final Logger log = Logger.getLogger(UsuarioAuthController.class.getName());

	private final UsuarioRepository usuarioRepository;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	// 🔹 REGISTER
	public void registrar(RegisterRequest request) {

		if (usuarioRepository.findByUsuario(request.getUsuario()).isPresent()) {
			throw new RuntimeException("El usuario ya está registrado");
		}

		log.info("Registrando nuevo usuario. Usuario: " + request.getUsuario() +
				 ", Nombre: " + request.getNombre());

		Usuario usuario = new Usuario();
		usuario.setNombre(request.getNombre());
		usuario.setUsuario(request.getUsuario());
		usuario.setContrasenya(encoder.encode(request.getPassword()));

		usuarioRepository.save(usuario);
	}

	// 🔹 LOGIN
	public Usuario login(String usuario, String password) {

		Usuario usuarioDb = usuarioRepository.findByUsuario(usuario)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		if (!encoder.matches(password, usuarioDb.getContrasenya())) {
			throw new RuntimeException("Contraseña incorrecta");
		}

		log.info("Usuario autenticado correctamente. Usuario: " + usuarioDb.getUsuario() +
				 ", Nombre: " + usuarioDb.getNombre());

		return usuarioDb;
	}

	// 🔹 CHANGE PASSWORD
	public void changePassword(ChangePasswordRequest request) {

		log.info("Intentando cambiar contraseña para usuario: " + request.getUsuario());

		Usuario usuario = usuarioRepository.findByUsuario(request.getUsuario())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		if (!encoder.matches(request.getOldPassword(), usuario.getContrasenya())) {
			throw new RuntimeException("La contraseña actual es incorrecta");
		}

		usuario.setContrasenya(encoder.encode(request.getNewPassword()));
		usuarioRepository.save(usuario);
	}

	public UsuarioResponse obtenerUsuarioPorId(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNoEncontradoException(id));

		return new UsuarioResponse(
				usuario.getId(),
				usuario.getNombre(),
				usuario.getUsuario()
		);
	}
}
