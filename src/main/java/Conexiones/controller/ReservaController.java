package Conexiones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

import Conexiones.dto.ReservaRequest;
import Conexiones.model.EstadoReserva;
import Conexiones.model.Producto;
import Conexiones.model.Reserva;
import Conexiones.model.Usuario;
import Conexiones.repository.ProductoRepository;
import Conexiones.repository.UsuarioRepository;
import Conexiones.service.ReservaService;

@RestController
@RequestMapping("/renthub/reservas")
@CrossOrigin
public class ReservaController {

	private static final Logger log = Logger.getLogger(ReservaController.class.getName());

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProductoRepository productoRepository;

	public ReservaController(ReservaService reservaService) {
		super();
		this.reservaService = reservaService;
	}

	@PostMapping("/crear")
	public Reserva crear(@RequestBody ReservaRequest request) {

		Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Producto producto = productoRepository.findById(request.getProductoId())
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		Reserva reserva = new Reserva();
		reserva.setUsuario(usuario);
		reserva.setProducto(producto);
		reserva.setFechaInicio(request.getFechaInicio());
		reserva.setFechaFin(request.getFechaFin());

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

	@GetMapping("/admin/{adminId}")
	public ResponseEntity<List<Reserva>> obtenerReservasAdmin(@PathVariable Long adminId) {

		List<Reserva> reservas = reservaService.obtenerReservasAdmin(adminId);
		log.info("Reservas obtenidas para admin ID: " + adminId + ". Cantidad: " + (reservas != null ? reservas.size() : "null"));
		return ResponseEntity.ok(reservas);
	}
}
