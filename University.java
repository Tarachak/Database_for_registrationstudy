package com.databasePoject;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_university")
    private int idUniversity;

    @Column(name = "name")
    private String universityName;

    @Column(name = "address")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "street_number")
    private String streetNumber;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "idUniversity",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                       CascadeType.DETACH, CascadeType.REFRESH})
    private List<FieldOfStudy> fieldOfStudyList;

    @OneToMany(mappedBy = "idUniversity",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Declaration> declarationList;

    public University(){

    }

    public University(String universityName, String city, String street, String streetNumber) {
        this.universityName = universityName;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public int getIdUniversity() {
        return idUniversity;
    }

    public void setIdUniversity(int idUniversity) {
        this.idUniversity = idUniversity;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public List<FieldOfStudy> getFieldOfStudyList() {
        return fieldOfStudyList;
    }

    public void setFieldOfStudyList(List<FieldOfStudy> fieldOfStudyList) {
        this.fieldOfStudyList = fieldOfStudyList;
    }

    public List<Declaration> getDeclarationList() {
        return declarationList;
    }

    public void setDeclarationList(List<Declaration> declarationList) {
        this.declarationList = declarationList;
    }

    @Override
    public String toString() {
        return "University{" +
                "idUniversity=" + idUniversity +
                ", universityName='" + universityName + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                '}';
    }

    public void add(Declaration declaration){

        if(declarationList == null){
            declarationList = new ArrayList<>();
        }

        declarationList.add(declaration);
        declaration.setIdUniversity(this);
    }

    public void addFieldOfStudy(FieldOfStudy fieldOfStudy){

        if(fieldOfStudyList == null){
            fieldOfStudyList = new ArrayList<>();
        }

        fieldOfStudyList.add(fieldOfStudy);
        fieldOfStudy.setIdUniversity(this);
    }
}
