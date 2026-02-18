package Conexiones.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Conexiones.model.Producto;
import Conexiones.service.CloudinaryService;

@RestController
public class ProductoController {

    private final CloudinaryService cloudinaryService;

    public ProductoController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/api/productos")
    public ResponseEntity<Producto> crearProducto(
            @RequestParam("files") MultipartFile[] file,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam String categoria,
            @RequestParam double precioDia,
            @RequestParam int stock,
            @RequestParam Long adminId
    ) throws IOException {

        Producto producto = cloudinaryService.crearProductoConMultimedia(
                file, nombre, descripcion, categoria, precioDia, stock, adminId
        );

        return ResponseEntity.ok(producto);
    }
}
