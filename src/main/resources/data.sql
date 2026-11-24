INSERT IGNORE INTO tipo_usuarios (id, tipo_usuario) VALUES
    (1, 'Administrador'), (2, 'Agente'), (3, 'Supervisor');

INSERT IGNORE INTO users (name, email, password, profile_image_uri, tipo_usuario_id)
VALUES ('Administrador', 'admin@duoc.cl', 'admin123', NULL, 1);
