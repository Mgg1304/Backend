package Conexiones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import Conexiones.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	List<Producto> findByAdminId(Long adminId);
	
	List<Producto> findAll();
	
}
