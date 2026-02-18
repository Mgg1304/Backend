package Conexiones.model;

import jakarta.persistence.*;

@Entity
@Table(name = "archivos_multimedia")
public class ArchivoMultimedia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_archivo_multimedia")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	private String ruta_archivo;

	private String tipo; // "IMAGEN" o "VIDEO"

	public ArchivoMultimedia() {
	}

	public ArchivoMultimedia(Producto producto, String ruta_archivo, String tipo) {
		this.producto = producto;
		this.ruta_archivo = ruta_archivo;
		this.tipo = tipo;
	}

	// Getters y setters
	public Long getId() {
		return id;
	}

	public Producto getId_producto() {
		return producto;
	}

	public void setId_producto(Producto producto) {
		this.producto = producto;
	}

	public String getRuta_archivo() {
		return ruta_archivo;
	}

	public void setRuta_archivo(String ruta_archivo) {
		this.ruta_archivo = ruta_archivo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
