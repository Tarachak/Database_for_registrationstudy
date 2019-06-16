package com.databasePoject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableCells {
    private SimpleStringProperty university;

    private SimpleIntegerProperty points;

    public TableCells(String university) {
        this.university = new SimpleStringProperty(university);
    }

    public TableCells(String university, int points) {
        this.university = new SimpleStringProperty(university);
        this.points = new SimpleIntegerProperty(points);
    }

    public SimpleStringProperty getUniversity() {
        return university;
    }

    public SimpleStringProperty universityProperty() {
        return university;
    }

    public void setUniversity(String university) {
        this.university.set(university);
    }

    public Integer getPoints() {
        return points.get();
    }

    public SimpleIntegerProperty pointsProperty() {
        return points;
    }

    public void setPoints(int points) {
        this.points.set(points);
    }
}
