package com.proz.snake.view;


import com.proz.snake.controller.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class Menu {

    private Stage stage;
    private Scene scene;
    private Game game;
    private VBox vBox;
    private Button startBttn;
    private Text chooseSize;
    private ComboBox boardSize;
    private static final String FONT = "-fx-font: 30 arial; -fx-base: #0eb2ee;";

    public Menu (Stage stage)
    {
        this.stage = stage;
        BorderPane root = new BorderPane();
        this.scene = new Scene(root);
        vBox = new VBox();
        root.setCenter(vBox);

        Text chooseSize = new Text("choose board size");
        ObservableList<String> sizeList = FXCollections.observableArrayList (
                "small", "medium", "big");
        boardSize = new ComboBox(sizeList);
        boardSize.setPrefWidth(150);
        boardSize.setPrefHeight(50);

        startBttn = new Button("New game");
        startBttn.setOnAction(event -> {
                String newValue = (String)boardSize.getSelectionModel().selectedItemProperty().get();
                if (newValue == "small")  { game = new Game(stage, 1); }
                else if (newValue == "medium" || newValue == null ) { game = new Game(stage, 2); }
                else if (newValue == "big") { game = new Game(stage, 3); }

        });

        startBttn.setStyle(FONT);
        startBttn.setMaxWidth(Double.MAX_VALUE);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(100, 30, 100, 30));
        vBox.getChildren().addAll(startBttn, chooseSize, boardSize);
    }


    public Scene getScene()
    {
        return scene;
    }

}
