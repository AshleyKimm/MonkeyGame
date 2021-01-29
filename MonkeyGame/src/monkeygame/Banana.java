/*
 * AshleyMonkeyGame - Banana.java
 * Purpose: sets the banana's image and its position
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

public class Banana {

	// A static int variable to keep the score that can be accessed
	// in other classes
	static int bananasEaten = 0;
	double x;
	double y;

	String imageName = "images/banana.png";
	Image image = new Image(imageName);

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	public Banana(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomBanana();
	}

	public Banana(String imageName, GraphicsContext gc, Canvas gameCanvas) {
		this.imageName = imageName;
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomBanana();
	}

	// Sets the random position of banana in the game canvas
	public void randomBanana() {
		this.x = (int) (Math.random() * (this.gameCanvas.getWidth() - this.image.getWidth()));
		this.y = (int) (Math.random() * (this.gameCanvas.getHeight() - this.image.getHeight()));
	}

	// Displays the banana image at corresponding position
	public void drawBanana() {
		gc.drawImage(this.image, this.x, this.y);
	}

	public static int getBananasEaten() {
		return bananasEaten;
	}

	public static void setBananasEaten(int bananasEaten) {
		Banana.bananasEaten = bananasEaten;
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	// gets the x, y, width and height of image to set collision with other images
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}

}
