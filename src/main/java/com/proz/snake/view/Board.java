package com.proz.snake.view;

import com.proz.snake.model.Snake;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board extends Canvas {
    private static final int GRID_SIZE = 16;
    private int width;
    private int height;

    public Board(int width, int height) {
        super(width * GRID_SIZE, height * GRID_SIZE);
        this.width = width;
        this.height = height;
    }

    public void drawSnake(Snake snake) {
        GraphicsContext ctx = getGraphicsContext2D();
        ctx.clearRect(0, 0, width * GRID_SIZE, height * GRID_SIZE);
        ctx.setFill(Color.LIGHTGRAY);
        ctx.fillRect(0, 0, width * GRID_SIZE, height * GRID_SIZE);
        ctx.setStroke(Color.LIGHTGRAY);
        ctx.setLineWidth(1);
        snake.getSnakeBody().forEach(field -> {
            ctx.setFill(Color.RED);
            ctx.fillRect(GRID_SIZE * field.getX(), GRID_SIZE * field.getY(), GRID_SIZE, GRID_SIZE);
            ctx.strokeRect(GRID_SIZE * field.getX(), GRID_SIZE * field.getY(), GRID_SIZE, GRID_SIZE);
        });
    }

    public void drawApple(final int x, int y) {
        GraphicsContext ctx = getGraphicsContext2D();
        ctx.setFill(Color.GREEN);
        ctx.setStroke(Color.LIGHTGRAY);
        ctx.setLineWidth(1);
        ctx.fillRect(GRID_SIZE * x, GRID_SIZE * y, GRID_SIZE, GRID_SIZE);
        ctx.strokeRect(GRID_SIZE * x, GRID_SIZE * y, GRID_SIZE, GRID_SIZE);
    }

}
