package Conexiones.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import Conexiones.model.EstadoReserva;
import Conexiones.model.Reserva;
import Conexiones.service.ReservaService;

@RestController
@RequestMapping("/reservas")
@CrossOrigin
public class ReservaController {

	private final ReservaService reservaService;

	public ReservaController(ReservaService reservaService) {
		super();
		this.reservaService = reservaService;
	}

	@PostMapping
	public Reserva crear(@RequestBody Reserva reserva) {
		return reservaService.crearReserva(reserva);
	}

	@GetMapping
	public List<Reserva> obtenerTodas() {
		return reservaService.obtenerTodas();
	}

	@GetMapping("/usuario/{id}")
	public List<Reserva> porUsuario(@PathVariable Long id) {
		return reservaService.obtenerReservasUsuario(id);
	}

	@PutMapping("/{id}/estado")
	public Reserva cambiarEstado(@PathVariable Long id, @RequestParam EstadoReserva estado) {
		return reservaService.cambiarEstado(id, estado);
	}
}
