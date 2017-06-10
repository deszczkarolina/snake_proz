package com.proz.snake;

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
        new Menu(primaryStage).show();
        primaryStage.show();
    }
}
