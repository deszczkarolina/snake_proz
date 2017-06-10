package com.proz.snake.view;

import com.proz.snake.controller.Game;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameOver {

    private Scene scene;
    private Stage stage;
    private Button restartBttn;
    private VBox vBox;
    private Menu menu;


    public GameOver(Stage stage) {
        this.stage = stage;
        BorderPane root = new BorderPane();
        scene = new Scene(root);
        stage.setScene(scene);
        vBox = new VBox();
        root.setCenter(vBox);
        restartBttn = new Button("New game");
        restartBttn.setOnAction(event -> {
            menu = new Menu(stage);
        });

        restartBttn.setStyle("-fx-font: 30 arial; -fx-base: #0eb2ee;");
        vBox.setPadding(new Insets(100, 30, 100, 30));
        vBox.getChildren().addAll(restartBttn);
    }

    public Scene getScene() {
        return scene;
    }

}
