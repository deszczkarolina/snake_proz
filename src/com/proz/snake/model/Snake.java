package com.proz.snake.model;

import java.util.LinkedList;

public class Snake
{
    private LinkedList<Field> snakeBody;
    private int length;
    private Field snakeHead;

    public Snake()
    {
        snakeBody = new LinkedList<Field>();
        length = 0;
    }

    void move(Direction direction)
    {
        snakeHead = snakeBody.getFirst();
        Field newHead = snakeHead;
        switch (direction)
        {
            case UP:
                newHead = new Field(snakeHead.getX(), snakeHead.getY()+1, FieldType.SNAKE);
                break;
            case DOWN:
                newHead = new Field(snakeHead.getX(), snakeHead.getY()-1, FieldType.SNAKE);
                break;
            case LEFT:
                newHead = new Field(snakeHead.getX()-1, snakeHead.getY(), FieldType.SNAKE);
                break;
            case RIGHT:
                newHead = new Field(snakeHead.getX()+1, snakeHead.getY(), FieldType.SNAKE);
                break;
        }
        /**
         * TODO KONIEC GRY

        if (snakeBody.contains(newHead))  */
        snakeBody.removeLast();
        snakeBody.addFirst(newHead);
     }

     public int getLength()
    {
       return length;
    }
}
