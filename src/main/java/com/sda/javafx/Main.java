package com.sda.javafx;

import com.sda.javafx.controller.PersonController;
import com.sda.javafx.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;

public class Main extends Application {

    private Stage stage;
    private VBox layout;

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    public Main() {
        personList.add(new Person("Jan", "Kowalski"));
        personList.add(new Person("Jan", "Kowalski"));
        personList.add(new Person("Jan", "Kowalski"));
        personList.add(new Person("Jan", "Kowalski"));
        personList.add(new Person("Jan", "Kowalski"));
        personList.add(new Person("Jan", "Kowalski"));

    }

    public static void main(String[] args) {
        launch();


    }

    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setTitle("Moja pierwsza aplikacja w JavaFX");
        loadView();
    }

    public void loadView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/javafx.fxml"));
            layout = (VBox) loader.load();

            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();

            PersonController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadNewPerson() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/NewPerson.fxml"));
        VBox window = null;
        try {
            window = (VBox) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage newPersonStage = new Stage();
        newPersonStage.setTitle("dodaj nową osobę");
        Scene scene = new Scene(window);
        newPersonStage.setScene(scene);
        newPersonStage.show();
    }

    public void loadPersonEdit() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/PersonEdit.fxml"));
        VBox window = null;
        try {
            window = (VBox) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage editStage = new Stage();
        editStage.setTitle("edytuj osobę");
        Scene scene = new Scene(window);
        editStage.setScene(scene);
        editStage.show();
    }
}
