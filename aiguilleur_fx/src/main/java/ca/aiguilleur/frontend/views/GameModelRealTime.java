package ca.aiguilleur.frontend.views;

import ca.aiguilleur.models.PongModel;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.path.ValuePath;
import javafx.scene.canvas.GraphicsContext;

public class GameModelRealTime extends PongModel {

	private GraphicsContext gc;

	public GameModelRealTime(GraphicsContext gc) {
		super();

		this.gc = gc;
	}

	public synchronized void applyModelUpdates(ModelUpdates updates) {
		updates.forEachUpdate((updateType, valueId, newValue) -> {
			
			ValuePath valuePath = valueId.getPath();

			if(valuePath.startsWith("ball")) {

				getBall().updateValue(valuePath.subPath(1), newValue);

			}else if(valuePath.startsWith("paddleLeft")) {

				getPaddleLeft().updateValue(valuePath.subPath(1), newValue);

			}else if(valuePath.startsWith("paddleRight")) {

				getPaddleRight().updateValue(valuePath.subPath(1), newValue);
			}
		});
	}

	public synchronized void redraw() {
		double width = gc.getCanvas().getWidth();
		double height = gc.getCanvas().getHeight();
		
		gc.clearRect(0, 0, width, height);
		
		drawBall(width, height);
		drawPaddleLeft(width, height);
		drawPaddleRight(width, height);
	}

	private void drawPaddleRight(double width, double height) {
		// TODO Auto-generated method stub
		
	}

	private void drawPaddleLeft(double width, double height) {
		// TODO Auto-generated method stub
		
	}

	private void drawBall(double width, double height) {
		// TODO Auto-generated method stub
		
	}

	public synchronized void timePasses(double elapsedTimeSeconds) {
		
	}
}
