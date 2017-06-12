package com.proz.snake.model;

import com.proz.snake.controller.GameControllerInterface;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameModel {

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

    private int width;
    private int height;
    private boolean gameEnd = false;
    private GameControllerInterface gameControllerInterface;

    public GameModel(int size, GameControllerInterface game) {
        this.gameControllerInterface = game;
        width = size * BASEBOARDWIDTH;
        height = size * BASEBOARDHEIGHT;

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
                    gameEnd = true;
                    gameControllerInterface.gameOver();
                }
                if (shouldElongate) {
                    shouldElongate = false;
                }

                if (snake.getHead().getX() == fruit.getX() && snake.getHead().getY() == fruit.getY()) {
                    shouldElongate = true;
                    generateFruit();
                }
            }
        };

        logicTimer.scheduleAtFixedRate(timerTask, 0, 150);
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long delta) {
                gameControllerInterface.refreshScene();
            }
        };
        animationTimer.start();
    }

    public void processKey(KeyCode key) {
        switch (key) {
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
    }

    public void stopTimers() {
        logicTimer.cancel();
        animationTimer.stop();
    }

    public boolean isBoardCollision() {
        Field head = snake.getHead();
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

    public Snake getSnake() {
        return snake;
    }

    public Field getFruit() {
        return fruit;
    }

    public boolean isEnded() {
        return gameEnd;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScore() {
        return snake.getLength() - 3;
    }

    public GameControllerInterface getInterface() {
        return this.gameControllerInterface;
    }
}

