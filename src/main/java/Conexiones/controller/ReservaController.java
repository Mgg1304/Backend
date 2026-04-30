package Conexiones.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

import Conexiones.model.EstadoReserva;
import Conexiones.model.Reserva;
import Conexiones.service.ReservaService;

@RestController
@RequestMapping("/renthub/reservas")
@CrossOrigin
public class ReservaController {
	
	private static final Logger log = Logger.getLogger(ReservaController.class.getName());

	private final ReservaService reservaService;

	public ReservaController(ReservaService reservaService) {
		super();
		this.reservaService = reservaService;
	}

	@PostMapping("/crear")
	public Reserva crear(@RequestBody Reserva reserva) {
		log.info("Recibida solicitud de creación de reserva. Usuario ID: " + reserva.getUsuario().getNombre() + ", Producto ID: " + reserva.getProducto().getNombre() + ", Fecha inicio: " + reserva.getFechaInicio() + ", Fecha fin: " + reserva.getFechaFin());
		return reservaService.crearReserva(reserva);
	}

	@GetMapping("/usuario/{id}")
	public List<Reserva> porUsuario(@PathVariable Long id) {
		log.info("Recibida solicitud de obtención de reservas para usuario ID: " + id);
		return reservaService.obtenerReservasUsuario(id);
	}

	@PutMapping("/{id}/estado")
	public Reserva cambiarEstado(@PathVariable Long id, @RequestParam EstadoReserva estado) {
		log.info("Recibida solicitud de cambio de estado de reserva ID: " + id + " a estado: " + estado);
		return reservaService.cambiarEstado(id, estado);
	}
}
