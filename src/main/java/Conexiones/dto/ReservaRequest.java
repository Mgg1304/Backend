package Conexiones.dto;

import java.time.LocalDate;

public class ReservaRequest {
    private Long usuarioId;
    private Long productoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
	
    public ReservaRequest(Long usuarioId, Long productoId, LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.usuarioId = usuarioId;
		this.productoId = productoId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
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
    
    
}