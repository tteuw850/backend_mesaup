CREATE TABLE tb_comanda (
    id BIGSERIAL PRIMARY KEY,

    mesa_id BIGINT NOT NULL,
    usuario_id BIGINT,

    status VARCHAR(20) NOT NULL,

    total NUMERIC(10,2) DEFAULT 0,

    data_criacao TIMESTAMP NOT NULL,
    data_fechamento TIMESTAMP,

    CONSTRAINT fk_comanda_mesa
        FOREIGN KEY (mesa_id)
        REFERENCES tb_mesa(id),

    CONSTRAINT fk_comanda_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES tb_usuario(id)
);