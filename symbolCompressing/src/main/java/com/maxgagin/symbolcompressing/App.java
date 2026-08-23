package com.maxgagin.symbolcompressing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        var label = new Label("Hello world!");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
        System.out.println("what?");
    }

    public static void main(String[] args) {
        launch();
    }

}