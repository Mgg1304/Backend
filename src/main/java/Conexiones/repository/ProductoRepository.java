package Conexiones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Conexiones.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	List<Producto> findByAdminId(Long adminId);
	
	List<Producto> findAll();

	@Modifying
	@Query("""
			UPDATE Producto p
			SET p.ratingCount = p.ratingCount + 1,
			    p.ratingSum = p.ratingSum + :estrellas,
			    p.ratingAvg = (p.ratingSum + :estrellas) / (p.ratingCount + 1)
			WHERE p.id = :productoId
			""")
	int actualizarRatingProducto(@Param("productoId") Long productoId, @Param("estrellas") double estrellas);
	
}
