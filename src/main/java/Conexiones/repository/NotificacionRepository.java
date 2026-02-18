package Conexiones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Conexiones.model.Notificacion;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByUsuarioId(Long usuarioId);
}
