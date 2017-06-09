package com.proz.snake.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Game().getScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
