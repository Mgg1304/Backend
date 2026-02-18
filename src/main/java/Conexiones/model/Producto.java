package Conexiones.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private Long id;

	private String nombre;

	@Column(length = 500)
	private String descripcion;

	private String categoria;

	private double precioDia;

	private int stock;

	private Long id_admin;

	public Producto() {
	}

	public Producto(String nombre, String descripcion, String categoria, double precioDia, int stock, Long id_admin) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.precioDia = precioDia;
		this.stock = stock;
		this.id_admin = id_admin;
	}

	// Getters y setters
	public Long getIdProducto() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecioDia() {
		return precioDia;
	}

	public void setPrecioDia(double precioDia) {
		this.precioDia = precioDia;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Long getId_Admin() {
		return id_admin;
	}

	public void setId_Admin(Long id_admin) {
		this.id_admin = id_admin;
	}
}
