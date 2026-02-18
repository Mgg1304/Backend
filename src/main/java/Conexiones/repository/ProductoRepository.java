package Conexiones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Conexiones.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
