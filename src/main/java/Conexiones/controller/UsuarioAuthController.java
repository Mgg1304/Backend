package Conexiones.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Conexiones.model.Usuario;
import Conexiones.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioAuthController {

	private final UsuarioService usuarioService;

	public UsuarioAuthController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("/register")
	public Usuario register(@RequestParam String nombre, @RequestParam String usuario, @RequestParam String password) {
		return usuarioService.registrar(nombre, usuario, password);
	}

	@PostMapping("/login")
	public Usuario login(@RequestParam String usuario, @RequestParam String password) {
		return usuarioService.login(usuario, password);
	}
}
