package com.proz.snake.model;

public class Field
{
    private FieldType type;
    private int x;
    private int y;


    public Field()
    {
        type = FieldType.EMPTY;
    }

    public Field (int x, int y, FieldType type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public FieldType getType()
    {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setType(FieldType type)
    {
        this.type = type;
    }

    public boolean isEqualPosition (Field other)
    {
        return (this.x == other.getX() && this.y == other.getY());
    }


}
