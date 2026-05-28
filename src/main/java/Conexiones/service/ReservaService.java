package Conexiones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import Conexiones.exception.ReservaInvalidaException;
import Conexiones.model.EstadoReserva;
import Conexiones.model.Producto;
import Conexiones.model.Reserva;
import Conexiones.repository.ProductoRepository;
import Conexiones.repository.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private ProductoRepository productoRepository;

	public ReservaService(ReservaRepository reservaRepository) {
		super();
		this.reservaRepository = reservaRepository;
	}

	private static final List<EstadoReserva> ESTADOS_QUE_BLOQUEAN = Arrays.asList(
			EstadoReserva.CONFIRMADA,
			EstadoReserva.EN_CURSO
	);

	private void validarFechas(LocalDate inicio, LocalDate fin) {

		if (inicio == null || fin == null) {
			throw new IllegalArgumentException("Las fechas no pueden ser nulas");
		}

		if (inicio.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("No se puede reservar en fechas pasadas");
		}

		if (!fin.isAfter(inicio)) {
			throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio");
		}

		long dias = ChronoUnit.DAYS.between(inicio, fin);

		if (dias > 30) {
			throw new IllegalArgumentException("No se permiten reservas superiores a 30 días");
		}
	}

	public List<Reserva> obtenerReservasUsuario(Long usuarioId) {
		return reservaRepository.findByUsuarioId(usuarioId);
	}

	public List<Reserva> obtenerTodas() {
		return reservaRepository.findAll();
	}

	public List<Reserva> obtenerReservasAdmin(Long adminId) {
		return reservaRepository.obtenerReservasPorAdmin(adminId);
	}

	public void actualizarReservasEnCurso() {
		reservaRepository.actualizarReservasEnCurso();
	}

	@Transactional
	public void finalizarReservasVencidas() {
		List<Reserva> reservasVencidas = reservaRepository.findByEstadoAndFechaFinLessThanEqual(
				EstadoReserva.EN_CURSO,
				LocalDate.now()
		);

		for (Reserva reserva : reservasVencidas) {
			Producto producto = reserva.getProducto();
			producto.setStock(producto.getStock() + 1);
			productoRepository.save(producto);
			reserva.setProducto(producto);
			reserva.setEstado(EstadoReserva.FINALIZADA);
		}

		if (!reservasVencidas.isEmpty()) {
			reservaRepository.saveAll(reservasVencidas);
		}
	}

	@Transactional
	public Reserva crearReserva(Reserva reserva) {
		validarFechas(reserva.getFechaInicio(), reserva.getFechaFin());

		Producto producto = productoRepository.findById(reserva.getProducto().getIdProducto())
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		long reservasSolapadas = reservaRepository.contarReservasSolapadas(
				producto.getIdProducto(),
				reserva.getFechaInicio(),
				reserva.getFechaFin(),
				ESTADOS_QUE_BLOQUEAN
		);

		if (reservasSolapadas >= producto.getStock()) {
			throw new ReservaInvalidaException("No hay stock disponible para el periodo seleccionado");
		}

		reserva.setEstado(EstadoReserva.PENDIENTE);
		reserva.setProducto(producto);
		return reservaRepository.save(reserva);
	}

	@Transactional
	public Reserva confirmarReserva(Long id) {
		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

		if (reserva.getEstado() != EstadoReserva.PENDIENTE) {
			throw new ReservaInvalidaException("Solo se pueden confirmar reservas pendientes");
		}

		Producto producto = productoRepository.findById(reserva.getProducto().getIdProducto())
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		long reservasSolapadas = reservaRepository.contarReservasSolapadas(
				producto.getIdProducto(),
				reserva.getFechaInicio(),
				reserva.getFechaFin(),
				ESTADOS_QUE_BLOQUEAN
		);

		if (reservasSolapadas >= producto.getStock()) {
			throw new ReservaInvalidaException("No hay stock disponible para confirmar esta reserva");
		}

		if (producto.getStock() <= 0) {
			throw new ReservaInvalidaException("No hay stock disponible para confirmar esta reserva");
		}

		producto.setStock(producto.getStock() - 1);
		productoRepository.save(producto);

		reserva.setEstado(EstadoReserva.CONFIRMADA);
		reserva.setProducto(producto);
		return reservaRepository.save(reserva);
	}

	@Transactional
	public Reserva cancelarReserva(Long id) {
		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

		if (reserva.getEstado() == EstadoReserva.CANCELADA || reserva.getEstado() == EstadoReserva.FINALIZADA) {
			throw new ReservaInvalidaException("La reserva ya no se puede cancelar");
		}

		if (reserva.getEstado() == EstadoReserva.CONFIRMADA || reserva.getEstado() == EstadoReserva.EN_CURSO) {
			Producto producto = reserva.getProducto();
			producto.setStock(producto.getStock() + 1);
			productoRepository.save(producto);
			reserva.setProducto(producto);
		}

		reserva.setEstado(EstadoReserva.CANCELADA);
		return reservaRepository.save(reserva);
	}
	
	@Transactional
	public Reserva finalizarReserva(Long id) {
		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

		if (reserva.getEstado() != EstadoReserva.CONFIRMADA && reserva.getEstado() != EstadoReserva.EN_CURSO) {
			throw new ReservaInvalidaException("Solo se pueden finalizar reservas confirmadas o en curso");
		}

		Producto producto = reserva.getProducto();
		producto.setStock(producto.getStock() + 1);
		productoRepository.save(producto);
		reserva.setProducto(producto);

		reserva.setEstado(EstadoReserva.FINALIZADA);
		reserva.setFechaFin(LocalDate.now());
		return reservaRepository.save(reserva);
	}
}
