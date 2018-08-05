package com.sda.javafx.controller;

import com.sda.javafx.model.PersonFX;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PersonDetailsController {

    @FXML
    private TextField name;

    @FXML
    private TextField lastname;

    @FXML
    private TextField street;

    @FXML
    private TextField city;

    @FXML
    private TextField postalCode;

    @FXML
    private TextField telephoneNumber;

    @FXML
    private PersonFX person;

    @FXML
    private Stage stage;

    @FXML
    public void initialize() {
        name.setText("name");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPerson (PersonFX person) {

        this.person =person;
        name.setText(person.getName());
        lastname.setText(person.getLastName());
        street.setText(person.getStreet());
        city.setText(person.getCity());
        postalCode.setText(person.getPostalCode());
        telephoneNumber.setText(person.getTelephone());
    }

    public void handleOk() {
        person.setName(name.getText());
        person.setLastName(lastname.getText());
        person.setCity(city.getText());
        person.setStreet(street.getText());
        person.setPostalCode(postalCode.getText());
        person.setTelephone(telephoneNumber.getText());
        this.stage.close();
    }

    public void handleCancel() {
        this.stage.close();
    }
}
