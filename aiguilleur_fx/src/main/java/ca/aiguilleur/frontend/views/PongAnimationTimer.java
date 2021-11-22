package ca.aiguilleur.frontend.views;

import javafx.animation.AnimationTimer;

public class PongAnimationTimer extends AnimationTimer {
	
	private PongModelRealTime model;
	private long previousNowNanoSeconds;
	
	public PongAnimationTimer(PongModelRealTime model) {
		super();
		
		this.model = model;
		
		this.previousNowNanoSeconds = System.nanoTime();
	}

	@Override
	public void handle(long nowNanoSeconds) {
		
		double elapsedTimeSeconds = (nowNanoSeconds - previousNowNanoSeconds) / 1E9;

		model.timePasses(elapsedTimeSeconds);
		model.redraw();
	}

}
