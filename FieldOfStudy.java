package com.databasePoject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "field_of_study")
public class FieldOfStudy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_field_of_study")
    private int idFieldOfStudy;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_university")
    private University idUniversity;

    @Column(name = "name")
    private String fieldOfStudyName;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "students_limit")
    private int studentsLimit;

    @Column(name = "min_points")
    private int minPoints;

    @OneToMany(mappedBy = "idFieldOfStudy",
            cascade = {CascadeType.ALL})
    private List<Declaration> declarationList;

    public FieldOfStudy(){

    }

    public FieldOfStudy(String fieldOfStudyName, String faculty, int students_limit, int minPoints) {
        this.fieldOfStudyName = fieldOfStudyName;
        this.faculty = faculty;
        this.studentsLimit = students_limit;
        this.minPoints = minPoints;
    }

    public int getIdFieldOfStudy() {
        return idFieldOfStudy;
    }

    public void setIdFieldOfStudy(int idFieldOfStudy) {
        this.idFieldOfStudy = idFieldOfStudy;
    }

    public University getIdUniversity() {
        return idUniversity;
    }

    public void setIdUniversity(University idUniversity) {
        this.idUniversity = idUniversity;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }

    public int getStudentsLimit() {
        return studentsLimit;
    }

    public void setStudentsLimit(int studentsLimit) {
        this.studentsLimit = studentsLimit;
    }

    public String getFieldOfStudyName() {
        return fieldOfStudyName;
    }

    public void setFieldOfStudyName(String fieldOfStudyName) {
        this.fieldOfStudyName = fieldOfStudyName;
    }

    public List<Declaration> getDeclarationList() {
        return declarationList;
    }

    public void setDeclarationList(List<Declaration> declarationList) {
        this.declarationList = declarationList;
    }

    public void add(Declaration declaration){

        if(declarationList == null){
            declarationList = new ArrayList<>();
        }

        declarationList.add(declaration);
        declaration.setIdFieldOfStudy(this);
    }
}
