package Conexiones.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

import Conexiones.model.Valoracion;
import Conexiones.service.ValoracionService;

@RestController
@RequestMapping("/renthub/valoraciones")
@CrossOrigin
public class ValoracionController {
	
	private static final Logger log = Logger.getLogger(ValoracionController.class.getName());

	private final ValoracionService valoracionService;

	public ValoracionController(ValoracionService valoracionService) {
		super();
		this.valoracionService = valoracionService;
	}

	@PostMapping("/create")
	public Valoracion crear(@RequestBody Valoracion valoracion) {
		log.info("Recibida solicitud de creación de valoración. Usuario ID: " + valoracion.getUsuario().getId() + ", Producto ID: " + valoracion.getProducto().getIdProducto() + ", Puntuación: " + valoracion.getEstrellas() + ", Comentario: " + valoracion.getComentario());
		return valoracionService.crear(valoracion);
	}
	
	@GetMapping("/producto/{id}")
	public List<Valoracion> porProducto(@PathVariable Long id) {
		log.info("Recibida solicitud de obtener valoraciones por producto. Producto ID: " + id);
		return valoracionService.obtenerPorProducto(id);
	}
}
