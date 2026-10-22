CREATE TABLE tb_comentario (
    id BINARY(16) NOT NULL,
    data DATE NOT NULL,
    comentario TEXT NOT NULL,
    post_id BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_comentario_post FOREIGN KEY (post_id) REFERENCES tb_post(id)
);

CREATE INDEX idx_comentario_post_id ON tb_comentario(post_id);