package com.sda.javafx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.javafx.controller.PersonController;
import com.sda.javafx.controller.PersonDetailsController;
import com.sda.javafx.model.Person;
import com.sda.javafx.model.PersonFX;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage stage;
    private VBox layout;

    public ObservableList<PersonFX> getPersonFXList() {
        return personFXList;
    }

    private ObservableList<PersonFX> personFXList = FXCollections.observableArrayList();
    private List<Person> personList = new ArrayList<Person>();

    public Main() throws IOException {
        personList.add(new Person("Jan", "Kowalski", "Mickiewicza", "Poznań", "78-109", "555444222"));
        personList.add(new Person("Piotr", "Nowak", "Lubicka", "Toruń", "87-100", "111222333"));
        personList.add(new Person("Adam", "Nowakowski", "Mickiewicza", "Poznań", "78-109", "555444222"));
        personList.add(new Person("Anna", "Wiśniewska", "Lubicka", "Toruń", "87-100", "111222333"));
        personList.add(new Person("Krzysztof", "Zanussi", "Mickiewicza", "Poznań", "78-109", "555444222"));
        personList.add(new Person("Barbara", "Barbarowska", "Lubicka", "Toruń", "87-100", "111222333"));


        ObjectMapper mapper = new ObjectMapper();
        File fileName = new File("pracownicy.json");
        fileName.createNewFile();
        mapper.writeValue(fileName, personList);

        Person[] readPerson = mapper.readValue(new File("pracownicy.json"), Person[].class);

        ObservableList<PersonFX> personListFX = FXCollections.observableArrayList();

        for (Person p : readPerson) {
            System.out.println(p.getName());
            personFXList.add(new PersonFX(p.getName(), p.getLastName(), p.getStreet(), p.getCity(), p.getPostalCode(), p.getTelephone()));
        }
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

    public void loadNewPerson(PersonFX personFX) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/NewPerson.fxml"));
        VBox window = null;
        try {
            window = (VBox) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PersonDetailsController personDetails = loader.getController();
        personDetails.setMain(this);
        Stage newPersonStage = new Stage();
        personDetails.setStage(newPersonStage);
        personDetails.setPersonFX(personFX);
        newPersonStage.setTitle("dodaj nowego pracownika");
        Scene scene = new Scene(window);
        newPersonStage.setScene(scene);
        newPersonStage.show();
    }

    public void loadPersonEdit(PersonFX personFX) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/PersonEdit.fxml"));
        VBox window = null;
        try {
            window = (VBox) loader.load();
            PersonDetailsController personDetails = loader.getController();
            personDetails.setPersonFX(personFX);
            Stage stage = new Stage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage editStage = new Stage();
        PersonDetailsController personDetails = loader.getController();
        editStage.setTitle("edytuj osobę");
        personDetails.setStage(editStage);
        Scene scene = new Scene(window);
        editStage.setScene(scene);
        editStage.show();
    }

    public Stage getStage() {
        return stage;
    }
}
