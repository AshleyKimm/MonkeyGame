/*
 * AshleyMonkeyGame - Score.java
 * Purpose: to display texts on monkey game canvas
 * Author: Ashley Kim
 * Date: October 28, 2020
 * Course: ICS4U1
 */
package monkeygame;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Score {

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	public Score(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
	}

	public void display(Monkey monkey) {
		// Displays the score (number of bananas eaten - rotten bananas eaten)
		String scoreString = "Score: " + Banana.bananasEaten;
		gc.setFont(Font.font("ComicSansMS", FontWeight.BOLD, 36));
		gc.setFill(Color.RED);
		gc.fillText(scoreString, 20, 50);

		// Displays the monkey's lives (if collided with coconut, lives decrease)
		String livesString = "Lives: " + monkey.getLives();
		gc.setFill(Color.RED);
		gc.fillText(livesString, 200, 50);

		// Displays the number of power ups
		String powerUpString = "Power ups: " + MonkeyGameController.powerUps;
		gc.setFill(Color.BLUE);
		gc.fillText(powerUpString, 500, 50);
	}

	// Displays the ending text when game ends (when lives = 0)
	public void displayEnding() {
		String gameOver = "GAME OVER";
		gc.setFont(Font.font("ComicSansMS", FontWeight.BOLD, 100));
		gc.setFill(Color.RED);
		gc.fillText(gameOver, 200, 200);

	}
}
