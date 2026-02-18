package Conexiones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Conexiones.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	List<Reserva> findByUsuarioId(Long usuarioId);

	List<Reserva> findByProductoId(Long productoId);

	List<Reserva> findByEstado(String estado);

}
