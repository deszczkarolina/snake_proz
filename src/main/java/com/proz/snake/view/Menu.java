package com.proz.snake.view;

import com.proz.snake.controller.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Menu {

    private static final String STYLE = "-fx-font: 30 arial; -fx-base: #0eb2ee;";
    private Stage stage;
    private Scene scene;
    private VBox vBox;
    private Button startBttn;
    private Text chooseSize;
    private ComboBox boardSize;

    public Menu(Stage stage) {
        this.stage = stage;
        BorderPane root = new BorderPane();
        this.scene = new Scene(root);
        vBox = new VBox();
        root.setCenter(vBox);

        chooseSize = new Text("choose board size");
        ObservableList<String> sizeList = FXCollections.observableArrayList(
                "small", "medium", "large");
        boardSize = new ComboBox<>(sizeList);
        boardSize.setPrefWidth(150);
        boardSize.setPrefHeight(40);

        boardSize.getSelectionModel().select("small");

        startBttn = new Button("New game");
        startBttn.setOnAction(event -> {
            String newValue = (String) boardSize.getSelectionModel().selectedItemProperty().get();
            if (newValue.equals("small")) {
                new Game(stage, 1);
            } else if (newValue.equals("medium") || newValue.isEmpty()) {
                new Game(stage, 2);
            } else if (newValue.equals("large")) {
                new Game(stage, 3);
            }

        });

        startBttn.setStyle(STYLE);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(100, 40, 78, 40));
        vBox.getChildren().addAll(startBttn, chooseSize, boardSize);
    }

    public void show() {
        stage.setScene(scene);
    }
}
