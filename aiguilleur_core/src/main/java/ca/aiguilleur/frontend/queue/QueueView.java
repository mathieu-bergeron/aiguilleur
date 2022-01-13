package ca.aiguilleur.frontend.queue;

import ca.aiguilleur.frontend.events.EvtShowGameView;
import ca.ntro.app.frontend.ViewModel;

public interface QueueView<VN extends Object> extends ViewModel<VN> {
	
	void installShowGameViewEvent(EvtShowGameView event);


}
