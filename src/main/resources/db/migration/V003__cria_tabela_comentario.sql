CREATE TABLE `osworks`.`comentario`
(
    `id`               INT      NOT NULL AUTO_INCREMENT,
    `descricao`        TEXT     NULL,
    `data_envio`       DATETIME NULL,
    `ordem_servico_id` INT      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_comentario_ordem_servico_idx` (`ordem_servico_id` ASC) VISIBLE,
    CONSTRAINT `fk_comentario_ordem_servico`
        FOREIGN KEY (`ordem_servico_id`)
            REFERENCES `osworks`.`ordem_servico` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;