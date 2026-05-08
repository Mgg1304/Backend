package Conexiones.dto;

import java.time.LocalDate;

public class ReservaAdminDTO {

    private Long reservaId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    private Long productoId;
    private String nombreProducto;
    private Double precioDia;
	
    public ReservaAdminDTO() {
    }
    
    public ReservaAdminDTO(Long reservaId, LocalDate fechaInicio, LocalDate fechaFin, String estado, Long productoId,
			String nombreProducto, Double precioDia) {
		super();
		this.reservaId = reservaId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.productoId = productoId;
		this.nombreProducto = nombreProducto;
		this.precioDia = precioDia;
	}

	public Long getReservaId() {
		return reservaId;
	}

	public void setReservaId(Long reservaId) {
		this.reservaId = reservaId;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getPrecioDia() {
		return precioDia;
	}

	public void setPrecioDia(Double precioDia) {
		this.precioDia = precioDia;
	}
    
    
}
