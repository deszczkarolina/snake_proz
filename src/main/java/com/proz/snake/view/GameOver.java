package com.proz.snake.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameOver {

    private static final String STYLE = "-fx-font: 30 arial; -fx-base: #0eb2ee;";
    private Scene scene;
    private Stage stage;
    private Button restartBttn;
    private VBox vBox;
    private Text loseMsg;

    public GameOver(Stage stage) {
        this.stage = stage;
        BorderPane root = new BorderPane();
        scene = new Scene(root);
        vBox = new VBox();
        root.setCenter(vBox);
        loseMsg = new Text("GAME OVER");
        loseMsg.setFont(new Font(20));
        restartBttn = new Button("Restart game");
        restartBttn.setOnAction(event -> {
            new Menu(stage).show();
        });

        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);
        restartBttn.setStyle(STYLE);
        vBox.setPadding(new Insets(100, 21, 100, 21));
        vBox.getChildren().addAll(loseMsg, restartBttn);
    }

    public void show() {
        stage.setScene(scene);
    }
}
