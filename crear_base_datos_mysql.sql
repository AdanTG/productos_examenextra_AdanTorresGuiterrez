CREATE DATABASE IF NOT EXISTS productosdb
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE productosdb;

CREATE TABLE IF NOT EXISTS usuarios (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    username  VARCHAR(50)  NOT NULL UNIQUE,
    password  VARCHAR(100) NOT NULL,
    rol       VARCHAR(20)  NOT NULL
);

CREATE TABLE IF NOT EXISTS productos (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(150)   NOT NULL,
    descripcion    TEXT,
    precio         DECIMAL(12,2)  NOT NULL,
    existencia     INT            NOT NULL,
    categoria      VARCHAR(100)   NOT NULL,
    fecha_registro DATE
);

INSERT INTO usuarios (nombre, username, password, rol) VALUES
('Administrador del Sistema', 'admin',      'admin123',   'ADMINISTRADOR'),
('Usuario Capturista',        'capturista', 'captura123', 'CAPTURISTA');

INSERT INTO productos (nombre, descripcion, precio, existencia, categoria, fecha_registro) VALUES
('Laptop Lenovo ThinkPad',     'Laptop Core i7, 16GB RAM, 512GB SSD',     18500.00, 10, 'Computadoras',   CURDATE()),
('Monitor Dell 24"',           'Monitor Full HD IPS 75Hz',                 3200.50, 25, 'Monitores',      CURDATE()),
('Teclado Mecanico Redragon',  'Switches rojos, retroiluminacion RGB',      899.00, 40, 'Perifericos',    CURDATE()),
('Mouse Logitech MX Master 3', 'Mouse inalambrico ergonomico',             1750.00, 15, 'Perifericos',    CURDATE()),
('SSD Kingston 1TB',           'Unidad de estado solido NVMe M.2',         1450.99, 30, 'Almacenamiento', CURDATE());
