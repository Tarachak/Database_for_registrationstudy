package com.databasePoject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartAppController implements Initializable {

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputSurname;

    @FXML
    private Button newAccount;

    @FXML
    private Button logInButton;

    Session session;

    SessionFactory factory;

    int idStudent;

    boolean newAccountFlag = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        factory = new Configuration()
                .configure("com/databasePoject/hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(University.class)
                .addAnnotatedClass(Declaration.class)
                .addAnnotatedClass(FieldOfStudy.class)
                .buildSessionFactory();

        session = factory.getCurrentSession();
    }

    public void openNewWindow(int idStudent){
        System.out.println(idStudent);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller controller = loader.getController();
        controller.setIdStudent(idStudent);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Recruitment App");
        Scene scene = new Scene(root, 760, 580);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setOnHidden(e -> {
            controller.exitApp();
            Platform.exit();
        });
        primaryStage.show();
    }

    @FXML
    public void onClick(){
        StartAppController controller = new StartAppController();

        if(inputSurname.getText().length()>0 && inputName.getText().length()>0 && newAccountFlag==false) {
            try {
                session.beginTransaction();
                idStudent = (int) session.createQuery("select student.idStudent from Student student where student.name='" + inputName.getText() + "'" + " and student.surname='" + inputSurname.getText() + "'").getSingleResult();

                session.getTransaction().commit();

            } finally {
                session.close();
                factory.close();
            }
            controller.openNewWindow(idStudent);
        }else if(inputSurname.getText().length()>0 && inputName.getText().length()>0 && newAccountFlag==true){
            Student student = new Student(inputName.getText(),inputSurname.getText());
            try {
                session.beginTransaction();
                session.save(student);
                session.getTransaction().commit();
            } finally {
                session.close();
                factory.close();
            }
            controller.openNewWindow(student.getId());
        }
    }

    @FXML
    public void makeNewAccount(){
        newAccountFlag=true;
        newAccount.setDisable(true);
        logInButton.setText("Sing up & Log In");
    }

    public int getIdStudent(){
        return this.idStudent;
    }

}
