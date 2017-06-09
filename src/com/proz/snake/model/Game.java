package com.proz.snake.model;

public class Game
{
    public static final int BOARDWIDTH = 20;
    public static final int BOARDHEIGHT = 20;

    private Board board;
    private Snake snake;
    private boolean gameEnd = false;

    public Game()
    {
        board = new Board(BOARDWIDTH, BOARDHEIGHT);
        snake = new Snake();
    }

    public boolean isEnded()
    {
        return gameEnd;
    }

    public void restart()
    {
        board = new Board(BOARDWIDTH,BOARDHEIGHT);
        snake = new Snake();
        gameEnd = false;
    }
}
