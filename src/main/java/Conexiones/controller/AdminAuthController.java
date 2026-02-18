package Conexiones.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Admin register(
            @RequestParam String nombre,
            @RequestParam String usuario,
            @RequestParam String password
    ) {
        return adminService.registrar(nombre, usuario, password);
    }

    @PostMapping("/login")
    public Admin login(
            @RequestParam String usuario,
            @RequestParam String password
    ) {
        return adminService.login(usuario, password);
    }
}

