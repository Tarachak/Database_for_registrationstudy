use `student_registration`;

Select declaration.id_field_of_study from student inner join declaration on declaration.id_student=student.id_student where student.id_student=2 order by declaration.priority;