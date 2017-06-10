package com.proz.snake.model;

import java.util.LinkedList;

public class Snake {
    private LinkedList<Field> snakeBody;
    private Field snakeHead;

    public Snake(int x, int y, int size) {
        snakeBody = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            snakeBody.add(new Field(x, y + i));
        }
        snakeHead = snakeBody.getFirst();
    }

    public boolean move(Direction direction, boolean shouldElongate) {
        snakeHead = snakeBody.getFirst();
        Field newHead = snakeHead;
        switch (direction) {
            case UP:
                newHead = new Field(snakeHead.getX(), snakeHead.getY() - 1);
                break;
            case DOWN:
                newHead = new Field(snakeHead.getX(), snakeHead.getY() + 1);
                break;
            case LEFT:
                newHead = new Field(snakeHead.getX() - 1, snakeHead.getY());
                break;
            case RIGHT:
                newHead = new Field(snakeHead.getX() + 1, snakeHead.getY());
                break;
        }
        if (!shouldElongate) snakeBody.removeLast();
        if (snakeBody.contains(newHead)) {
            return true;
        }

        snakeBody.addFirst(newHead);
        return false;
    }

    public boolean isCollision(int x, int y) {
        return (snakeBody.contains(new Field(x, y)));
    }

    public LinkedList<Field> getSnakeBody() {
        return new LinkedList<>(snakeBody);
    }

    public Field getSnakeHead() {
        return snakeBody.getFirst();
    }

}
