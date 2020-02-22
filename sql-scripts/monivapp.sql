-- DROP DATABASE IF EXISTS `monivapp`;
-- CREATE DATABASE IF NOT EXISTS `monivapp`;
-- USE `monivapp`;
USE `ad_50bca53daa359fe`;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `user`;

SET FOREIGN_KEY_CHECKS=1;
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
('admin','$2a$10$7NtqYWm04eEennIPmp0DeOb2ul6hLPtLiwQzQ5Y0v1H5H7/cJ.qiu','Five','Six','admin@example.com'),
('cfvoter','$2a$10$5ni9ZafQIycxhU9oJXwLMOXYnq5QC5Jc2kspp.aSZQIffr8mQJlzW','Seven','Eight','cfvoter@example.com'),
('cfmaintainer','$2a$10$5ni9ZafQIycxhU9oJXwLMOXYnq5QC5Jc2kspp.aSZQIffr8mQJlzW','Nine','Ten','cfmaintainer@example.com'),
('cfadmin','$2a$10$5ni9ZafQIycxhU9oJXwLMOXYnq5QC5Jc2kspp.aSZQIffr8mQJlzW','Eleven','Twelve','cfadmin@example.com');

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `role`;

SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(42) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `role` (name) VALUES 
('ROLE_VOTER'),('ROLE_MAINTAINER'),('ROLE_ADMIN');

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `users_roles`;

SET FOREIGN_KEY_CHECKS=1;
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

SET FOREIGN_KEY_CHECKS=0;
INSERT INTO `users_roles` (user_id,role_id) VALUES
(2, 2),
(12, 12),
(22, 22),
(32, 2),
(42, 12),
(52, 22);

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `movie`;

SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `movie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(42) DEFAULT NULL,
  `votes` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO `movie` VALUES 
	(1,'The Matrix',2),
	(2,'The Matrix Reloaded',1),
	(3,'The Matrix Revolutions',0);
	
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `action`;

SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `action` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(42) DEFAULT NULL,
  `action` varchar(42) DEFAULT NULL,
  `movie_id` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- SET FOREIGN_KEY_CHECKS=0;
-- DROP TABLE IF EXISTS `users_roles`;

-- SET FOREIGN_KEY_CHECKS=1;
-- CREATE TABLE `users_roles` (
--   `user_id` int NOT NULL,
--   `role_id` int NOT NULL,  
--   PRIMARY KEY (`user_id`,`role_id`),
--   KEY `FK_ROLE_idx` (`role_id`),
--   CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
--   REFERENCES `user` (`id`) 
--   ON DELETE NO ACTION ON UPDATE NO ACTION,
--   CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
--   REFERENCES `role` (`id`) 
--   ON DELETE NO ACTION ON UPDATE NO ACTION
-- ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- SET FOREIGN_KEY_CHECKS=0;
-- INSERT INTO `users_roles` (user_id,role_id) VALUES
-- (1, 1),
-- (2, 2),
-- (3, 3),
-- (4, 1),
-- (5, 2),
-- (6, 3);

-- SET FOREIGN_KEY_CHECKS=0;
-- DROP TABLE IF EXISTS `users_roles`;

-- SET FOREIGN_KEY_CHECKS=1;
-- CREATE TABLE `users_roles` (
--   `user_id` int NOT NULL,
--   `role_id` int NOT NULL,  
--   PRIMARY KEY (`user_id`,`role_id`),
--   KEY `FK_ROLE_idx` (`role_id`),
--   CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
--   REFERENCES `user` (`id`) 
--   ON DELETE NO ACTION ON UPDATE NO ACTION,
--   CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
--   REFERENCES `role` (`id`) 
--   ON DELETE NO ACTION ON UPDATE NO ACTION
-- ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- SET FOREIGN_KEY_CHECKS=0;
-- INSERT INTO `users_roles` (user_id,role_id) VALUES
-- (2, 2),
-- (12, 12),
-- (22, 22),
-- (32, 2),
-- (42, 12),
-- (52, 22);