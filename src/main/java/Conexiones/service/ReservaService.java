package Conexiones.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import Conexiones.model.Reserva;
import Conexiones.repository.ReservaRepository;

@Service
public class ReservaService {

	private final ReservaRepository reservaRepository;

	public ReservaService(ReservaRepository reservaRepository) {
		super();
		this.reservaRepository = reservaRepository;
	}

	public Reserva crearReserva(Reserva reserva) {
		validarFechas(reserva.getFechaInicio(), reserva.getFechaFin());
		reserva.setEstado("PENDIENTE");
		return reservaRepository.save(reserva);
	}

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

	public Reserva cambiarEstado(Long id, String estado) {
		Reserva reserva = reservaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

		reserva.setEstado(estado);
		return reservaRepository.save(reserva);
	}
}
