package Conexiones.controller;

import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Conexiones.dto.ChangePasswordRequest;
import Conexiones.dto.LoginRequest;
import Conexiones.dto.LoginResponse;
import Conexiones.dto.RegisterRequest;
import Conexiones.model.Admin;
import Conexiones.service.AdminService;

@RestController
@RequestMapping("/renthub/admin")
public class AdminAuthController {
	
	private static final Logger log = Logger.getLogger(AdminAuthController.class.getName());

	private final AdminService adminService;

	public AdminAuthController(AdminService adminService) {
		this.adminService = adminService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
		
		log.info("Recibida solicitud de registro. Usuario: " + request.getUsuario() + ", Nombre: " + request.getNombre() + ", Contraseña: " + request.getPassword());

		adminService.registrar(request);

		return ResponseEntity.ok("Usuario creado correctamente");
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		
		log.info("Recibida solicitud de login. Usuario: " + request.getUsuario() + ", Contraseña: " + request.getPassword());

		Admin admin = adminService.login(request.getUsuario(), request.getPassword());

		LoginResponse response = new LoginResponse(admin.getId(), admin.getUsuario(), admin.getNombre());

		return ResponseEntity.ok(response);
	}

	@PutMapping("/change-password")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
		
		log.info("Recibida solicitud de cambio de contraseña. Usuario: " + request.getUsuario() + ", Contraseña actual: " + request.getOldPassword() + ", Nueva contraseña: " + request.getNewPassword());

		adminService.changePassword(request);

		return ResponseEntity.ok("Contraseña actualizada correctamente");
	}

}
