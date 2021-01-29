/*
 * AshleyMonkeyGame - Monkey.java
 * Purpose: sets the monkey's image, speed and position
 * Author: Ashley Kim
 * Date: October 28, 2020
 * Course: ICS4U1
 */

package monkeygame;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Monkey {

	double x = 100;
	double y = 100;
	double dx = 0;
	double dy = 0;
	// sets the static int value of the speed of monkey, which 
	// has to be accessible in other class
	static int speed = 3;

	int lives = 3;

	String left = "LEFT";
	String right = "RIGHT";
	String up = "UP";
	String down = "DOWN";

	String imageName = "images/monkey.png";
	Image image = new Image(imageName);

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	// An arrayList of key inputs
	ArrayList<String> input;

	public Monkey(GraphicsContext gc, Canvas gameCanvas, ArrayList<String> input) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.input = input;
	}

	public Monkey(String imageName, GraphicsContext gc, Canvas gameCanvas, ArrayList<String> input) {
		this.imageName = imageName;
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.input = input;
	}

	// Randomly place monkey in canvas
	public void randomXY() {
		this.x = (int) (Math.random() * (gameCanvas.getWidth() - this.image.getWidth()));
		this.y = (int) (Math.random() * (gameCanvas.getHeight() - this.image.getHeight()));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	// Controls the monkey's movement corresponding to the key input
	public void move() {

		// left button
		if (this.input.contains(this.left)) {
			this.dx = -Monkey.speed;
		} else if (this.input.contains(this.right)) {
			this.dx = Monkey.speed;
		} else {
			this.dx = 0;
		}

		// up button
		if (this.input.contains(this.up)) {
			this.dy = -Monkey.speed;
		} else if (this.input.contains(this.down)) {
			this.dy = Monkey.speed;
		} else {
			this.dy = 0;
		}

		// save old position
		double x = this.x;
		double y = this.y;

		// Adds the new dx and dy value to current position to make it move
		this.x += this.dx;
		this.y += this.dy;

		if (this.x < 0 || this.x > gameCanvas.getWidth() - this.image.getWidth()) {
			this.x = x;
			this.y = y;
		}
		if (this.y < 0 || this.y > gameCanvas.getHeight() - this.image.getHeight()) {
			this.x = x;
			this.y = y;
		}
		// displays the image in corresponding position
		this.gc.drawImage(this.image, this.x, this.y);
	}
	
	// gets the x, y, width and height of image to set collision with other images
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}

	// Checks for collision of monkey with banana, coconut and rottenBanana
	// Returns true if collided
	public boolean collisionBanana(Banana banana) {
		boolean collide = this.getBoundary().intersects(banana.getBoundary());
		return collide;
	}

	public boolean collisionCoconut(Coconut coconut) {
		boolean collide = this.getBoundary().intersects(coconut.getBoundary());
		return collide;
	}

	public boolean collisionRotten(RottenBanana rottenBanana) {
		boolean collide = this.getBoundary().intersects(rottenBanana.getBoundary());
		return collide;
	}

}
