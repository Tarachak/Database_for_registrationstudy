package com.databasePoject;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "declaration")
public class Declaration implements Serializable {

    @EmbeddedId
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_university")
    private University idUniversity;

    @EmbeddedId
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_student")
    private Student idStudent;

    @EmbeddedId
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_field_of_study")
    private FieldOfStudy idFieldOfStudy;

    @Column(name = "priority")
    private int priority;

    @Column(name = "exams_score")
    private int examsScore;


    public Declaration(){

    }

    public Declaration(int priority, int examsScore) {
        this.priority = priority;
        this.examsScore = examsScore;
    }

    public Declaration(University idUniversity, Student idStudent, FieldOfStudy idFieldOfStudy, int priority, int examsScore) {
        this.idUniversity = idUniversity;
        this.idStudent = idStudent;
        this.idFieldOfStudy = idFieldOfStudy;
        this.priority = priority;
        this.examsScore = examsScore;
    }

    public University getIdUniversity() {
        return idUniversity;
    }

    public void setIdUniversity(University idUniversity) {
        this.idUniversity = idUniversity;
    }

    public Student getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Student idStudent) {
        this.idStudent = idStudent;
    }

    public FieldOfStudy getIdFieldOfStudy() {
        return idFieldOfStudy;
    }

    public void setIdFieldOfStudy(FieldOfStudy idFieldOfStudy) {
        this.idFieldOfStudy = idFieldOfStudy;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getExamsScore() {
        return examsScore;
    }

    public void setExamsScore(int examsScore) {
        this.examsScore = examsScore;
    }

    @Override
    public String toString() {
        return "Declaration{" +
                "idUniversity=" + idUniversity +
                ", idStudent=" + idStudent +
                ", idFieldOfStudy=" + idFieldOfStudy +
                ", priority=" + priority +
                ", examsScore=" + examsScore +
                '}';
    }
}
