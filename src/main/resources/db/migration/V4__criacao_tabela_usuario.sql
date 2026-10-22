-- Migration versão 2 da Blog API.
-- UUID do Hibernate 6 é armazenado por padrão como BINARY(16) no MySQL.
-- SQL CODE.
CREATE TABLE TB_USUARIO(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE
);