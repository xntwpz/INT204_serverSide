CREATE SCHEMA IF NOT EXISTS `simple-rbac-java-servlet` DEFAULT CHARACTER SET utf8mb4 ;
USE `simple-rbac-java-servlet` ;

CREATE TABLE IF NOT EXISTS `simple-rbac-java-servlet`.`users` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50)
);