package Conexiones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Conexiones.model.ArchivoMultimedia;

public interface ArchivoMultimediaRepository extends JpaRepository<ArchivoMultimedia, Long> {
	
	@Query("SELECT a FROM ArchivoMultimedia a WHERE a.producto.id = :productoId")
	List<ArchivoMultimedia> findByProductoId(@Param("productoId") Long productoId);
}
