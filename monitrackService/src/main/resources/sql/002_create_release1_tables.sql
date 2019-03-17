create database monitrack;
use monitrack;
DROP TABLE IF EXISTS `PERSON`;
CREATE TABLE IF NOT EXISTS `PERSON` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `creation_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;