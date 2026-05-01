CREATE TABLE IF NOT EXISTS administradores(
	id_admin BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE,
    usuario VARCHAR(100),
    contrasenya VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS usuarios(
	id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE,
    usuario VARCHAR(50),
    contrasenya VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS productos(
    id_producto BIGINT AUTO_INCREMENT PRIMARY KEY,
    precio_dia DECIMAL(10, 2),
    categoria VARCHAR(50),
    stock INT,
    nombre VARCHAR(50),
    id_admin BIGINT,
    descripcion VARCHAR(200),
    FOREIGN KEY (id_admin) REFERENCES administradores(id_admin)
);

CREATE TABLE IF NOT EXISTS reservas(
	id_reserva BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT,
    id_producto BIGINT,
    estado VARCHAR(50),
    fecha_inicio DATE,
    fecha_fin DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE IF NOT EXISTS notificaciones(
	id_notificacion BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_reserva BIGINT,
    id_usuario BIGINT,
    tipo VARCHAR(50),
    mensaje VARCHAR(200),
    fecha DATETIME,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_reserva) REFERENCES reservas(id_reserva)
);

CREATE TABLE IF NOT EXISTS valoraciones(
	id_valoracion BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_producto BIGINT,
    id_usuario BIGINT,
	comentario VARCHAR(200),
    estrellas INT CHECK,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE IF NOT EXISTS archivos_multimedia(
	id_archivo_multimedia BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_producto BIGINT,
    tipo VARCHAR(50),
    ruta_archivo TEXT,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);