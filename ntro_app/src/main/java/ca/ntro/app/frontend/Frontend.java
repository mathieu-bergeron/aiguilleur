package ca.ntro.app.frontend;

import ca.ntro.app.events.EventRegistrar;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;

public interface Frontend<VR extends ViewRegistrar<?>> {

	void registerEvents(EventRegistrar registrar);

	void registerViews(VR registrar);

	void createTasks(FrontendTaskCreator to);

}
