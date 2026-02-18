package Conexiones.service;

import org.springframework.stereotype.Service;

import java.util.List;

import Conexiones.model.Valoracion;
import Conexiones.repository.ValoracionRepository;

@Service
public class ValoracionService {

	private final ValoracionRepository valoracionRepository;

	public ValoracionService(ValoracionRepository valoracionRepository) {
		super();
		this.valoracionRepository = valoracionRepository;
	}

	public Valoracion crear(Valoracion valoracion) {
		return valoracionRepository.save(valoracion);
	}

	public List<Valoracion> obtenerPorProducto(Long productoId) {
		return valoracionRepository.findByProductoId(productoId);
	}
}
