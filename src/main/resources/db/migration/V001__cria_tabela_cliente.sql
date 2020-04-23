CREATE TABLE `osworks`.`cliente`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `nome`     VARCHAR(45) NOT NULL,
    `email`    VARCHAR(45) NOT NULL,
    `telefone` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;