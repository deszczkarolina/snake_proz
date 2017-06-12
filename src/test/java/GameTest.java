import com.proz.snake.controller.GameController;
import com.proz.snake.model.Direction;
import com.proz.snake.model.GameModel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class GameTest {

    GameController gameController;
    GameModel game;


    @Before
    public void initialize() throws Exception {
        Thread thread = new Thread(() -> {
            new JFXPanel(); // Initializes the JavaFx Platform
            Platform.runLater(() -> {
                gameController = new GameController(new Stage(), 5);
            });
        });
        thread.start();
        Thread.sleep(1000);
        game = new GameModel(1, gameController);
    }

    @Test
    public void testGameModelInitialization() {

        assertNotNull(gameController);
        assertTrue(game.getHeight() == 10 && game.getWidth() == 10);
        assertTrue(game.getSnake().getLength() == 3);
        assertTrue(game.getScore() == 0);
        assertFalse(game.isEnded());
    }

    @Test
    public void testGenerateFruit() {
        game.generateFruit();
        assertTrue(game.getFruit().getX() < game.getWidth() && game.getFruit().getX() >= 0);
        assertTrue(game.getFruit().getY() < game.getHeight() && game.getFruit().getY() >= 0);
        assertFalse(game.getSnake().isCollision(game.getFruit().getX(), game.getFruit().getY()));
    }

    @Test
    public void testBoardCollision() {
        for (int i = 0; i <= 4; i++) {
            game.getSnake().move(Direction.RIGHT, false);
        }
        assertTrue(game.isBoardCollision());
    }

}


