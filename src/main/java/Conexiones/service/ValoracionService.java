package Conexiones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	public Valoracion crear(Valoracion valoracion) {

        Long idProducto = valoracion.getProducto().getIdProducto();
        Long idUsuario = valoracion.getUsuario().getId();

        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        valoracion.setProducto(producto);
        valoracion.setUsuario(usuario);

        
        return valoracionRepository.save(valoracion);
    }

	public List<Valoracion> obtenerPorProducto(Long productoId) {
		return valoracionRepository.findByProductoId(productoId);
	}
}
