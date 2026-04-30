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

	@Column(name = "ruta_archivo")
	private String Url;

	private String tipo; // "IMAGEN" o "VIDEO"

	public ArchivoMultimedia() {
	}

	public ArchivoMultimedia( Producto producto, String Url, String tipo) {
		super();
		this.producto = producto;
		this.Url = Url;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String Url) {
		this.Url = Url;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
