package Conexiones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Conexiones.model.ArchivoMultimedia;
import Conexiones.repository.ArchivoMultimediaRepository;

@Service
public class ArchivoMultimediaService {

	@Autowired
	private ArchivoMultimediaRepository repository;

	public List<String> obtenerUrlsPorProducto(Long productoId) {

		List<String> urls = repository.findByProductoId(productoId).stream().map(ArchivoMultimedia::getUrl).toList();
		return urls.stream()
		        .map(url -> url.replace("/upload/", "/upload/f_jpg/"))
		        .toList();
	}
}