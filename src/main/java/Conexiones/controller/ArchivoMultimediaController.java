package Conexiones.controller;

import Conexiones.model.ArchivoMultimedia;
import Conexiones.repository.ArchivoMultimediaRepository;
import Conexiones.service.ArchivoMultimediaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/renthub/archivos")
@CrossOrigin(origins = "*")
public class ArchivoMultimediaController {

	private static final Logger log = Logger.getLogger(ArchivoMultimediaController.class.getName());

	private final ArchivoMultimediaRepository archivoRepository;
	
	@Autowired
    private ArchivoMultimediaService service;

	public ArchivoMultimediaController(ArchivoMultimediaRepository archivoRepository) {
		this.archivoRepository = archivoRepository;
	}

	// Obtener por producto
	@GetMapping("/producto/{id}")
	public ResponseEntity<List<String>> obtenerUrls(@PathVariable Long id) {
		log.info("Recibida solicitud de obtención de URLs de archivos multimedia para producto ID: " + id);
        return ResponseEntity.ok(service.obtenerUrlsPorProducto(id));
    }

	// Crear archivo multimedia
	@PostMapping("/crear")
	public ArchivoMultimedia crearArchivo(@RequestBody ArchivoMultimedia archivo) {
		log.info("Recibida solicitud de creación de archivo multimedia. URL: " + archivo.getUrl() + ", Tipo: "
				+ archivo.getTipo() + ", Producto ID: " + archivo.getId());
		return archivoRepository.save(archivo);
	}

	// Eliminar archivo
	@DeleteMapping("/eliminar")
	public void eliminarArchivo(@PathVariable Long id) {
		log.info("Recibida solicitud de eliminación de archivo multimedia. ID: " + id);
		archivoRepository.deleteById(id);
	}
}
