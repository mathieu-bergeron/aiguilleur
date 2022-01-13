package ca.aiguilleur.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.aiguilleur.frontend.pong.GameView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.frontend.root.RootView;
import ca.ntro.app.views.ViewFx;
import javafx.scene.layout.Pane;

public class RootViewFx 

       extends ViewFx 
       
       implements RootView<Pane> {

	private QueueView<Pane> queueView;
	private GameView<Pane> gameView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void registerQueueView(QueueView<?> queueView) {
		this.queueView = (QueueView<Pane>) queueView;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void registerGameView(GameView<?> gameView) {
		this.gameView = (GameView<Pane>) gameView;
	}


	@Override
	public void showQueueView() {
		rootNode().getChildren().clear();
		rootNode().getChildren().add(queueView.rootNode());
	}


	@Override
	public void showGameView() {
		rootNode().getChildren().clear();
		rootNode().getChildren().add(gameView.rootNode());
	}


}

