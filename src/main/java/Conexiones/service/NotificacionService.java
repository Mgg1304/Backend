package Conexiones.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import Conexiones.model.Notificacion;
import Conexiones.repository.NotificacionRepository;

@Service
public class NotificacionService {

	private final NotificacionRepository notificacionRepository;

	public NotificacionService(NotificacionRepository notificacionRepository) {
		super();
		this.notificacionRepository = notificacionRepository;
	}

	public Notificacion crear(Notificacion notificacion) {
		notificacion.setFecha(LocalDateTime.now());
		return notificacionRepository.save(notificacion);
	}

	public List<Notificacion> obtenerPorUsuario(Long usuarioId) {
		return notificacionRepository.findByUsuarioId(usuarioId);
	}
}
