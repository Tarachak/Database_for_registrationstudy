use `student_registration`;

create table `declaration`(
`id_university` int(3) not null,
`id_student` int(3) not null,
`id_field_of_study` int(3) not null,
`priority` varchar(45) not null,
`exams_score` int(3) not null,
foreign key (`id_university`) references university(`id_university`),
foreign key (`id_student`) references student(`id_student`),
foreign key (`id_field_of_study`) references field_of_study(`id_field_of_study`),
primary key (`id_university`,`id_student`,`id_field_of_study`)
)


