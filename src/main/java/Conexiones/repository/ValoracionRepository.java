package Conexiones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Conexiones.model.Valoracion;

import java.util.List;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {

    List<Valoracion> findByProductoId(Long productoId);
}

