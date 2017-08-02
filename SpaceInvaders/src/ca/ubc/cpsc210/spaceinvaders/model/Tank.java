package ca.ubc.cpsc210.spaceinvaders.model;

import java.awt.Color;

/**
 * Represents a tank
 */
public class Tank {
	
	public static final int SIZE_X = 15;
	public static final int SIZE_Y = 8;
	public static final int DX = 2;
	public static final int Y_POS = SIGame.HEIGHT - 40;
	public static final Color COLOR = new Color(250, 128, 20);
    // private static final int LEFT = -1;
    // private static final int RIGHT = 1;

	// private int direction;
    private boolean facesRight; /// <--- converted direction to be specified with a boolean instead of an integer as above
    private int x;

	// EFFECTS: places tank at position (x, Y_POS) facing right.
	public Tank(int x) {
		this.x = x;
		//this.facesRight = RIGHT;
        this.facesRight = true;
	}
	
	public int getX() {
		return x;
	}

    // EFFECTS: returns true if tank is facing right, false otherwise
    public boolean isFacingRight() {
        // return facesRight == RIGHT;
        return facesRight == true;
    }

	// MODIFIES: this
	// EFFECTS: tank is facing right
	public void faceRight() {
		// facesRight = RIGHT;
        facesRight = true;
	}

	// MODIFIES: this
	// EFFECTS: tank is facing left
	public void faceLeft() {
		// facesRight = LEFT;
        facesRight = false;
	}

	// MODIFIES: this
	// EFFECTS:  tank is moved DX units in whatever direction it is facing and is
	//           constrained to remain within vertical boundaries of game
	public void move() {
		// x = x + facesRight * DX;

        if (facesRight == true)
        {x = x + DX;}
        else
        {x = x - DX;}
		
		handleBoundary();
	}

	// MODIFIES: this
	// EFFECTS: tank is constrained to remain within vertical boundaries of game
	private void handleBoundary() {
		if (x < 0)
			x = 0;
		else if (x > SIGame.WIDTH)
			x = SIGame.WIDTH;
	}
}
