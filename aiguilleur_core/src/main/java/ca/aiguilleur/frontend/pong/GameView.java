package ca.aiguilleur.frontend.pong;

import ca.aiguilleur.frontend.events.EvtShowQueueView;
import ca.ntro.app.frontend.ViewModel;

public interface GameView<VN extends Object> extends ViewModel<VN> {

	void installShowQueueViewEvent(EvtShowQueueView event);

}
