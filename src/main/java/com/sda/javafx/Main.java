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

    public ObservableList<PersonFX> getPersonList() {
        return personList;
    }

    private ObservableList<PersonFX> personList = FXCollections.observableArrayList();
    private List<PersonFX> personFXlist = new ArrayList<>();

    public Main() throws IOException {
        personFXlist.add(new PersonFX("Jan", "Kowalski", "Mickiewicza", "Poznań", "78-109", "555444222"));
        personFXlist.add(new PersonFX("Piotr", "Nowak", "Lubicka", "Toruń", "87-100", "111222333"));
        personFXlist.add(new PersonFX("Adam", "Nowakowski", "Mickiewicza", "Poznań", "78-109", "555444222"));
        personFXlist.add(new PersonFX("Anna", "Wiśniewska", "Lubicka", "Toruń", "87-100", "111222333"));
        personFXlist.add(new PersonFX("Krzysztof", "Zanussi", "Mickiewicza", "Poznań", "78-109", "555444222"));
        personFXlist.add(new PersonFX("Barbara", "Barbarowska", "Lubicka", "Toruń", "87-100", "111222333"));


        ObjectMapper mapper = new ObjectMapper();
        File fileName = new File("pracownicy.json");
        fileName.createNewFile();
        mapper.writeValue(fileName, personFXlist);

        Person[] readPerson = mapper.readValue(new File("pracownicy.json"), Person[].class);

        ObservableList<PersonFX> personListFX = FXCollections.observableArrayList();

        for (Person p : readPerson) {
            System.out.println(p.getName());
            personList.add(new PersonFX(p.getName(), p.getLastName(), p.getStreet(), p.getCity(), p.getPostalCode(), p.getTelephone()));
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
        newPersonStage.setTitle("dodaj nowego pracownika");
        Scene scene = new Scene(window);
        newPersonStage.setScene(scene);
        newPersonStage.show();
    }

    public void loadPersonEdit(PersonFX person) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/PersonEdit.fxml"));
        VBox window = null;
        try {
            window = (VBox) loader.load();
            PersonDetailsController personDetails = loader.getController();
            personDetails.setPerson(person);
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
