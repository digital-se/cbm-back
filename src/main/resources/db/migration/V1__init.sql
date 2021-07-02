CREATE TABLE "documentos" (
    "id" SERIAL NOT NULL,
    "nome" VARCHAR(255) NOT NULL,
    "numeracao" VARCHAR(255) NULL,
    "publico" BOOLEAN NOT NULL,
    "tipo" VARCHAR(255) NOT NULL,
    "data" TIMESTAMP NOT NULL,
    "descricao" TEXT NOT NULL,
    "criado" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "atualizado" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "arquivos" (
    "id" SERIAL NOT NULL,
    "documento_id" INT NOT NULL REFERENCES "documentos" ("id") ON UPDATE CASCADE ON DELETE RESTRICT,
    "nome" VARCHAR(255) NOT NULL,
    "ocr" BOOLEAN NOT NULL,
    "status" VARCHAR(255) NOT NULL,
    "mime" VARCHAR(255) NOT NULL,
    "tamanho" BIGINT NOT NULL,
    "dados" TEXT NOT NULL,
    "criado" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "atualizado" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "militares" (
    "matricula" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("matricula")
);

CREATE TABLE "fk_documento_militar" (
    "documento_id" INT NOT NULL REFERENCES "documentos" ("id") ON UPDATE CASCADE ON DELETE CASCADE,
    "militar_matricula" VARCHAR(255) NOT NULL  REFERENCES "militares" ("matricula") ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY ("documento_id", "militar_matricula")
);
