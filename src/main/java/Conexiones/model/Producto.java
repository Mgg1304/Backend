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

	@Column(name = "id_admin")
	private Long adminId;

	@Column(name = "rating_count", nullable = false)
	private int ratingCount;

	@Column(name = "rating_sum", nullable = false)
	private double ratingSum;

	@Column(name = "rating_avg", nullable = false)
	private double ratingAvg;
	

	public Producto() {
	}

	public Producto(String nombre, String descripcion, String categoria, double precioDia, int stock, Long id_admin) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.precioDia = precioDia;
		this.stock = stock;
		this.adminId = id_admin;
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

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public int getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public double getRatingSum() {
		return ratingSum;
	}

	public void setRatingSum(double ratingSum) {
		this.ratingSum = ratingSum;
	}

	public double getRatingAvg() {
		return ratingAvg;
	}

	public void setRatingAvg(double ratingAvg) {
		this.ratingAvg = ratingAvg;
	}
	
	@Override
	public String toString() {
	    return "Producto{" +
	            "id=" + id +
	            ", nombre='" + nombre + '\'' +
	            ", precioPorDia=" + precioDia +
	            ", ratingAvg=" + ratingAvg +
	            '}';
	}
}
