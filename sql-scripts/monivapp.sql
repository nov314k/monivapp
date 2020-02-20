DROP DATABASE IF EXISTS `monivapp`;
CREATE DATABASE IF NOT EXISTS `monivapp`;
USE `monivapp`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(42) NOT NULL,
  `password` char(84) NOT NULL,
  `first_name` varchar(42) NOT NULL,
  `last_name` varchar(42) NOT NULL,
  `email` varchar(42) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `user` (username,password,first_name,last_name,email) VALUES 
('voter','$2a$10$7NtqYWm04eEennIPmp0DeOb2ul6hLPtLiwQzQ5Y0v1H5H7/cJ.qiu','One','Two','voter@example.com'),
('maintainer','$2a$10$7NtqYWm04eEennIPmp0DeOb2ul6hLPtLiwQzQ5Y0v1H5H7/cJ.qiu','Three','Four','maintainer@example.com'),
('admin','$2a$10$7NtqYWm04eEennIPmp0DeOb2ul6hLPtLiwQzQ5Y0v1H5H7/cJ.qiu','Five','Six','admin@example.com');

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(42) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `role` (name) VALUES 
('ROLE_VOTER'),('ROLE_MAINTAINER'),('ROLE_ADMIN');

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,  
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_ROLE_idx` (`role_id`),
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `users_roles` (user_id,role_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3);

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(42) DEFAULT NULL,
  `last_name` varchar(42) DEFAULT NULL,
  `email` varchar(42) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO `customer` VALUES 
	(1,'Customer','Abc','abc@customer.com'),
	(2,'Customer','Def','def@customer.com'),
	(3,'Customer','Ghi','ghi@customer.com'),
	(4,'Customer','Jkl','jkl@customer.com'),
	(5,'Customer','Mno','mno@customer.com');