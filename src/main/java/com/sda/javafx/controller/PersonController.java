package com.sda.javafx.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.javafx.Main;
import com.sda.javafx.model.Person;
import com.sda.javafx.model.PersonFX;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonController {

    private Main main;

    @FXML
    private TableView<PersonFX> personTable;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label streetLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label postalCodeLabel;

    @FXML
    private Label telephoneLabel;

    @FXML
    private TableColumn<PersonFX, String> firstNameCol;

    @FXML
    private TableColumn<PersonFX, String> lastNameCol;

    @FXML
    private Button newButton;

    @FXML
    private Button deleteButton;

    @FXML
    public void handleNewButton() {
        PersonFX personFX = new PersonFX();
        this.main.loadNewPerson(personFX);
        main.getPersonFXList().add(personFX);
    }

    @FXML
    public void handleEditButton() {

        PersonFX selectedPerson = personTable.getSelectionModel().getSelectedItem();

        if (selectedPerson != null) {
            System.out.println(selectedPerson.getName());
            this.main.loadPersonEdit(selectedPerson);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("Brak zaznaczonego pracownika");
            alert.setContentText("Żaden pracownik nie został wybrany. Zaznacz pracownika i wybierz \"Edytuj\".");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleDeleteButton() {

        int index = personTable.getSelectionModel().getSelectedIndex();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Czy na pewno chcesz usunąć pracownika " +
                personTable.getSelectionModel().getSelectedItem().getName()
                + " " + personTable.getSelectionModel().getSelectedItem().getLastName() + " ?",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            if (index >= 0) {
                personTable.getItems().remove(index);
            }
        }
    }

    @FXML
    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(main.getStage());
        if (selectedFile != null) {

        }
    }

    @FXML
    public void saveFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<PersonFX> personFXlist = new ArrayList<>();

        for (PersonFX p : main.getPersonFXList()) {
            personFXlist.add(new PersonFX(p.getName(), p.getLastName(), p.getStreet(), p.getCity(), p.getPostalCode(), p.getTelephone()));
        }
        File filename = new File("pracownicy.json");
        mapper.writeValue(filename, personFXlist);
    }

    @FXML
    public void saveAsFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<PersonFX> personFXlist = new ArrayList<>();
        for (PersonFX p : main.getPersonFXList()) {
            personFXlist.add(new PersonFX(p.getName(), p.getLastName(), p.getStreet(), p.getCity(), p.getPostalCode(), p.getTelephone()));
        }
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(main.getStage());
        mapper.writeValue(file, personFXlist);
    }

    @FXML
    public void handleHelpItemMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "To jest aplikacja do zapisywania danych pracowników.");
        alert.setHeaderText("");
        alert.setTitle("Informacje");
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        firstNameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
        lastNameCol.setCellValueFactory(cell -> cell.getValue().lastNameProperty());

        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldField, newField) -> showPersonDetails(newField));
    }

    private void showPersonDetails(PersonFX person) {
        firstNameLabel.setText(person.getName());
        lastNameLabel.setText(person.getLastName());
        streetLabel.setText(person.getStreet());
        cityLabel.setText(person.getCity());
        postalCodeLabel.setText(person.getPostalCode());
        telephoneLabel.setText(person.getTelephone());
    }

    public void setMain(Main main) {
        this.main = main;
        personTable.setItems(main.getPersonFXList());
    }
}
