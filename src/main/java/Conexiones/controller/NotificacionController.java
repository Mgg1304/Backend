package Conexiones.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

import Conexiones.model.Notificacion;
import Conexiones.service.NotificacionService;

@RestController
@RequestMapping("/renthub/notificaciones")
@CrossOrigin
public class NotificacionController {
	
	private static final Logger log = Logger.getLogger(NotificacionController.class.getName());

	private final NotificacionService notificacionService;

	public NotificacionController(NotificacionService notificacionService) {
		super();
		this.notificacionService = notificacionService;
	}

	@PostMapping("/crear")
	public Notificacion crear(@RequestBody Notificacion notificacion) {
		return notificacionService.crear(notificacion);
	}

	@GetMapping("/usuario/{id}")
	public List<Notificacion> porUsuario(@PathVariable Long id) {
		return notificacionService.obtenerPorUsuario(id);
	}
}
