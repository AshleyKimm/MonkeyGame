/*
 * AshleyMonkeyGame - Coconut.java
 * Purpose: sets the coconuts' image, speed and positions
 * Author: Ashley Kim
 * Date: October 28, 2020
 * Course: ICS4U1
 */

package monkeygame;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Coconut {

	// Sets the variables that can be accessed and changed in other classes
	static int numCoconuts = 1;
	static int speed = 3;
	
	double x = 200;
	double y = 200;
	double dx = speed;
	double dy = -speed;
	
	String imageName = "images/coconut.png";
	Image image = new Image(imageName);

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;
	
	int[] speedList = { -4, -3, -2, -1, 1, 2, 3, 4 };


	public Coconut(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomStart();
	}

	public Coconut(String imageName, GraphicsContext gc, Canvas gameCanvas) {
		this.imageName = imageName;
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomStart();
	}

	// Makes the coconuts appear in random position (the starting position)
	public void randomStart() {
		// Chooses random speed for x value
		int rnd = (int) (Math.random() * speedList.length);
		this.dx = speedList[rnd];
		
		// Chooses random speed for y value
		rnd = (int) (Math.random() * speedList.length);
		this.dy = speedList[rnd];
		
		// Generates random x and y value within the size of gameCanvas
		this.x = (int) (Math.random() * (this.gameCanvas.getWidth() - this.image.getWidth()));
		this.y = (int) (Math.random() * (this.gameCanvas.getHeight() - this.image.getHeight()));
	}

	public static int getNumCoconuts() {
		return numCoconuts;
	}

	public static void setNumCoconuts(int numCoconuts) {
		Coconut.numCoconuts = numCoconuts;
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void move() {

		// Add dx value to the current x position
		this.x += this.dx;
		// Add dy value to the current y position
		this.y += this.dy;
		
		// If this.x or this.y values are smaller or bigger than the width or height
		// of the image, then they are 'bounced' off the walls.
		if (this.x <= 0 || this.x >= this.gameCanvas.getWidth() - this.image.getWidth()) {
			this.dx = -this.dx;
		}
		if (this.y <= 0 || this.y >= this.gameCanvas.getHeight() - this.image.getHeight()) {
			this.dy = -this.dy;
		}
		// displays the image in corresponding position
		this.gc.drawImage(this.image, this.x, this.y);

	}
	
	// gets the x, y, width and height of image to set collision with other images
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}

}
