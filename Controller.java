package com.databasePoject;

import com.mysql.cj.util.StringUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private Button editStudentDataButton;

    @FXML
    private Button saveDeclarationButton;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputSurname;

    @FXML
    private TextField inputExamScore1;

    @FXML
    private TextField inputExamScore2;

    @FXML
    private TextField inputExamScore3;

    @FXML
    private TextField inputExamScore4;

    @FXML
    private TextField inputExamScore5;

    @FXML
    private Label labelName;

    @FXML
    private Label labelSurname;

    @FXML
    private Label labelDeclaration1;

    @FXML
    private Label labelDeclaration2;

    @FXML
    private Label labelDeclaration3;

    @FXML
    private Label labelDeclaration4;

    @FXML
    private Label labelDeclaration5;

    @FXML
    private ChoiceBox choiceUniversity;

    @FXML
    private ChoiceBox choiceFieldOfStudy1;

    @FXML
    private ChoiceBox choiceFieldOfStudy2;

    @FXML
    private ChoiceBox choiceFieldOfStudy3;

    @FXML
    private ChoiceBox choiceFieldOfStudy4;

    @FXML
    private ChoiceBox choiceFieldOfStudy5;

    @FXML
    private ChoiceBox choiceFaculty1;

    @FXML
    private ChoiceBox choiceFaculty2;

    @FXML
    private ChoiceBox choiceFaculty3;

    @FXML
    private ChoiceBox choiceFaculty4;

    @FXML
    private ChoiceBox choiceFaculty5;

    @FXML
    private TableView<TableCells> tableUniversity;

    @FXML
    private TableView<TableCells> tableFaculty;

    @FXML
    private TableView<TableCells> tableFieldOfStudy;

    @FXML
    private TableColumn<TableCells, String> columnUniversity;

    @FXML
    private TableColumn<TableCells, String> columnFaculty;

    @FXML
    private TableColumn<TableCells, String> columnFieldOfStudy;

    @FXML
    private TableColumn<TableCells, Integer> columnMinPoints;

    @FXML
    private TableColumn tableMinPoints;

    @FXML
    private ListView<String> results;

    private int idStudent;

    private Student student;

    private List<University> universityList;


    private University university1;

    private University university2;

    private University university3;

    private University university4;

    private University university5;

    private FieldOfStudy fieldOfStudy1;

    private FieldOfStudy fieldOfStudy2;

    private FieldOfStudy fieldOfStudy3;

    private FieldOfStudy fieldOfStudy4;

    private FieldOfStudy fieldOfStudy5;

    private List<FieldOfStudy> fieldOfStudyList;

    private List<Student> studentsList;

    private List<FieldOfStudy> facultyList;

    private List<Declaration> fieldOfStudyTempList;

    private List<String> resultsTempList;

    private List<Declaration> declarationList;

    private List<Declaration> declarationTempList;

    private boolean editFalg;

    private boolean editFlag2;

    private Session session;

    private SessionFactory factory;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        editFalg=false;
        factory = new Configuration()
                .configure("com/databasePoject/hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(University.class)
                .addAnnotatedClass(Declaration.class)
                .addAnnotatedClass(FieldOfStudy.class)
                .buildSessionFactory();

        choiceUniversity.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            labelDeclaration1.setText("");
            labelDeclaration2.setText("");
            labelDeclaration3.setText("");
            labelDeclaration4.setText("");
            labelDeclaration5.setText("");


            fieldOfStudyTempList = new ArrayList<Declaration>();

            for(int i=0; i < student.getDeclarationList().size();i++){
                if(newValue.equals(student.getDeclarationList().get(i).getIdUniversity().getUniversityName())){
                    fieldOfStudyTempList.add(student.getDeclarationList().get(i));
                }
            }

//            System.out.println(student.getDeclarationList());

//            labelDeclaration1.setText(fieldOfStudyTempList.get(0).getIdUniversity().getUniversityName() + ", " +
//                    fieldOfStudyTempList.get(0).getIdFieldOfStudy().getFaculty() + ", " +
//                    fieldOfStudyTempList.get(0).getIdFieldOfStudy().getFieldOfStudyName());

            for(int i =0; i < fieldOfStudyTempList.size();i++){
                if(fieldOfStudyTempList.size()>0 && fieldOfStudyTempList.get(i).getPriority()==1) {
                    labelDeclaration1.setText(fieldOfStudyTempList.get(i).getIdUniversity().getUniversityName() + ", " +
                            fieldOfStudyTempList.get(i).getIdFieldOfStudy().getFaculty() + ", " +
                            fieldOfStudyTempList.get(i).getIdFieldOfStudy().getFieldOfStudyName() + ", pkt: " + String.valueOf(fieldOfStudyTempList.get(i).getExamsScore()));
                }
                if(fieldOfStudyTempList.size()>1 && fieldOfStudyTempList.get(i).getPriority()==2) {
                    labelDeclaration2.setText(fieldOfStudyTempList.get(i).getIdUniversity().getUniversityName() + ", " +
                            fieldOfStudyTempList.get(i).getIdFieldOfStudy().getFaculty() + ", " +
                            fieldOfStudyTempList.get(i).getIdFieldOfStudy().getFieldOfStudyName() + ", pkt: " + String.valueOf(fieldOfStudyTempList.get(i).getExamsScore()));
                }
                if(fieldOfStudyTempList.size()>2 && fieldOfStudyTempList.get(i).getPriority()==3) {
                    labelDeclaration3.setText(fieldOfStudyTempList.get(i).getIdUniversity().getUniversityName() + ", " +
                            fieldOfStudyTempList.get(i).getIdFieldOfStudy().getFaculty() + ", " +
                            fieldOfStudyTempList.get(i).getIdFieldOfStudy().getFieldOfStudyName() + ", pkt: " + String.valueOf(fieldOfStudyTempList.get(i).getExamsScore()));
                }
                if(fieldOfStudyTempList.size()>3 && fieldOfStudyTempList.get(i).getPriority()==4) {
                    labelDeclaration4.setText(fieldOfStudyTempList.get(i).getIdUniversity().getUniversityName() + ", " +
                            fieldOfStudyTempList.get(i).getIdFieldOfStudy().getFaculty() + ", " +
                            fieldOfStudyTempList.get(i).getIdFieldOfStudy().getFieldOfStudyName() + ", pkt: " + String.valueOf(fieldOfStudyTempList.get(i).getExamsScore()));
                }
                if(fieldOfStudyTempList.size()>4 && fieldOfStudyTempList.get(i).getPriority()==5) {
                    labelDeclaration5.setText(fieldOfStudyTempList.get(i).getIdUniversity().getUniversityName() + ", " +
                            fieldOfStudyTempList.get(i).getIdFieldOfStudy().getFaculty() + ", " +
                            fieldOfStudyTempList.get(i).getIdFieldOfStudy().getFieldOfStudyName() + ", pkt: " + String.valueOf(fieldOfStudyTempList.get(i).getExamsScore()));
                }
            }

            int Id = (int) session.createQuery("select uni.idUniversity from University uni where uni.universityName = '" + newValue +"'").getSingleResult();
            String query = "select field.faculty from FieldOfStudy field where field.idUniversity = '" + Id +"'";
            facultyList = session.createQuery(query).getResultList();
            choiceFaculty1.setItems(FXCollections.observableArrayList(facultyList));
            choiceFaculty1.getSelectionModel().selectFirst();
            choiceFaculty2.setItems(FXCollections.observableArrayList(facultyList));
            choiceFaculty2.getSelectionModel().selectFirst();
            choiceFaculty3.setItems(FXCollections.observableArrayList(facultyList));
            choiceFaculty3.getSelectionModel().selectFirst();
            choiceFaculty4.setItems(FXCollections.observableArrayList(facultyList));
            choiceFaculty4.getSelectionModel().selectFirst();
            choiceFaculty5.setItems(FXCollections.observableArrayList(facultyList));
            choiceFaculty5.getSelectionModel().selectFirst();
        });

        choiceFaculty1.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            String query = "select field.fieldOfStudyName from FieldOfStudy field where field.faculty = '" + newValue +"'";
            fieldOfStudyList = session.createQuery(query).getResultList();
            choiceFieldOfStudy1.setItems(FXCollections.observableArrayList(fieldOfStudyList));
            choiceFieldOfStudy1.getSelectionModel().selectFirst();
        });

        choiceFaculty2.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            String query = "select field.fieldOfStudyName from FieldOfStudy field where field.faculty = '" + newValue +"'";
            fieldOfStudyList = session.createQuery(query).getResultList();
            choiceFieldOfStudy2.setItems(FXCollections.observableArrayList(fieldOfStudyList));
            choiceFieldOfStudy2.getSelectionModel().selectFirst();
        });

        choiceFaculty3.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            String query = "select field.fieldOfStudyName from FieldOfStudy field where field.faculty = '" + newValue +"'";
            fieldOfStudyList = session.createQuery(query).getResultList();
            choiceFieldOfStudy3.setItems(FXCollections.observableArrayList(fieldOfStudyList));
            choiceFieldOfStudy3.getSelectionModel().selectFirst();
        });

        choiceFaculty4.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            String query = "select field.fieldOfStudyName from FieldOfStudy field where field.faculty = '" + newValue +"'";
            fieldOfStudyList = session.createQuery(query).getResultList();
            choiceFieldOfStudy4.setItems(FXCollections.observableArrayList(fieldOfStudyList));
            choiceFieldOfStudy4.getSelectionModel().selectFirst();
        });

        choiceFaculty5.getSelectionModel().selectedItemProperty().addListener((list, value, newValue) -> {
            String query = "select field.fieldOfStudyName from FieldOfStudy field where field.faculty = '" + newValue +"'";
            fieldOfStudyList = session.createQuery(query).getResultList();
            choiceFieldOfStudy5.setItems(FXCollections.observableArrayList(fieldOfStudyList));
            choiceFieldOfStudy5.getSelectionModel().selectFirst();
        });

        tableUniversity.setOnMouseClicked((MouseEvent event) -> {
            clickColumnUniversity();
        });

        tableFaculty.setOnMouseClicked((MouseEvent event) -> {
            clickColumnFaculty();
        });


        session = factory.getCurrentSession();
        session.beginTransaction();
        Platform.runLater(() -> {
            student = session.get(Student.class, idStudent);
        });
        universityList = session.createQuery("select uni from University uni").getResultList();

        ObservableList<TableCells> dane = FXCollections.observableArrayList();
        for (int i=0; i<universityList.size();i++){
            dane.add(new TableCells(String.valueOf(universityList.get(i).getUniversityName())));
        }
        columnUniversity.setCellValueFactory(new PropertyValueFactory<TableCells, String>("university"));
        tableUniversity.setEditable(true);
        tableUniversity.itemsProperty().setValue(dane);
    }

    @FXML
    public void clickDataStudent(){
        inputName.setVisible(false);
        inputSurname.setVisible(false);

        labelName.setText(student.getName());
        labelSurname.setText(student.getSurname());
    }

    @FXML
    public void editStudentData(){

        if(editFalg && validInputText(inputName.getText()) && validInputText(inputSurname.getText())){
            editStudentDataButton.setText("Edit");
            inputName.setVisible(false);
            inputSurname.setVisible(false);
            labelName.setText(inputName.getText());
            labelSurname.setText(inputSurname.getText());

            student.setName(inputName.getText());
            student.setSurname(inputSurname.getText());

            editFalg=false;
        }
        else{
            System.out.println("gg");
            editStudentDataButton.setText("Save");
            inputName.setVisible(true);
            inputSurname.setVisible(true);
            editFalg=true;
        }
    }

    @FXML
    public void setInputName(){

    }

    @FXML
    public void setInputSurname(){

    }

    @FXML
    public void clickDeclaration(){
        universityList = session.createQuery("select universityName from University uni").getResultList();
        choiceUniversity.setItems(FXCollections.observableArrayList(universityList));
        choiceUniversity.getSelectionModel().selectFirst();

        choiceFaculty1.setDisable(true);
        choiceFaculty2.setDisable(true);
        choiceFaculty3.setDisable(true);
        choiceFaculty4.setDisable(true);
        choiceFaculty5.setDisable(true);
        choiceFieldOfStudy1.setDisable(true);
        choiceFieldOfStudy2.setDisable(true);
        choiceFieldOfStudy3.setDisable(true);
        choiceFieldOfStudy4.setDisable(true);
        choiceFieldOfStudy5.setDisable(true);
        inputExamScore1.setDisable(true);
        inputExamScore2.setDisable(true);
        inputExamScore3.setDisable(true);
        inputExamScore4.setDisable(true);
        inputExamScore5.setDisable(true);

        editFlag2=true;
        saveDeclarationButton.setText("Edit");

    }

    @FXML
    public void saveDeclaration(){
        System.out.println(fieldOfStudyTempList);
        if(fieldOfStudyTempList.size()==0 && !editFlag2) {

            choiceFaculty1.setDisable(true);
            choiceFaculty2.setDisable(true);
            choiceFaculty3.setDisable(true);
            choiceFaculty4.setDisable(true);
            choiceFaculty5.setDisable(true);
            choiceFieldOfStudy1.setDisable(true);
            choiceFieldOfStudy2.setDisable(true);
            choiceFieldOfStudy3.setDisable(true);
            choiceFieldOfStudy4.setDisable(true);
            choiceFieldOfStudy5.setDisable(true);
            inputExamScore1.setDisable(true);
            inputExamScore2.setDisable(true);
            inputExamScore3.setDisable(true);
            inputExamScore4.setDisable(true);
            inputExamScore5.setDisable(true);

            saveDeclarationButton.setText("Edit");

            Declaration declaration1;
            Declaration declaration2;
            Declaration declaration3;
            Declaration declaration4;
            Declaration declaration5;


            if(inputExamScore1.getText().length()>0) {
                university1 = (University) session.createQuery("select uni from University uni where uni.universityName = '" + choiceUniversity.getValue() + "'").getSingleResult();
                fieldOfStudy1 = (FieldOfStudy) session.createQuery("select field from FieldOfStudy field where field.fieldOfStudyName = '" + choiceFieldOfStudy1.getValue() + "'").getSingleResult();

                declaration1 = new Declaration(1, Integer.parseInt(inputExamScore1.getText()));
                student.add(declaration1);
                university1.add(declaration1);
                fieldOfStudy1.add(declaration1);
                session.save(declaration1);

                labelDeclaration1.setText(university1.getUniversityName() + ", " +
                        fieldOfStudy1.getFaculty() + ", " +
                        fieldOfStudy1.getFieldOfStudyName() + ", pkt: " + inputExamScore1.getText());

            }
            if(inputExamScore2.getText().length()>0) {
                university2 = (University) session.createQuery("select uni from University uni where uni.universityName = '" + choiceUniversity.getValue() + "'").getSingleResult();
                fieldOfStudy2 = (FieldOfStudy) session.createQuery("select field from FieldOfStudy field where field.fieldOfStudyName = '" + choiceFieldOfStudy2.getValue() + "'").getSingleResult();

                declaration2 = new Declaration(2, Integer.parseInt(inputExamScore2.getText()));
                student.add(declaration2);
                university2.add(declaration2);
                fieldOfStudy2.add(declaration2);
                session.save(declaration2);

                labelDeclaration2.setText(university2.getUniversityName() + ", " +
                        fieldOfStudy2.getFaculty() + ", " +
                        fieldOfStudy2.getFieldOfStudyName() + ", pkt: " + inputExamScore2.getText());

            }

            if(inputExamScore3.getText().length()>0) {
                university3 = (University) session.createQuery("select uni from University uni where uni.universityName = '" + choiceUniversity.getValue() + "'").getSingleResult();
                fieldOfStudy3 = (FieldOfStudy) session.createQuery("select field from FieldOfStudy field where field.fieldOfStudyName = '" + choiceFieldOfStudy3.getValue() + "'").getSingleResult();

                declaration3 = new Declaration(3, Integer.parseInt(inputExamScore3.getText()));
                student.add(declaration3);
                university3.add(declaration3);
                fieldOfStudy3.add(declaration3);
                session.save(declaration3);

                labelDeclaration3.setText(university3.getUniversityName() + ", " +
                        fieldOfStudy3.getFaculty() + ", " +
                        fieldOfStudy3.getFieldOfStudyName() + ", pkt: " + inputExamScore3.getText());

            }

            if(inputExamScore4.getText().length()>0) {
                university4 = (University) session.createQuery("select uni from University uni where uni.universityName = '" + choiceUniversity.getValue() + "'").getSingleResult();
                fieldOfStudy4 = (FieldOfStudy) session.createQuery("select field from FieldOfStudy field where field.fieldOfStudyName = '" + choiceFieldOfStudy4.getValue() + "'").getSingleResult();

                declaration4 = new Declaration(4, Integer.parseInt(inputExamScore4.getText()));
                student.add(declaration4);
                university4.add(declaration4);
                fieldOfStudy4.add(declaration4);
                session.save(declaration4);

                labelDeclaration4.setText(university1.getUniversityName() + ", " +
                        fieldOfStudy4.getFaculty() + ", " +
                        fieldOfStudy4.getFieldOfStudyName() + ", pkt: " + inputExamScore4.getText());

            }

            if(inputExamScore5.getText().length()>0) {
                university5 = (University) session.createQuery("select uni from University uni where uni.universityName = '" + choiceUniversity.getValue() + "'").getSingleResult();
                fieldOfStudy5 = (FieldOfStudy) session.createQuery("select field from FieldOfStudy field where field.fieldOfStudyName = '" + choiceFieldOfStudy5.getValue() + "'").getSingleResult();

                declaration5 = new Declaration(5, Integer.parseInt(inputExamScore5.getText()));
                student.add(declaration5);
                university5.add(declaration5);
                fieldOfStudy5.add(declaration5);
                session.save(declaration5);

                labelDeclaration5.setText(university5.getUniversityName() + ", " +
                        fieldOfStudy5.getFaculty() + ", " +
                        fieldOfStudy5.getFieldOfStudyName() + ", pkt: " + inputExamScore5.getText());

            }

            editFlag2=true;
            System.out.println(editFlag2);
//            session.getTransaction().commit();
        }else if(!editFlag2){
            choiceFaculty1.setDisable(true);
            choiceFaculty2.setDisable(true);
            choiceFaculty3.setDisable(true);
            choiceFaculty4.setDisable(true);
            choiceFaculty5.setDisable(true);
            choiceFieldOfStudy1.setDisable(true);
            choiceFieldOfStudy2.setDisable(true);
            choiceFieldOfStudy3.setDisable(true);
            choiceFieldOfStudy4.setDisable(true);
            choiceFieldOfStudy5.setDisable(true);
            inputExamScore1.setDisable(true);
            inputExamScore2.setDisable(true);
            inputExamScore3.setDisable(true);
            inputExamScore4.setDisable(true);
            inputExamScore5.setDisable(true);

            saveDeclarationButton.setText("Edit");

            declarationTempList = new ArrayList<Declaration>();

            declarationList = student.getDeclarationList();

            for (Declaration declar: declarationList) {
                if(declar.getIdUniversity().getUniversityName().equals(choiceUniversity.getValue())){
                    declarationTempList.add(declar);
                }
            }
            for(int j =0; j< declarationTempList.size();j++){
                if(fieldOfStudyTempList.size()>0 && inputExamScore1.getText().length()>0 && declarationTempList.get(j).getPriority()==1){
                    for(int i=0;i<fieldOfStudyList.size();i++){
                        if(String.valueOf(fieldOfStudyList.get(i)).equals(choiceFieldOfStudy1.getValue())){

                            String getQuery = "select field from FieldOfStudy field where field.fieldOfStudyName = '" + fieldOfStudyList.get(i) +"'";
                            FieldOfStudy fieldOfStudy = (FieldOfStudy) session.createQuery(getQuery).getSingleResult();

                            Declaration declaration1 = new Declaration(1, Integer.parseInt(inputExamScore1.getText()));
                            System.out.println(fieldOfStudy.getIdFieldOfStudy());
                            session.delete(declarationTempList.get(j));
                            session.flush();
                            declarationTempList.get(j).getIdUniversity().add(declaration1);
                            declarationTempList.get(j).getIdStudent().add(declaration1);

                            fieldOfStudy.add(declaration1);
                            session.save(declaration1);

                            labelDeclaration1.setText(fieldOfStudy.getIdUniversity().getUniversityName() + ", " +
                                    fieldOfStudy.getFaculty() + ", " +
                                    fieldOfStudy.getFieldOfStudyName() + ", pkt: " + inputExamScore1.getText());

                        }
                    }
                }

                if(fieldOfStudyTempList.size()>1 && inputExamScore2.getText().length()>0  && declarationTempList.get(j).getPriority()==2) {
                    for(int i=0;i<fieldOfStudyList.size();i++){
                        if(String.valueOf(fieldOfStudyList.get(i)).equals(choiceFieldOfStudy2.getValue())){
                            String getQuery = "select field from FieldOfStudy field where field.fieldOfStudyName = '" + fieldOfStudyList.get(i) +"'";
                            FieldOfStudy fieldOfStudy = (FieldOfStudy) session.createQuery(getQuery).getSingleResult();

                            Declaration declaration1 = new Declaration(2, Integer.parseInt(inputExamScore2.getText()));
                            System.out.println(fieldOfStudy.getIdFieldOfStudy());
                            session.delete(declarationTempList.get(j));
                            session.flush();
                            declarationTempList.get(j).getIdUniversity().add(declaration1);
                            declarationTempList.get(j).getIdStudent().add(declaration1);

                            fieldOfStudy.add(declaration1);
                            session.save(declaration1);

                            labelDeclaration2.setText(fieldOfStudy.getIdUniversity().getUniversityName() + ", " +
                                    fieldOfStudy.getFaculty() + ", " +
                                    fieldOfStudy.getFieldOfStudyName() + ", pkt: " + inputExamScore2.getText());

                        }
                    }
                }

                if(fieldOfStudyTempList.size()>2 && inputExamScore3.getText().length()>0  && declarationTempList.get(j).getPriority()==3) {
                    for(int i=0;i<fieldOfStudyList.size();i++){
                        if(String.valueOf(fieldOfStudyList.get(i)).equals(choiceFieldOfStudy3.getValue())){
                            String getQuery = "select field from FieldOfStudy field where field.fieldOfStudyName = '" + fieldOfStudyList.get(i) +"'";
                            FieldOfStudy fieldOfStudy = (FieldOfStudy) session.createQuery(getQuery).getSingleResult();

                            Declaration declaration1 = new Declaration(3, Integer.parseInt(inputExamScore3.getText()));
                            System.out.println(fieldOfStudy.getIdFieldOfStudy());
                            session.delete(declarationTempList.get(j));
                            session.flush();
                            declarationTempList.get(j).getIdUniversity().add(declaration1);
                            declarationTempList.get(j).getIdStudent().add(declaration1);

                            fieldOfStudy.add(declaration1);
                            session.save(declaration1);

                            labelDeclaration3.setText(fieldOfStudy.getIdUniversity().getUniversityName() + ", " +
                                    fieldOfStudy.getFaculty() + ", " +
                                    fieldOfStudy.getFieldOfStudyName() + ", pkt: " + inputExamScore3.getText());

                        }
                    }
                }

                if(fieldOfStudyTempList.size()>3 && inputExamScore4.getText().length()>0  && declarationTempList.get(j).getPriority()==4) {
                    for(int i=0;i<fieldOfStudyList.size();i++){
                        if(String.valueOf(fieldOfStudyList.get(i)).equals(choiceFieldOfStudy4.getValue())){
                            String getQuery = "select field from FieldOfStudy field where field.fieldOfStudyName = '" + fieldOfStudyList.get(i) +"'";
                            FieldOfStudy fieldOfStudy = (FieldOfStudy) session.createQuery(getQuery).getSingleResult();

                            Declaration declaration1 = new Declaration(4, Integer.parseInt(inputExamScore4.getText()));
                            System.out.println(fieldOfStudy.getIdFieldOfStudy());
                            session.delete(declarationTempList.get(j));
                            session.flush();
                            declarationTempList.get(j).getIdUniversity().add(declaration1);
                            declarationTempList.get(j).getIdStudent().add(declaration1);

                            fieldOfStudy.add(declaration1);
                            session.save(declaration1);

                            labelDeclaration4.setText(fieldOfStudy.getIdUniversity().getUniversityName() + ", " +
                                    fieldOfStudy.getFaculty() + ", " +
                                    fieldOfStudy.getFieldOfStudyName() + ", pkt: " + inputExamScore4.getText());

                        }
                    }
                }

                if(fieldOfStudyTempList.size()>4 && inputExamScore5.getText().length()>0  && declarationTempList.get(j).getPriority()==5) {
                    for(int i=0;i<fieldOfStudyList.size();i++){
                        if(String.valueOf(fieldOfStudyList.get(i)).equals(choiceFieldOfStudy5.getValue())){
                            String getQuery = "select field from FieldOfStudy field where field.fieldOfStudyName = '" + fieldOfStudyList.get(i) +"'";
                            FieldOfStudy fieldOfStudy = (FieldOfStudy) session.createQuery(getQuery).getSingleResult();

                            Declaration declaration1 = new Declaration(5, Integer.parseInt(inputExamScore5.getText()));
                            System.out.println(fieldOfStudy.getIdFieldOfStudy());
                            session.delete(declarationTempList.get(j));
                            session.flush();
                            declarationTempList.get(j).getIdUniversity().add(declaration1);
                            declarationTempList.get(j).getIdStudent().add(declaration1);

                            fieldOfStudy.add(declaration1);
                            session.save(declaration1);

                            labelDeclaration5.setText(fieldOfStudy.getIdUniversity().getUniversityName() + ", " +
                                    fieldOfStudy.getFaculty() + ", " +
                                    fieldOfStudy.getFieldOfStudyName() + ", pkt: " + inputExamScore5.getText());

                        }
                    }
                }
            }

            editFlag2=true;
//            session.getTransaction().commit();
        }else{

            choiceFaculty1.setDisable(false);
            choiceFaculty2.setDisable(false);
            choiceFaculty3.setDisable(false);
            choiceFaculty4.setDisable(false);
            choiceFaculty5.setDisable(false);
            choiceFieldOfStudy1.setDisable(false);
            choiceFieldOfStudy2.setDisable(false);
            choiceFieldOfStudy3.setDisable(false);
            choiceFieldOfStudy4.setDisable(false);
            choiceFieldOfStudy5.setDisable(false);
            inputExamScore1.setDisable(false);
            inputExamScore2.setDisable(false);
            inputExamScore3.setDisable(false);
            inputExamScore4.setDisable(false);
            inputExamScore5.setDisable(false);

            saveDeclarationButton.setText("Save");
            editFlag2=false;
        }
    }

    @FXML
    public void clickInformation(){

    }

    @FXML
    public void clickColumnUniversity(){
        universityList = session.createQuery("select uni from University uni").getResultList();
        TableCells university = tableUniversity.getSelectionModel().getSelectedItem();
        tableFieldOfStudy.setItems(null);

        for (int i=0; i<universityList.size();i++){
            if(universityList.get(i).getUniversityName().equals(university.getUniversity().getValue())){
                facultyList=universityList.get(i).getFieldOfStudyList();
            }
        }

        List<Integer> facultyTempList = new ArrayList<>();

        for(int i =0;i<facultyList.size();i++){
            facultyTempList.add(0);
        }

        ObservableList<TableCells> dane = FXCollections.observableArrayList();
        for (int i=0; i<facultyList.size();i++){
            if(facultyTempList.get(i)!=1){
                dane.add(new TableCells(String.valueOf(facultyList.get(i).getFaculty())));
                for(int j=0;j<facultyList.size();j++){
                    if(facultyList.get(i).getFaculty().equals(facultyList.get(j).getFaculty())){
                        facultyTempList.set(j,1);
                    }
                }
            }
        }
        columnFaculty.setCellValueFactory(new PropertyValueFactory<TableCells, String>("university"));
        tableFaculty.setEditable(true);
        tableFaculty.itemsProperty().setValue(dane);
    }

    @FXML
    public void clickColumnFaculty(){
        TableCells faculty = tableFaculty.getSelectionModel().getSelectedItem();

        fieldOfStudyList= new ArrayList<FieldOfStudy>();

        for (int i=0; i<facultyList.size();i++){
            if(facultyList.get(i).getFaculty().equals(faculty.getUniversity().getValue())){
                fieldOfStudyList.add(facultyList.get(i));
            }
        }

        ObservableList<TableCells> dane = FXCollections.observableArrayList();

        for (int i=0; i<fieldOfStudyList.size();i++){
            dane.add(new TableCells(String.valueOf(fieldOfStudyList.get(i).getFieldOfStudyName()), Integer.valueOf(fieldOfStudyList.get(i).getMinPoints())));
        }
        columnFieldOfStudy.setCellValueFactory(new PropertyValueFactory<TableCells, String>("university"));
        columnMinPoints.setCellValueFactory(new PropertyValueFactory<TableCells, Integer>("points"));
        tableFieldOfStudy.setEditable(true);
        tableFieldOfStudy.itemsProperty().setValue(dane);
    }

    @FXML
    public void closedInformation(){

    }

    @FXML
    public void clickResults(){
        resultsTempList = new ArrayList<String>();
        studentsList = new ArrayList<Student>();
        results.getItems().clear();

        declarationList = session.createQuery("select decl from Declaration decl order by decl.priority").getResultList();

        List<Integer> tempList = new ArrayList<>();
        for(int i=0; i< declarationList.size();i++){
            tempList.add(0);
        }

        int universityLastName=0;

        for (int i =0;i<declarationList.size();i++) {

            if(declarationList.get(i).getPriority()==1 && student.getId()==declarationList.get(i).getIdStudent().getId() && tempList.get(i)!=1){
                int size = 0;

                List<Declaration> declarationTempList = session.createQuery("select decl from Declaration decl where decl.idFieldOfStudy = '" + declarationList.get(i).getIdFieldOfStudy().getIdFieldOfStudy() + "' and decl.priority=1 order by decl.examsScore desc").getResultList();

                if(declarationList.get(i).getIdFieldOfStudy().getStudentsLimit()>=declarationTempList.size()){
                    size= declarationTempList.size();

                }else{
                    size = declarationList.get(i).getIdFieldOfStudy().getStudentsLimit();

                }

                for(int l =0 ; l<size;l++) {

                    if ((declarationTempList.get(l).getIdFieldOfStudy().getIdFieldOfStudy()==declarationList.get(i).getIdFieldOfStudy().getIdFieldOfStudy()) && (student.getId() == declarationTempList.get(l).getIdStudent().getId())) {
                        String result = declarationTempList.get(l).getIdUniversity().getUniversityName() + ", " + declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName() + ", priorytet: " + declarationTempList.get(l).getPriority() + ", pkt:" + declarationTempList.get(l).getExamsScore();
                        resultsTempList.add(result);

                        for(int j =0 ;j<declarationList.size();j++){
                            if(declarationList.get(i).getIdUniversity().getIdUniversity()==declarationList.get(j).getIdUniversity().getIdUniversity()){
                                tempList.set(j,1);
                            }
                        }
                    }
                }

            }
            else if(declarationList.get(i).getPriority()==2 && student.getId()==declarationList.get(i).getIdStudent().getId() && tempList.get(i)!=1){
                int size = 0;
                List<Declaration> declarationTempList = session.createQuery("select decl from Declaration decl where decl.idFieldOfStudy = '" + declarationList.get(i).getIdFieldOfStudy().getIdFieldOfStudy() + "' and decl.priority=2 order by decl.examsScore desc ").getResultList();

                if(declarationList.get(i).getIdFieldOfStudy().getStudentsLimit()>=declarationTempList.size()){
                    size= declarationTempList.size();
                }else{
                    size = declarationList.get(i).getIdFieldOfStudy().getStudentsLimit();
                }

                for(int l =0 ; l<size;l++) {
                    if (declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName()==declarationList.get(i).getIdFieldOfStudy().getFieldOfStudyName() && student.getId() == declarationTempList.get(l).getIdStudent().getId()) {
                        String result = declarationTempList.get(l).getIdUniversity().getUniversityName() + ", " + declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName() + ", priorytet: " + declarationTempList.get(l).getPriority();
                        resultsTempList.add(result);

                        for(int j =0 ;j<declarationList.size();j++){
                            if(declarationList.get(i).getIdUniversity().getIdUniversity()==declarationList.get(j).getIdUniversity().getIdUniversity()){
                                tempList.set(j,1);
                            }
                        }
                    }
                }

            }
            else if(declarationList.get(i).getPriority()==3 && student.getId()==declarationList.get(i).getIdStudent().getId() && tempList.get(i)!=1){
                int size = 0;

                System.out.println(declarationList.get(i) + "3");

                List<Declaration> declarationTempList = session.createQuery("select decl from Declaration decl where decl.idFieldOfStudy = '" + declarationList.get(i).getIdFieldOfStudy().getIdFieldOfStudy() + "' and decl.priority=3 order by decl.examsScore desc").getResultList();


                if(declarationList.get(i).getIdFieldOfStudy().getStudentsLimit()>=declarationTempList.size()){
                    size= declarationTempList.size();
                }else{
                    size = declarationList.get(i).getIdFieldOfStudy().getStudentsLimit();
                }

                for(int l =0 ; l<size;l++) {
                    if (declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName()==declarationList.get(i).getIdFieldOfStudy().getFieldOfStudyName() && student.getId() == declarationTempList.get(l).getIdStudent().getId()) {
                        String result = declarationTempList.get(l).getIdUniversity().getUniversityName() + ", " + declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName() + ", priorytet: " + declarationTempList.get(l).getPriority();
                        universityLastName=declarationTempList.get(l).getIdUniversity().getIdUniversity();
                        resultsTempList.add(result);

                        for(int j =0 ;j<declarationList.size();j++){
                            if(declarationList.get(i).getIdUniversity().getIdUniversity()==declarationList.get(j).getIdUniversity().getIdUniversity()){
                                tempList.set(j,1);
                            }
                        }
                    }
                }

            }
            else if(declarationList.get(i).getPriority()==4 && student.getId()==declarationList.get(i).getIdStudent().getId() && tempList.get(i)!=1){
                int size = 0;

                List<Declaration> declarationTempList = session.createQuery("select decl from Declaration decl where decl.idFieldOfStudy = '" + declarationList.get(i).getIdFieldOfStudy().getIdFieldOfStudy() + "' and decl.priority=4 order by decl.examsScore desc").getResultList();


                if(declarationList.get(i).getIdFieldOfStudy().getStudentsLimit()>=declarationTempList.size()){
                    size= declarationTempList.size();
                }else{
                    size = declarationList.get(i).getIdFieldOfStudy().getStudentsLimit();
                }

                for(int l =0 ; l<size;l++) {
                    if (declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName()==declarationList.get(i).getIdFieldOfStudy().getFieldOfStudyName() && student.getId() == declarationTempList.get(l).getIdStudent().getId()) {
                        String result = declarationTempList.get(l).getIdUniversity().getUniversityName() + ", " + declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName() + ", priorytet: " + declarationTempList.get(l).getPriority();
                        universityLastName=declarationTempList.get(l).getIdUniversity().getIdUniversity();
                        resultsTempList.add(result);
                        for(int j =0 ;j<declarationList.size();j++){
                            if(declarationList.get(i).getIdUniversity().getIdUniversity()==declarationList.get(j).getIdUniversity().getIdUniversity()){
                                tempList.set(j,1);
                            }
                        }
                    }
                }

            }
            else if(declarationList.get(i).getPriority()==5 && student.getId()==declarationList.get(i).getIdStudent().getId() && tempList.get(i)!=1){
                int size = 0;

                List<Declaration> declarationTempList = session.createQuery("select decl from Declaration decl where decl.idFieldOfStudy = '" + declarationList.get(i).getIdFieldOfStudy().getIdFieldOfStudy() + "' and decl.priority=5 order by decl.examsScore desc").getResultList();


                if(declarationList.get(i).getIdFieldOfStudy().getStudentsLimit()>=declarationTempList.size()){
                    size= declarationTempList.size();
                }else{
                    size = declarationList.get(i).getIdFieldOfStudy().getStudentsLimit();
                }

                for(int l =0 ; l<size;l++) {
                    if (declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName()==declarationList.get(i).getIdFieldOfStudy().getFieldOfStudyName() && student.getId() == declarationTempList.get(l).getIdStudent().getId()) {
                        String result = declarationTempList.get(l).getIdUniversity().getUniversityName() + ", " + declarationTempList.get(l).getIdFieldOfStudy().getFieldOfStudyName() + ", priorytet: " + declarationTempList.get(l).getPriority();
                        universityLastName=declarationTempList.get(l).getIdUniversity().getIdUniversity();
                        resultsTempList.add(result);
                        for(int j =0 ;j<declarationList.size();j++){
                            if(declarationList.get(i).getIdUniversity().getIdUniversity()==declarationList.get(j).getIdUniversity().getIdUniversity()){
                                tempList.set(j,1);
                            }
                        }
                    }
                }

            }
        }


        ObservableList<String> resultsList = FXCollections.<String>observableArrayList(resultsTempList);

        results.getItems().addAll(resultsList);
    }

    public boolean validInputText(String text){
        System.out.println(text.length() + "  " + StringUtils.isEmptyOrWhitespaceOnly(text));
        if(text.length()>0 && !StringUtils.isEmptyOrWhitespaceOnly(text)){
            return true;
        }
        return false;
    }


    public void exitApp() {
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    public void setIdStudent(int id){
        this.idStudent = id;
    }


}
