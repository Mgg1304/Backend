package Conexiones.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Conexiones.dto.ChangePasswordRequest;
import Conexiones.dto.LoginRequest;
import Conexiones.dto.LoginResponse;
import Conexiones.dto.RegisterRequest;
import Conexiones.model.Usuario;
import Conexiones.repository.UsuarioRepository;
import Conexiones.service.UsuarioService;

@RestController
@RequestMapping("/renthub/usuarios")
public class UsuarioAuthController {

	private static final Logger log = Logger.getLogger(UsuarioAuthController.class.getName());

	private final UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioAuthController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	// 🔹 REGISTER
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

		log.info("Registro usuario → Usuario: " + request.getUsuario() + 
				 ", Nombre: " + request.getNombre());

		usuarioService.registrar(request);

		return ResponseEntity.ok("Usuario creado correctamente");
	}

	// 🔹 LOGIN
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

		log.info("Login usuario → Usuario: " + request.getUsuario());

		Usuario usuario = usuarioService.login(request.getUsuario(), request.getPassword());

		LoginResponse response = new LoginResponse(
				usuario.getId(),
				usuario.getUsuario(),
				usuario.getNombre()
		);

		return ResponseEntity.ok(response);
	}

	// 🔹 CHANGE PASSWORD
	@PutMapping("/change-password")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {

		log.info("Cambio contraseña → Usuario: " + request.getUsuario());

		usuarioService.changePassword(request);

		return ResponseEntity.ok("Contraseña actualizada correctamente");
	}
	
	@GetMapping("/{id}")
	public Usuario obtenerProducto(@PathVariable Long id) {
	    return usuarioRepository.findById(id).orElse(null);
	}
}