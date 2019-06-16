use `student_registration`;

Select field_of_study.name,field_of_study.min_points,field_of_study.students_limit from university inner join field_of_study on field_of_study.id_university=university.id_university where university.id_university=2;