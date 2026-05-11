package Conexiones.dto;

public class ValoracionDTO {
	
	private String comentario;
	private double estrellas;
	private Long idUsuario;
	private Long idProducto;

	public ValoracionDTO() {
	}

	public ValoracionDTO(String comentario, double estrellas, Long idUsuario, Long idProducto) {
		this.comentario = comentario;
		this.estrellas = estrellas;
		this.idUsuario = idUsuario;
		this.idProducto = idProducto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public double getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(double estrellas) {
		this.estrellas = estrellas;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
}
