CREATE TABLE tb_comanda_itens (
    id BIGSERIAL PRIMARY KEY,

    comanda_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,

    quantidade INTEGER NOT NULL,
    preco_unitario NUMERIC(10,2) NOT NULL,
    subtotal NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_item_comanda
        FOREIGN KEY (comanda_id)
        REFERENCES tb_comanda(id),

    CONSTRAINT fk_item_produto
        FOREIGN KEY (produto_id)
        REFERENCES tb_produto(id)
);