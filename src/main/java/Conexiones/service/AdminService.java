package Conexiones.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Conexiones.dto.ChangePasswordRequest;
import Conexiones.dto.RegisterRequest;
import Conexiones.model.Admin;
import Conexiones.repository.AdminRepository;

@Service
public class AdminService {

	private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	public void registrar(RegisterRequest request) {

		if (adminRepository.findByUsuario(request.getUsuario()).isPresent()) {
			throw new RuntimeException("El usuario ya está registrado");
		}

		Admin admin = new Admin();
		admin.setNombre(request.getNombre());
		admin.setUsuario(request.getUsuario());
		admin.setContrasenya(encoder.encode(request.getPassword()));

		adminRepository.save(admin);
	}

	public Admin login(String usuario, String password) {

		Admin admin = adminRepository.findByUsuario(usuario)
				.orElseThrow(() -> new RuntimeException("Admin no encontrado"));

		if (password != admin.getContrasenya()) {
			throw new RuntimeException("Contraseña incorrecta");
		}

		return admin;
	}
	
	public void changePassword(ChangePasswordRequest request) {

	    Admin admin = adminRepository.findByUsuario(request.getUser())
	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

	    if (!encoder.matches(request.getOldPassword(), admin.getContrasenya())) {
	        throw new RuntimeException("La contraseña actual es incorrecta");
	    }

	    admin.setContrasenya(encoder.encode(request.getNewPassword()));
	    adminRepository.save(admin);
	}

}
