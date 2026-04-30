package Conexiones.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
	
	private static final Logger log = Logger.getLogger(CloudinaryService.class.getName());

	public CloudinaryService(Cloudinary cloudinary, ProductoRepository productoRepository,
			ArchivoMultimediaRepository archivoRepository) {

		this.cloudinary = cloudinary;
		this.productoRepository = productoRepository;
		this.archivoRepository = archivoRepository;
	}

	public Producto crearProductoConMultimedia(MultipartFile[] files, String nombre, String descripcion,
			String categoria, double precioDia, int stock, Long adminId) throws IOException {

		log.info("Creando producto con multimedia. Nombre: " + nombre + ", Descripción: " + descripcion + ", Categoría: " + categoria + ", Precio por día: " + precioDia + ", Stock: " + stock + ", Admin ID: " + adminId);
		
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
	
	public List<Producto> obtenerProductosPorAdmin(Long adminId) {
		List<Producto> productos = productoRepository.findByAdminId(adminId);

		log.info("Obtenidos " + productos.size() + " productos para admin ID: " + adminId);
		log.info("Productos: " + productos.toString());
		
		return productos;
	}

	
	
	

}
