package Conexiones.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Conexiones.model.Producto;
import Conexiones.service.CloudinaryService;


@RestController
public class ProductoController {

    private final CloudinaryService cloudinaryService;
    
    private static final Logger log = Logger.getLogger(AdminAuthController.class.getName());

    public ProductoController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/renthub/productos/crear")
    public ResponseEntity<Producto> crearProducto(
            @RequestParam("files") MultipartFile[] file,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam String categoria,
            @RequestParam double precioDia,
            @RequestParam int stock,
            @RequestParam Long adminId
    ) throws IOException {

    	log.info("Recibida solicitud de creación de producto. Nombre: " + nombre + ", Descripción: " + descripcion + ", Categoría: " + categoria + ", Precio por día: " + precioDia + ", Stock: " + stock + ", Admin ID: " + adminId);
    	
        Producto producto = cloudinaryService.crearProductoConMultimedia(
                file, nombre, descripcion, categoria, precioDia, stock, adminId
        );

        return ResponseEntity.ok(producto);
    }

    @GetMapping("/renthub/productos/admin/{adminId}")
    public ResponseEntity<List<Producto>> obtenerProductosPorAdmin(@PathVariable Long adminId) {

    	log.info("Recibida solicitud de productos para admin ID: " + adminId);
    	
        List<Producto> productos = cloudinaryService.obtenerProductosPorAdmin(adminId);

        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/renthub/productos")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {

		log.info("Recibida solicitud de todos los productos.");
		
		List<Producto> productos = cloudinaryService.obtenerTodosLosProductos();

		return ResponseEntity.ok(productos);
	}
    
}
