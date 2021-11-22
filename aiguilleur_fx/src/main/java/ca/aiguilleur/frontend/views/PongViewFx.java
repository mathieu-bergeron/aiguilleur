package ca.aiguilleur.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.aiguilleur.frontend.pong.PongView;
import ca.ntro.app.models.ModelUpdates;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

public class PongViewFx implements PongView, Initializable {
	
	private PongModelRealTime realTimeModel;
	private AnimationTimer timer;
	
	@FXML
	Canvas canvas;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		realTimeModel = new PongModelRealTime(canvas.getGraphicsContext2D());
		timer = new PongAnimationTimer(realTimeModel);

		timer.start();
	}

	@Override
	public void displayModelUpdates(ModelUpdates updates) {

		realTimeModel.applyModelUpdates(updates);
	}


}
