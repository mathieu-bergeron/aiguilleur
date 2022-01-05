package ca.ntro.app.frontend;

import ca.ntro.app.events.EventRegistrar;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;

public interface Frontend<VR extends ViewRegistrar<?>> {

	void registerEvents(EventRegistrar registrar);

	void registerViews(VR registrar);

	void createTasks(FrontendTasks inOrder);

}
