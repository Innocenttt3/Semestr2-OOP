package com.example.kolos2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class HelloController {
    public Label label;
    public Slider slider;

    @FXML
    public void initialize() {
        label.setText("Promień: " + (int) slider.getValue());

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText("Promień: " + newValue.intValue());
        });
    }
}