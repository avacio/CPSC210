package ca.ubc.cs.cpsc210.snake.tests;

import ca.ubc.cs.cpsc210.snake.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

// jUnit tests for Snake class
public class SnakeTest {
    private Snake testSnake;

    @Before
    public void runBefore() {
        testSnake = new Snake(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2));
    }

    // TODO: complete the unit tests for the Snake class

    @Test
    public void testConstructor() {
        assertEquals(1, testSnake.length());
        assertFalse(testSnake.canGrow());
    }

    @Test
    public void testMoveRight() {
        setSnakeDirection(testSnake, Direction.RIGHT);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 1), testSnake.getPosition());
    }

    @Test
    public void testMoveLeft() {
        setSnakeDirection(testSnake, Direction.LEFT);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 - 1), testSnake.getPosition());
    }

    @Test
    public void testMoveUp() {
        setSnakeDirection(testSnake, Direction.UP);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2 - 1, SnakeGame.BOARD_COLS / 2), testSnake.getPosition());
    }

    @Test
    public void testMoveDown() {
        setSnakeDirection(testSnake, Direction.DOWN);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2 + 1, SnakeGame.BOARD_COLS / 2), testSnake.getPosition());
    }

    @Test
    public void testMoveBody() {
        setSnakeDirection(testSnake, Direction.RIGHT);
        growBodyByTwo();
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 3), testSnake.getPosition());

        List<Cell> body = testSnake.getBodyPositions();
        assertEquals(2, body.size());
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 2), body.get(0));
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 1), body.get(1));
    }

    @Test
    public void testGrowOnFeed() {
        Food food = new Food(testSnake.getPosition(), Snake.NUTRITION_TO_GROW);
        testSnake.eat(food);
        assertEquals(1, testSnake.length());
        assertTrue(testSnake.canGrow());

        testSnake.move();
        assertEquals(2, testSnake.length());
        assertFalse(testSnake.canGrow());

        // Grow again
        Food food2 = new Food(testSnake.getPosition(), 2 * Snake.NUTRITION_TO_GROW);
        testSnake.eat(food2);
        assertEquals(2, testSnake.length());
        assertTrue(testSnake.canGrow());
        testSnake.move();
        testSnake.move();
        assertEquals(4, testSnake.length());
        assertFalse(testSnake.canGrow());

        // Eat less than what it takes to grow
        Food food3 = new Food(testSnake.getPosition(), Snake.NUTRITION_TO_GROW / 2);
        testSnake.eat(food3);
        assertEquals(4, testSnake.length());
        assertFalse(testSnake.canGrow());
        testSnake.move();
        assertEquals(4, testSnake.length());
        assertFalse(testSnake.canGrow());

        Food food4 = new Food(testSnake.getPosition(), Snake.NUTRITION_TO_GROW / 4);
        testSnake.eat(food4);
        assertEquals(4, testSnake.length());
        assertFalse(testSnake.canGrow());
        testSnake.move();
        assertEquals(4, testSnake.length());
        assertFalse(testSnake.canGrow());

        Food food5 = new Food(testSnake.getPosition(), Snake.NUTRITION_TO_GROW / 3);
        testSnake.eat(food5);
        assertEquals(4, testSnake.length());
        assertTrue(testSnake.canGrow());
        testSnake.move();
        assertEquals(5, testSnake.length());
        assertFalse(testSnake.canGrow());
    }

    // EFFECTS: rotate snake until it is facing in direction d
    private void setSnakeDirection(Snake snake, Direction d) {
        while (snake.getDirection() != d)
            snake.rotateLeft();
    }

    // MODIFIES: this
    // EFFECTS:  get snake to eat enough food so that its body has length 2
    private void growBodyByTwo() {
        Food food = new Food(testSnake.getPosition(), 2 * Snake.NUTRITION_TO_GROW);
        testSnake.eat(food);
        testSnake.move();
        testSnake.move();
        assertEquals(3, testSnake.length()); // including head
    }

    // MODIFIES: this
    // EFFECTS:  get snake to eat enough food so that its body has length 3
    @Test
    public void growBodyByThree() {
        Food TastyFood = new Food(testSnake.getPosition(), 3 * Snake.NUTRITION_TO_GROW);
        testSnake.eat(TastyFood);
        testSnake.move();
        testSnake.move();
        testSnake.move();
        assertEquals(4, testSnake.length()); // including head
    }

    @Test
    public void testRotateLeft() {
        testSnake.rotateLeft();
        assertEquals(Direction.UP, testSnake.getDirection());
        testSnake.rotateLeft();
        assertEquals(Direction.LEFT, testSnake.getDirection());
        testSnake.rotateLeft();
        assertEquals(Direction.DOWN, testSnake.getDirection());
        testSnake.rotateLeft();
        assertEquals(Direction.RIGHT, testSnake.getDirection());
    }

    @Test
    public void testRotateRight() {
        testSnake.rotateRight();
        assertEquals(Direction.DOWN, testSnake.getDirection());
        testSnake.rotateRight();
        assertEquals(Direction.LEFT, testSnake.getDirection());
        testSnake.rotateRight();
        assertEquals(Direction.UP, testSnake.getDirection());
        testSnake.rotateRight();
        assertEquals(Direction.RIGHT, testSnake.getDirection());
    }

    @Test
    public void testMoveOutOfBounds() {
        Snake test2Snake = new Snake(new Cell(SnakeGame.BOARD_COLS / 2, SnakeGame.BOARD_COLS));
        test2Snake.move();
        assertEquals(new Cell(SnakeGame.BOARD_COLS / 2, SnakeGame.BOARD_COLS + 1), test2Snake.getPosition());
        assertEquals(1, test2Snake.length());
    }


}