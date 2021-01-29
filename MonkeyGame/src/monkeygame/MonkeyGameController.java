/*
 * AshleyMonkeyGame - MonkeyGameController.java
 * Purpose: to call methods and variables from other classes to 
 * run the monkey game
 * Author: Ashley Kim
 * Date: October 28, 2020
 * Course: ICS4U1
 */

package monkeygame;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MonkeyGameController {

	@FXML
	Canvas gameCanvas;

	GraphicsContext gc;

	Scene gameScene;

	boolean collidedBanana = false;
	boolean collidedCoconut = false;
	boolean collidedRotten = false;
	static int powerUps = 0;

	public void getScene(Stage primaryStage) {
		gameScene = primaryStage.getScene();
	}

	// Runs the actual game
	public void gameLoop() {

		gc = gameCanvas.getGraphicsContext2D();

		// Sets the background image to the canvas
		Image background = new Image("images/background.png");

		// Creates an arrayList that holds the coconuts
		ArrayList<Coconut> coconutList = new ArrayList<Coconut>();

		Banana banana = new Banana(gc, gameCanvas);
		RottenBanana rottenBanana = new RottenBanana(gc, gameCanvas);

		// Create coconuts by the starting number
		for (int i = 0; i < Coconut.numCoconuts; i++) {
			coconutList.add(new Coconut(gc, gameCanvas));
		}

		// An arrayList of key inputs
		ArrayList<String> input = new ArrayList<String>();

		// Adds the key input to the ArrayList input when the key is pressed
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				// Converts the keyCode to string, and adds it to the ArrayList
				String code = e.getCode().toString();
				if (!input.contains(code))
					input.add(code);
			}
		});

		// Removes the key input from the ArrayList input when the key is released
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				// Converts the keyCode to string, and removes it from the ArrayList
				String code = e.getCode().toString();
				if (input.contains(code))
					input.remove(code);
			}
		});
		
		Monkey monkey = new Monkey(gc, gameCanvas, input);
		Score score = new Score(gc, gameCanvas);

		new AnimationTimer() {
			// actual game loop that repeats
			@Override
			public void handle(long currentNanoTime) {

				gc.clearRect(gameCanvas.getWidth(), gameCanvas.getHeight(), 0, 0);
				gc.drawImage(background, 0, 0);

				// Displays banana and rottenBanana images to the canvas
				banana.drawBanana();
				rottenBanana.drawBanana();
				
				monkey.move();

				// Move the individual coconuts from the coconutList
				// apply the move method separately, individually
				for (int i = 0; i < Coconut.numCoconuts; i++) {
					coconutList.get(i).move();
				}

				collidedBanana = monkey.collisionBanana(banana);
				// When monkey eats banana, score increases and both banana
				// and rottenBanana are randomly placed in canvas
				if (collidedBanana) {
					Banana.bananasEaten += 1;
					banana.randomBanana();
					rottenBanana.randomBanana();

					// Adds new coconut after every three bananas are eaten 
					if (Banana.bananasEaten % 3 == 0) {
						Coconut.numCoconuts++;
						coconutList.add(new Coconut(gc, gameCanvas));
					}

					// Increases monkey's speed after every two bananas are eaten
					if (Banana.bananasEaten % 2 == 0 && Banana.bananasEaten != 0 && !collidedRotten) {
						Monkey.speed++;
						powerUps++;
					}
				}
				// When monkey eats rottenBanana, the score decreases and
				// rottenBanana is randomly placed
				collidedRotten = monkey.collisionRotten(rottenBanana);
				if (collidedRotten) {
					Banana.bananasEaten -= 1;
					rottenBanana.randomBanana();
				}

				// Checks for collision with monkey for every coconut
				for (int i = 0; i < Coconut.numCoconuts; i++) {
					// Temporary variable
					Coconut c = coconutList.get(i);
					collidedCoconut = monkey.collisionCoconut(c);
					// If collided, monkey's lives decrease and monkey is randomly placed
					if (collidedCoconut) {
						monkey.lives -= 1;
						monkey.randomXY();
					}
				}
				// displays the score strings 
				score.display(monkey);

				// When monkey dies, stops the coconuts and the monkey from moving
				if (monkey.getLives() <= 0) {
					// displays the Game Over string
					score.displayEnding();
					Coconut.speed = 0;
					Monkey.speed = 0;
					// Stops every coconut in the ArrayList
					for (int i = 0; i < Coconut.numCoconuts; i++) {
						coconutList.get(i).setDx(Coconut.speed);
						coconutList.get(i).setDy(Coconut.speed);
					}
				}
			}
		}.start();

	}

}
