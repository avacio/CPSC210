package ca.ubc.cs.cpsc210.snake.tests;

import ca.ubc.cs.cpsc210.snake.model.Cell;
import ca.ubc.cs.cpsc210.snake.model.Food;
import ca.ubc.cs.cpsc210.snake.model.SnakeGame;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


// jUnit tests for Food class
public class FoodTest {
    private Food testFood;

    @Before
    public void runBefore() {
        testFood = new Food(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2));
    }

    @Test
    public void testFood() {
        assertEquals(Food.INITIAL_NUTRITIONAL_VALUE, testFood.getNutritionalValue());
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2), testFood.getPosition());
    }

    @Test
    public void testFoodOther() {
        Food other = new Food((new Cell(0, 0)), 50);
        assertEquals(50, other.getNutritionalValue());
        assertEquals(new Cell(0, 0), other.getPosition());
    }
    @Test
    public void testDecay() {
        testFood.decay();
        assertEquals(Food.INITIAL_NUTRITIONAL_VALUE - Food.DECAY_AMOUNT, testFood.getNutritionalValue());

        // test bounds for nutritional value at 0
        Food LOW_BOUND = new Food((new Cell(0, 0)), 5);
        LOW_BOUND.decay();
        assertEquals(0, LOW_BOUND.getNutritionalValue());
    }



}