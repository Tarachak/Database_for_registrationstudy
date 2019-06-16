CREATE DATABASE `student_registration`;
USE `student_registration`;

CREATE TABLE `student`(
`id_student` int(4) NOT NULL AUTO_INCREMENT,
`name` varchar(45) DEFAULT NULL,
`surname` varchar(45) DEFAULT NULL,
PRIMARY KEY(`id_student`)
)ENGINE=InnoDB  DEFAULT CHARSET=latin1