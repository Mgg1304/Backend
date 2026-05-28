package Conexiones.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import Conexiones.model.EstadoReserva;
import Conexiones.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	List<Reserva> findByUsuarioId(Long usuarioId);

	List<Reserva> findByProductoId(Long productoId);

	List<Reserva> findByEstado(EstadoReserva estado);

	List<Reserva> findByEstadoAndFechaFinLessThanEqual(EstadoReserva estado, LocalDate fechaFin);

	@Query("""
			SELECT COUNT(r)
			FROM Reserva r
			WHERE r.producto.id = :productoId
			AND r.estado IN :estados
			AND r.fechaInicio < :fechaFin
			AND r.fechaFin > :fechaInicio
		""")
	long contarReservasSolapadas(@Param("productoId") Long productoId,
						 @Param("fechaInicio") LocalDate fechaInicio,
						 @Param("fechaFin") LocalDate fechaFin,
						 @Param("estados") List<EstadoReserva> estados);

	@Query("""
	        SELECT r
	        FROM Reserva r
	        JOIN FETCH r.producto p
	        WHERE p.adminId = :adminId
	    """)
	    List<Reserva> obtenerReservasPorAdmin(@Param("adminId") Long adminId);
	
	@Modifying
	@Transactional
	@Query("""
	    UPDATE Reserva r
	    SET r.estado = 'EN_CURSO'
	    WHERE r.fechaInicio <= CURRENT_DATE
	    AND r.fechaFin > CURRENT_DATE
	    AND r.estado = 'CONFIRMADA'
	""")
	void actualizarReservasEnCurso();
}
