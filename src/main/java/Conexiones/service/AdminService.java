package Conexiones.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Conexiones.model.Admin;
import Conexiones.repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    
    public Admin registrar(String nombre, String usuario, String password) {

        if (adminRepository.findByUsuario(usuario).isPresent()) {
            throw new RuntimeException("El administrador ya existe");
        }

        Admin admin = new Admin(
                nombre,
                usuario,
//                encoder.encode(password)
                password
        );

        return adminRepository.save(admin);
    }


    public Admin login(String usuario, String password) {

        Admin admin = adminRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));

        if (!encoder.matches(password, admin.getContrasenya())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return admin;
    }
}
