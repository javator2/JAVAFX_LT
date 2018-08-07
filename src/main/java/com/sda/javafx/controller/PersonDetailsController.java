package com.sda.javafx.controller;

import com.sda.javafx.Main;
import com.sda.javafx.model.PersonFX;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PersonDetailsController {

    private Main main;
    private PersonFX personFX;
    private Stage stage;

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
    public void initialize() {
        name.setText("name");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setPersonFX(PersonFX personFX) {
        this.personFX = personFX;
        name.setText(personFX.getName());
        lastname.setText(personFX.getLastName());
        street.setText(personFX.getStreet());
        city.setText(personFX.getCity());
        postalCode.setText(personFX.getPostalCode());
        telephoneNumber.setText(personFX.getTelephone());
    }

    public void handleOk() {
        personFX.setName(name.getText());
        personFX.setLastName(lastname.getText());
        personFX.setCity(city.getText());
        personFX.setStreet(street.getText());
        personFX.setPostalCode(postalCode.getText());
        personFX.setTelephone(telephoneNumber.getText());
        this.stage.close();
    }

    public void handleCancel() {
        this.stage.close();
    }

}
