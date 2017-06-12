import com.proz.snake.model.Direction;
import com.proz.snake.model.Snake;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SnakeTest {


    @Test
    public void testCreateSnake() {
        Snake snake = new Snake(5, 5, 3);
        assertTrue(snake.getLength() == 3);
        assertTrue(snake.getHead().getX() == 5 && snake.getHead().getY() == 5);
    }

    @Test
    public void testCopySnake() {
        Snake snake = new Snake(5, 5, 3);
        Snake other = new Snake(snake);
        assertTrue(other.getLength() == 3);
        assertTrue(other.getHead().getX() == 5 && other.getHead().getY() == 5);
    }


    @Test
    public void testElongate() {
        Snake snake = new Snake(5, 5, 3);
        snake.move(Direction.UP, true);
        assertTrue(snake.getLength() == 4);
    }

    @Test
    public void testIllegalDirectionChange() {
        Snake snake = new Snake(5, 5, 3);
        snake.move(Direction.RIGHT, false);
        assertTrue(snake.getHead().getX() == 6 && snake.getHead().getY() == 5);
        snake.move(Direction.LEFT, false);
        assertTrue(snake.getHead().getX() == 6 && snake.getHead().getY() == 5);
    }

    @Test
    public void testBittingItself() {
        Snake snake = new Snake(5, 5, 10);
        snake.move(Direction.RIGHT, false);
        snake.move(Direction.LEFT, false);
        assertTrue(snake.move(Direction.LEFT, false));
    }

    @Test
    public void testSnakeCollision() {
        Snake snake = new Snake(5, 5, 10);
        assertTrue(snake.isCollision(5, 6));
    }
}
