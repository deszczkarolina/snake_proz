package com.proz.snake.controller;

import com.proz.snake.model.Direction;
import com.proz.snake.model.Field;
import com.proz.snake.model.Snake;
import com.proz.snake.view.Board;
import com.proz.snake.view.GameOver;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    public static final int BASEBOARDWIDTH = 10;
    public static final int BASEBOARDHEIGHT = 10;

    private Field fruit;
    private Snake snake;
    private boolean shouldElongate;
    private Direction currentDirection;
    private Direction newDirection;

    private Timer logicTimer;
    private AnimationTimer animationTimer;
    private TimerTask timerTask;

    private Board board;
    private int width;
    private int height;
    private Stage stage;
    private Scene scene;


    private boolean gameEnd = false;

    public Game(Stage stage, int size) {

        this.stage = stage;
        width = size * BASEBOARDWIDTH;
        height = size * BASEBOARDHEIGHT;
        board = new Board(width, height);
        VBox box = new VBox(board);
        scene = new Scene(box);
        stage.setScene(scene);

        snake = new Snake(width / 2, height / 2, 3);
        newDirection = Direction.UP;
        shouldElongate = false;
        fruit = new Field(1, 1);
        generateFruit();

        logicTimer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                currentDirection = newDirection;
                boolean isBitten = snake.move(currentDirection, shouldElongate);
                if (isBitten || isBoardCollision()) {
                    logicTimer.cancel();
                    Platform.runLater(() -> {
                        GameOver lost = new GameOver(stage);
                        // gameEnd = true;
                        //  Alert a = new Alert(Alert.AlertType.WARNING);
                        // a.setHeaderText("UPS");
                        // a.setContentText("GAME OVER");
                        // a.showAndWait();
                    });
                }
                if (shouldElongate) {
                    shouldElongate = false;
                }

                if (snake.getSnakeHead().getX() == fruit.getX() && snake.getSnakeHead().getY() == fruit.getY()) {
                    shouldElongate = true;
                    generateFruit();
                }
            }
        };

        logicTimer.scheduleAtFixedRate(timerTask, 0, 150);


        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long delta) {
                if (!gameEnd) {
                    board.drawSnake(snake);
                }
                board.drawApple(fruit.getX(), fruit.getY());
            }
        };

        animationTimer.start();

        scene.setOnKeyPressed(key -> {
            switch (key.getCode()) {
                case UP:
                    if (currentDirection == Direction.DOWN) {
                        break;
                    }
                    newDirection = Direction.UP;
                    break;
                case DOWN:
                    if (currentDirection == Direction.UP) {
                        break;
                    }
                    newDirection = Direction.DOWN;
                    break;
                case LEFT:
                    if (currentDirection == Direction.RIGHT) {
                        break;
                    }
                    newDirection = Direction.LEFT;
                    break;
                case RIGHT:
                    if (currentDirection == Direction.LEFT) {
                        break;
                    }
                    newDirection = Direction.RIGHT;
                    break;

            }
        });
    }

    public boolean isBoardCollision() {
        Field head = snake.getSnakeHead();
        return (head.getX() < 0 || head.getX() == width || head.getY() < 0 || head.getY() == height);
    }

    public void generateFruit() {
        Random generator = new Random();
        int fruitX;
        int fruitY;
        do {
            fruitX = generator.nextInt(width);
            fruitY = generator.nextInt(height);
        } while (snake.isCollision(fruitX, fruitY));
        fruit.setX(fruitX);
        fruit.setY(fruitY);
    }


}

