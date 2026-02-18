package Conexiones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import Conexiones.model.ArchivoMultimedia;

public interface ArchivoMultimediaRepository extends JpaRepository<ArchivoMultimedia, Long> {


	List<ArchivoMultimedia> findByProductoId(Long productoId);
}
