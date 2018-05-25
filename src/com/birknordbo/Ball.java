package com.birknordbo;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30;
	public static int speed = 1;
	static int x = 0;
	static int y = 0;
	int xa = speed;
	int ya = speed;
	private Game game;

	public Ball(Game game) {
		this.game = game;
	}

	void move() {
		if (Game.gameover == false) {
			if (x + xa < 0) {
				xa = speed;
				Sound.WALLHIT.play();
			}
			if (x + xa > game.getWidth() - DIAMETER) {
				xa = -speed;
				Sound.WALLHIT.play();
			}
			if (y + ya < 0) {
				ya = speed;
				Sound.WALLHIT.play();
			}
			if (y + ya > game.getHeight() - DIAMETER)
				game.gameOver();
			if (collision()) {
				ya = -speed;
				y = game.line.getTopY() - DIAMETER;
				Game.hit = Game.hit + 1;
				Sound.HIT.play();
			}
			x = x + xa;
			y = y + ya;
		}
	}

	private boolean collision() {
		return game.line.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

}