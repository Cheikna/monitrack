use monitrack;
DROP TABLE IF EXISTS LOCATION;
CREATE TABLE LOCATION(
    ID_LOCATION  INTEGER NOT NULL AUTO_INCREMENT,
   	CREATION_DATE timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    NAME         varchar(35),
    CENTER       varchar(35),
    ID_SENSOR    INTEGER NOT NULL,
    FLOOR    INTEGER NOT NULL,
    WING         varchar(35),
    AREA    INTEGER NOT NULL,
    PRIMARY KEY (`ID_LOCATION`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;