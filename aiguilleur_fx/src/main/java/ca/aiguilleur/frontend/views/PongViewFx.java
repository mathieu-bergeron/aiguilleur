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
	
	private RealTimePongModel realTimeModel;
	private AnimationTimer timer;
	
	@FXML
	Canvas canvas;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		realTimeModel = new RealTimePongModel(canvas.getGraphicsContext2D());
		
		
		
	}

	@Override
	public void displayModelUpdates(ModelUpdates updates) {

		realTimeModel.applyModelUpdates(updates);
		
		
	}


}
