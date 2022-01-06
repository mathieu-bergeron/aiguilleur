package ca.aiguilleur.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.messages.MsgDisplayPong;
import ca.aiguilleur.messages.MsgDisplayQueue;
import ca.ntro.app.views.ViewFx;
import javafx.fxml.Initializable;

public class MenuViewFx 

       extends ViewFx

       implements MenuView, Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@Override
	public void installDisplayQueueMessage(MsgDisplayQueue displayQueueMessage) {
		
	}

	@Override
	public void installDisplayPongMessage(MsgDisplayPong displayPongMessage) {
		
	}

}
