package Conexiones.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import Conexiones.model.Notificacion;
import Conexiones.service.NotificacionService;

@RestController
@RequestMapping("/notificaciones")
@CrossOrigin
public class NotificacionController {

	private final NotificacionService notificacionService;

	public NotificacionController(NotificacionService notificacionService) {
		super();
		this.notificacionService = notificacionService;
	}

	@PostMapping
	public Notificacion crear(@RequestBody Notificacion notificacion) {
		return notificacionService.crear(notificacion);
	}

	@GetMapping("/usuario/{id}")
	public List<Notificacion> porUsuario(@PathVariable Long id) {
		return notificacionService.obtenerPorUsuario(id);
	}
}
