package com.birknordbo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	public static JFrame frame = new JFrame("Mini tennis");
	
	static String[] array = {};
	
	public static int hit = 0;
	public static boolean gameover = false;

	Ball ball = new Ball(this);
	Line line = new Line(this);

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				line.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				line.keyPressed(e);
			}
		});
		setFocusable(true);
	}

	private void move() {
		ball.move();
		line.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		line.paint(g2d);
		
		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(hit), 10, 30);
	}

	public void gameOver() {
		if (gameover == false) {
			gameover = true;
			Sound.GAMEOVER.play();
			JOptionPane.showMessageDialog(this, "Your score is: " + hit,
					"Game Over", JOptionPane.YES_NO_OPTION);
			restart();
			
			
		}
	}
	
	public void restart() {
		Ball.x = 0;
		Ball.y = 0;
		Line.x = 120;
		hit = 0;
		Ball.speed = 1;
		gameover = false;
	}

	public static void main(String[] args) throws InterruptedException {
		
		Game game = new Game();
		frame.add(game);
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
			
			if (hit >= 5 && hit < 20) {
				Ball.speed = 2;
			}
			if (hit >= 20 && hit < 40) {
				Ball.speed = 3;
			}
			if (hit >= 40 && hit < 70) {
				Ball.speed = 4;
			}
			if (hit >= 70 && hit < 100) {
				Ball.speed = 5;
			}
		}
	}
}