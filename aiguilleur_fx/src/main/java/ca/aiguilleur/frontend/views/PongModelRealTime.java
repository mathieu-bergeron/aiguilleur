package ca.aiguilleur.frontend.views;

import ca.aiguilleur.frontend.pong.PongModel;
import ca.ntro.app.models.ModelUpdates;
import javafx.scene.canvas.GraphicsContext;

public class PongModelRealTime extends PongModel {

	private GraphicsContext gc;

	public PongModelRealTime(GraphicsContext gc) {
		super();

		this.gc = gc;
	}

	public synchronized void applyModelUpdates(ModelUpdates updates) {
		
	}

	public synchronized void redraw() {
		
	}

	public synchronized void timePasses(double elapsedTimeSeconds) {
		
	}
}
