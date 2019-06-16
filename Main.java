package com.databasePoject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.*;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startApp.fxml"));
        Parent root = loader.load();
        StartAppController controller = loader.getController();
        primaryStage.setTitle("Recruitment App");
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setOnHidden(e -> {
            Platform.exit();
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);


//        SessionFactory factory = new Configuration()
//                .configure("com/databasePoject/hibernate.cfg.xml")
//                .addAnnotatedClass(Student.class)
//                .addAnnotatedClass(University.class)
//                .addAnnotatedClass(Declaration.class)
//                .addAnnotatedClass(FieldOfStudy.class)
//                .buildSessionFactory();
//
//        Session session = factory.getCurrentSession();


        //create
//        try{
//            System.out.println("Create");
//            Student student = new Student("Krzysiek","Maniak",200);
//            session.beginTransaction();
//            System.out.println("Save");
//            session.save(student);
//            session.getTransaction().commit();
//            System.out.println("Done");
//        }
//        finally {
//            factory.close();
//        }

        //read one
//        try{
//            System.out.println("Read");
//            session.beginTransaction();
//            System.out.println("Get");
//            Student student = session.get(Student.class, 10);
//            System.out.println("Student:" + student.getId() + " " + student.getName());
//            session.getTransaction().commit();
//            System.out.println("Done");
//        }
//        finally {
//            factory.close();
//        }

        //query list
//        try{
//            System.out.println("Query");
//            session.beginTransaction();
//            System.out.println("Get");
//            List<Student> student = session.createQuery("from Student s where s.name like '%Piotr'").getResultList();
//            System.out.println("Student:" + student);
//            session.getTransaction().commit();
        //after commit session.getTransaction().commit();
//            System.out.println("Done");
//        }
//        finally {
//            factory.close();
//        }

        //update
//        try{
//            System.out.println("Update");
//            session.beginTransaction();
//            System.out.println("Get");
//            Student student = session.get(Student.class, 10);
//            student.setName("Kamilos");
//            System.out.println("Student:" + student);
//            session.getTransaction().commit();
//            System.out.println("Done");
//        }
//        finally {
//            factory.close();
//        }

        //update for all
//        try{
//            System.out.println("Update");
//            session.beginTransaction();
//
//            session.createQuery("update Student set examsScore='155'").executeUpdate();
//
//            session.getTransaction().commit();
//            System.out.println("Done");
//        }
//        finally {
//            factory.close();
//        }

          //delete
//        try{
//            System.out.println("Delete");
//            session.beginTransaction();
//
//            Student student = session.get(Student.class,10);
//            session.delete(student);
//
//            session.getTransaction().commit();
//            System.out.println("Done");
//        }
//        finally {
//            factory.close();
//        }

        //delete by query
//        try{
//            System.out.println("Delete");
//            session.beginTransaction();
//
//            session.createQuery("delete from Student where id=2");
//
//            session.getTransaction().commit();
//            System.out.println("Done");
//        }
//        finally {
//            factory.close();
//        }
    }
}
