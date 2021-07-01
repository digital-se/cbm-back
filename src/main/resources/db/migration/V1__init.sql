CREATE TABLE `documentos` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(255) NOT NULL,
    `numeracao` VARCHAR(255) NULL,
    `publico` BOOLEAN NOT NULL,
    `tipo` VARCHAR(255) NOT NULL,
    `data` TIMESTAMP NOT NULL,
    `descricao` TEXT NOT NULL,
    `criado` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `atualizado` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `arquivos` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `documento_id` INT NOT NULL,
    `nome` VARCHAR(255) NOT NULL,
    `mime` VARCHAR(255) NOT NULL,
    `tamanho` BIGINT NOT NULL,
    `dados` TEXT NOT NULL,
    `criado` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `atualizado` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `arquivos`
    ADD CONSTRAINT `fk_documento_arquivo`
    FOREIGN KEY (`documento_id`)
    REFERENCES `documentos`(`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
;

CREATE TABLE `militares` (
    `matricula` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`matricula`)
);

CREATE TABLE `fk_documento_militar` (
    `documento_id` INT NOT NULL,
    `militar_matricula` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`documento_id`, `militar_matricula`)
);

ALTER TABLE `fk_documento_militar` 
    ADD CONSTRAINT `fk_documento_militar` 
    FOREIGN KEY (`documento_id`) 
    REFERENCES `documentos`(`id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
;

ALTER TABLE `fk_documento_militar` 
    ADD CONSTRAINT `fk_militar_documento` 
    FOREIGN KEY (`militar_matricula`) 
    REFERENCES `militares`(`matricula`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
;
