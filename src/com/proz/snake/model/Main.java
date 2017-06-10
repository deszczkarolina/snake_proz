package com.proz.snake.model;

import com.proz.snake.controller.Game;
import com.proz.snake.view.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("snake");
        primaryStage.setScene(new Menu(primaryStage).getScene());
        primaryStage.show();
     }
}
