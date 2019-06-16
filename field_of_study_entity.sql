use `student_registration`;

create table `field_of_study`(
`id_field_of_study` int(3) not null auto_increment,
`id_university` int(3) not null,
`name` varchar(45) default null,
`faculty` varchar(45) default null,
`students_limit` int(3) default null,
`min_points` int(3) default null,
foreign key (`id_university`) references university(`id_university`),
primary key (`id_field_of_study`)
)


