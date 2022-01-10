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
	
	private QueueViewFx queueViewFx;
	private GameViewFx gameViewFx;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.asserter().assertNotNull("pageContainer", pageContainer);
	}


	@Override
	public void installQueueView(QueueView queueView) {
		queueViewFx = (QueueViewFx) queueView;
		
	}

	@Override
	public void installGameView(GameView gameView) {
		gameViewFx = (GameViewFx) gameView;
	}


	@Override
	public void showQueueView() {
		System.out.println("showQueueView");
		pageContainer.getChildren().clear();
		pageContainer.getChildren().add(queueViewFx.rootNode());
	}


	@Override
	public void showGameView() {
		System.out.println("showGameView");
		pageContainer.getChildren().clear();
		pageContainer.getChildren().add(gameViewFx.rootNode());
	}
}

