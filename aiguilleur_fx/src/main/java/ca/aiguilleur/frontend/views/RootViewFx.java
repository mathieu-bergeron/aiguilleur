package ca.aiguilleur.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.aiguilleur.frontend.pong.GameView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.frontend.root.RootView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class RootViewFx 

       extends ViewFx {

	@FXML
	private VBox rootNode;
	
	private QueueViewFx queueViewFx;
	private GameViewFx gameViewFx;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Ntro.asserter().assertNotNull("rootNode", rootNode);
	}

	@Override
	public VBox rootNode() {
		return rootNode;
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
		rootNode().getChildren().clear();
		rootNode().getChildren().add(queueViewFx.rootNode());
	}


	@Override
	public void showGameView() {
		rootNode.getChildren().clear();
		rootNode.getChildren().add(gameViewFx.rootNode());
	}


}

