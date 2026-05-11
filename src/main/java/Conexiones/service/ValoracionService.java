package Conexiones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import Conexiones.dto.ValoracionDTO;
import Conexiones.model.Producto;
import Conexiones.model.Usuario;
import Conexiones.model.Valoracion;
import Conexiones.repository.ProductoRepository;
import Conexiones.repository.UsuarioRepository;
import Conexiones.repository.ValoracionRepository;

@Service
public class ValoracionService {

	@Autowired
	private ValoracionRepository valoracionRepository;
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public Valoracion crear(ValoracionDTO request) {

		Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Producto producto = productoRepository.findById(request.getIdProducto())
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Valoracion valoracion = new Valoracion();
        valoracion.setComentario(request.getComentario());
        valoracion.setEstrellas(request.getEstrellas());
        valoracion.setUsuario(usuario);
        valoracion.setProducto(producto);

        Valoracion valoracionGuardada = valoracionRepository.save(valoracion);
        int filasActualizadas = productoRepository.actualizarRatingProducto(producto.getIdProducto(), request.getEstrellas());

        if (filasActualizadas == 0) {
			throw new RuntimeException("No se pudo actualizar la valoración media del producto");
		}

        return valoracionGuardada;
    }

	public List<Valoracion> obtenerPorProducto(Long productoId) {
		return valoracionRepository.findByProductoId(productoId);
	}
}
