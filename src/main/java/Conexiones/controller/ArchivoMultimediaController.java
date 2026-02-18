package Conexiones.controller;

import Conexiones.model.ArchivoMultimedia;
import Conexiones.repository.ArchivoMultimediaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/archivos")
@CrossOrigin(origins = "*")
public class ArchivoMultimediaController {

    private final ArchivoMultimediaRepository archivoRepository;

    public ArchivoMultimediaController(ArchivoMultimediaRepository archivoRepository) {
        this.archivoRepository = archivoRepository;
    }

    // Obtener todos
    @GetMapping
    public List<ArchivoMultimedia> getAllArchivos() {
        return archivoRepository.findAll();
    }

    // Obtener por producto
    @GetMapping("/producto/{idProducto}")
    public List<ArchivoMultimedia> getByProducto(@PathVariable Long productoId) {
        return archivoRepository.findByProductoId(productoId);
    }

    // Crear archivo multimedia
    @PostMapping
    public ArchivoMultimedia crearArchivo(@RequestBody ArchivoMultimedia archivo) {
        return archivoRepository.save(archivo);
    }

    // Eliminar archivo
    @DeleteMapping("/{id}")
    public void eliminarArchivo(@PathVariable Long id) {
        archivoRepository.deleteById(id);
    }
}

