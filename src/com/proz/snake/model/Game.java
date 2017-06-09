package com.proz.snake.model;

import com.proz.snake.controller.SnakeCanvas;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game
{
    public static final int BOARDWIDTH = 20;
    public static final int BOARDHEIGHT = 20;

    private Field fruit;
    private Snake snake;
    private boolean shouldElongate;
    private Direction direction;

    private Timer logicTimer;
    private AnimationTimer animationTimer;
    private TimerTask timerTask;

    private SnakeCanvas snakeCanvas;
    private Scene scene;

    private boolean gameEnd = false;

    public Game() {
        snakeCanvas = new SnakeCanvas(BOARDWIDTH, BOARDHEIGHT);
        VBox box = new VBox(snakeCanvas);
        scene = new Scene(box);

        snake = new Snake(BOARDWIDTH / 2, BOARDHEIGHT / 2, 3);
        direction = Direction.UP;
        shouldElongate = false;
        fruit = new Field(1,1);
        generateFruit();

        logicTimer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                boolean isBitten = snake.move(direction, shouldElongate);
                if (isBitten || isBoardCollision()) {
                    logicTimer.cancel();
                    Platform.runLater(() -> {
                        Alert a = new Alert(Alert.AlertType.WARNING);
                        a.setHeaderText("UPS");
                        a.setContentText("GAME OVER");
                        a.showAndWait();
                    });
                }
                if (shouldElongate) {
                    shouldElongate = false;
                }

                if (snake.getSnakeHead().getX() == fruit.getX() && snake.getSnakeHead().getY() == fruit.getY())
                {
                    shouldElongate = true;
                    generateFruit();
                }
            }
        };

        logicTimer.scheduleAtFixedRate(timerTask, 0, 150);


        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long delta)
            {
                snakeCanvas.drawSnake(snake);
                snakeCanvas.drawApple(fruit.getX(), fruit.getY());
            }
        };

        animationTimer.start();

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    if (direction == Direction.DOWN)
                    {
                        break;
                    }
                    direction = Direction.UP;
                    break;
                case DOWN:
                    if (direction == Direction.UP)
                    {
                        break;
                    }
                    direction = Direction.DOWN;
                    break;
                case LEFT:
                    if (direction == Direction.RIGHT)
                    {
                        break;
                    }
                    direction = Direction.LEFT;
                    break;
                case RIGHT:
                    if (direction == Direction.LEFT)
                    {
                        break;
                    }
                    direction = Direction.RIGHT;
                    break;

            }
        });
    }

    public boolean isBoardCollision()
    {
        Field head = snake.getSnakeHead();
        return  (head.getX() < 0 || head.getX() == BOARDWIDTH || head.getY() < 0 || head.getY() == BOARDHEIGHT );
    }




    public void restart()
    {

        snake = new Snake(BOARDWIDTH/2,BOARDHEIGHT/2, 3);
        gameEnd = false;
    }

    public void generateFruit()
    {
        Random generator = new Random();
        int i;
        int j;
        do
        {
            i = generator.nextInt(BOARDWIDTH);
            j = generator.nextInt(BOARDHEIGHT);
        } while (snake.isCollision(i, j));
        fruit.setX(i);
        fruit.setY(j);

    }
    public Scene getScene()
    {
        return scene;
    }

}

