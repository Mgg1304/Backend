package Conexiones.controller;

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
@RequestMapping("/api/admin")
public class AdminAuthController {

	private final AdminService adminService;

	public AdminAuthController(AdminService adminService) {
		this.adminService = adminService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
	    
	    adminService.registrar(request);

	    return ResponseEntity.ok("Usuario creado correctamente");
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

	    Admin admin = adminService.login(request.getUsuario(), request.getPassword());
	    
	    LoginResponse response = new LoginResponse(
	            admin.getId(),
	            admin.getUsuario(),
	            admin.getNombre()
	    );

	    return ResponseEntity.ok(response);
	}

	@PutMapping("/change-password")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {

	    adminService.changePassword(request);

	    return ResponseEntity.ok("Contraseña actualizada correctamente");
	}

}
