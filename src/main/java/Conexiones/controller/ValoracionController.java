package Conexiones.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import Conexiones.model.Valoracion;
import Conexiones.service.ValoracionService;

@RestController
@RequestMapping("/valoraciones")
@CrossOrigin
public class ValoracionController {

	private final ValoracionService valoracionService;

	public ValoracionController(ValoracionService valoracionService) {
		super();
		this.valoracionService = valoracionService;
	}

	@PostMapping
	public Valoracion crear(@RequestBody Valoracion valoracion) {
		return valoracionService.crear(valoracion);
	}

	@GetMapping("/producto/{id}")
	public List<Valoracion> porProducto(@PathVariable Long id) {
		return valoracionService.obtenerPorProducto(id);
	}
}
