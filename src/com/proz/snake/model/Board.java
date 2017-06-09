package com.proz.snake.model;

import java.util.Random;

public class Board
{
    private Field[][] board;
    private int width;
    private int height;


    public Board(int width, int height)
    {
        this.width = width;
        this.height = height;
        board = new Field[width][height];
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                board[i][j] = new Field();
            }
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public FieldType getFieldType(int x, int y)
    {
        return board[x][y].getType();
    }

    public boolean isFieldInsideBoard(int x, int y)
    {
        return !(x >= width || y >= height || x < 0 || y < 0 );
    }


    public boolean isFruit(int x, int y)
    {
        return (board[x][y].getType() == FieldType.FRUIT );
    }

    public void insertFruit()
    {
        Random generator = new Random();
        int i = generator.nextInt(width) + 1;
        int j = generator.nextInt(height) + 1;
        board[i][j].setType(FieldType.FRUIT);
    }
}
