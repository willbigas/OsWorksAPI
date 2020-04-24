CREATE TABLE `osworks`.`ordem_servico`
(
    `id`               INT            NOT NULL AUTO_INCREMENT,
    `cliente_id`       BIGINT         NOT NULL,
    `descricao`        TEXT           NOT NULL,
    `preco`            DECIMAL(10, 2) NOT NULL,
    `status`           VARCHAR(20)    NOT NULL,
    `data_abertura`    DATETIME       NOT NULL,
    `data_finalizacao` DATETIME       NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

ALTER TABLE `osworks`.`ordem_servico`
    ADD CONSTRAINT `fk_ordem_servico_cliente`
        FOREIGN KEY (`cliente_id`)
            REFERENCES `osworks`.`cliente` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `osworks`.`ordem_servico`
    CHANGE COLUMN `cliente_id` `cliente_id` BIGINT NOT NULL AFTER `data_finalizacao`;



