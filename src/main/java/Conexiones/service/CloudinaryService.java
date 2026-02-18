package Conexiones.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import Conexiones.model.ArchivoMultimedia;
import Conexiones.model.Producto;
import Conexiones.repository.ArchivoMultimediaRepository;
import Conexiones.repository.ProductoRepository;

@Service
public class CloudinaryService {

	private final Cloudinary cloudinary;
	private final ProductoRepository productoRepository;
	private final ArchivoMultimediaRepository archivoRepository;

	public CloudinaryService(Cloudinary cloudinary, ProductoRepository productoRepository,
			ArchivoMultimediaRepository archivoRepository) {

		this.cloudinary = cloudinary;
		this.productoRepository = productoRepository;
		this.archivoRepository = archivoRepository;
	}

	public Producto crearProductoConMultimedia(MultipartFile[] files, String nombre, String descripcion,
			String categoria, double precioDia, int stock, Long adminId) throws IOException {

		
		Producto producto = new Producto(nombre, descripcion, categoria, precioDia, stock, adminId);
		producto = productoRepository.save(producto);

		 
		for (MultipartFile file : files) {

			Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

			String url = uploadResult.get("secure_url").toString();

			String tipo = file.getContentType().startsWith("video") ? "VIDEO" : "IMAGEN";

			ArchivoMultimedia archivo = new ArchivoMultimedia(producto, url, tipo);

			archivoRepository.save(archivo);
		}

		return producto;
	}

}
