CREATE SEQUENCE SEQ_ROTAS
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE TBL_ROTAS(
    ID INTEGER DEFAULT SEQ_ROTAS.NEXTVAL NOT NULL,
    DESCRICAO VARCHAR(200),
    DATA DATE
);