/*
 * AshleyMonkeyGame - RottenBanana.java
 * Purpose: sets the rotten banana's image and its random position
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

public class RottenBanana {

	double x;
	double y;

	String imageName = "images/rottenbanana.png";
	Image image = new Image(imageName);

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	public RottenBanana(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomBanana();
	}

	public RottenBanana(String imageName, GraphicsContext gc, Canvas gameCanvas) {
		this.imageName = imageName;
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomBanana();
	}

	// Places the rotten banana image in random position
	public void randomBanana() {
		this.x = (int) (Math.random() * (this.gameCanvas.getWidth() - this.image.getWidth()));
		this.y = (int) (Math.random() * (this.gameCanvas.getHeight() - this.image.getHeight()));
	}

	// Method that displays the rotten banana image at corresponding position
	public void drawBanana() {
		gc.drawImage(this.image, this.x, this.y);
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
