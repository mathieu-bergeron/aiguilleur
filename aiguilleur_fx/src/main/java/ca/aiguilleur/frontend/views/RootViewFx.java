package ca.aiguilleur.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.aiguilleur.frontend.pong.GameView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.frontend.root.RootView;
import ca.ntro.app.views.ViewFx;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class RootViewFx 

       extends ViewFx

       implements RootView, 
                  Initializable {

	@FXML
	private VBox pageContainer;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.asserter().assertNotNull("pageContainer", pageContainer);

	}


	@Override
	public void installQueueView(QueueView queueView) {
		QueueViewFx queueViewFx = (QueueViewFx) queueView;
		
		pageContainer.getChildren().clear();
		pageContainer.getChildren().add(queueViewFx.rootNode());
	}

	@Override
	public void installGameView(GameView gameView) {
		GameViewFx gameViewFx = (GameViewFx) gameView;
		
		pageContainer.getChildren().clear();
		pageContainer.getChildren().add(gameViewFx.rootNode());
		
	}
}

