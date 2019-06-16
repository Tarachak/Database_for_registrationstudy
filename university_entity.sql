use `student_registration`;

create table `university`(
`id_university` int(3) NOT NULL auto_increment,
`name` varchar(200) default null,
`address` varchar(45) default null,
`street` varchar(45) default null,
`street_number` int(3) default null,
primary key (`id_university`)
)


