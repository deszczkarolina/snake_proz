package com.proz.snake.controller;

import com.proz.snake.model.GameModel;
import com.proz.snake.model.Snake;
import com.proz.snake.view.Board;
import com.proz.snake.view.GameOver;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameController implements GameControllerInterface {

    private GameModel gameModel;
    private Board board;
    private Stage stage;
    private Scene scene;
    private VBox box;
    private Label score;

    public GameController(Stage stage, int size) {
        gameModel = new GameModel(size, this);
        this.stage = stage;
        board = new Board(gameModel.getWidth(), gameModel.getHeight());
        box = new VBox(board);
        scene = new Scene(box);
        score = new Label();
        box.getChildren().add(score);
        box.setAlignment(Pos.BOTTOM_RIGHT);
        stage.setScene(scene);

        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            gameModel.processKey(key);
        });

    }

    public void refreshScene() {

        if (!gameModel.isEnded()) {
            board.drawSnake(new Snake(gameModel.getSnake()));
        }
        board.drawApple(gameModel.getFruit().getX(), gameModel.getFruit().getY());
        score.setText("your score: " + gameModel.getScore());
    }

    public void gameOver() {

        gameModel.stopTimers();
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            new GameOver(stage, gameModel.getScore()).show();
        });
    }
}
