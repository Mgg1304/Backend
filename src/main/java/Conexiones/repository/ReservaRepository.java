package Conexiones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Conexiones.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	List<Reserva> findByUsuarioId(Long usuarioId);

	List<Reserva> findByProductoId(Long productoId);

	List<Reserva> findByEstado(String estado);

	@Query("""
	        SELECT r
	        FROM Reserva r
	        JOIN FETCH r.producto p
	        WHERE p.admin.id = :adminId
	    """)
	    List<Reserva> obtenerReservasPorAdmin(@Param("adminId") Long adminId);
}
