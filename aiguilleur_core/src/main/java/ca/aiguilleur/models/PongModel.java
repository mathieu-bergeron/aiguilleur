package ca.aiguilleur.models;

import ca.aiguilleur.models.pong.Ball;
import ca.aiguilleur.models.pong.Paddle;
import ca.ntro.app.models.Model;

public class PongModel implements Model  {

	private Ball ball = new Ball();
	private Paddle paddleLeft = new Paddle();
	private Paddle paddleRight = new Paddle();

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public Paddle getPaddleLeft() {
		return paddleLeft;
	}

	public void setPaddleLeft(Paddle paddleLeft) {
		this.paddleLeft = paddleLeft;
	}

	public Paddle getPaddleRight() {
		return paddleRight;
	}

	public void setPaddleRight(Paddle paddleRight) {
		this.paddleRight = paddleRight;
	}
}
