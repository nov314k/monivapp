USE `monivapp`;

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