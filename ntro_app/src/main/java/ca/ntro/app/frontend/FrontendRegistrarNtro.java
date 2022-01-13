package ca.ntro.app.frontend;

import ca.ntro.app.services.Window;
import ca.ntro.app.services.WindowNull;

public abstract class FrontendRegistrarNtro<VR extends ViewRegistrar> 

       implements     FrontendRegistrar<VR>,
                      FrontendExecutor {

	
	private EventRegistrarNtro eventRegistrar = new EventRegistrarNtro();
	private FrontendTaskCreatorNtro taskCreator = new FrontendTaskCreatorNtro();
	private VR viewRegistrar = newViewRegistrarInstance();
	private Window window = new WindowNull();
	private Frontend<VR> frontend;
	
	protected abstract VR newViewRegistrarInstance();

	public EventRegistrarNtro getEventRegistrar() {
		return eventRegistrar;
	}

	public void setEventRegistrar(EventRegistrarNtro eventRegistrar) {
		this.eventRegistrar = eventRegistrar;
	}

	public FrontendTaskCreatorNtro getTaskCreator() {
		return taskCreator;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public void setTaskCreator(FrontendTaskCreatorNtro taskCreator) {
		this.taskCreator = taskCreator;
	}

	public VR getViewRegistrar() {
		return viewRegistrar;
	}

	public void setViewRegistrar(VR viewRegistrar) {
		this.viewRegistrar = viewRegistrar;
	}
	
	
	
	




	@Override
	public void registerFrontend(Frontend<VR> frontend) {
		frontend.registerEvents(getEventRegistrar());
		frontend.registerViews(getViewRegistrar());

		getTaskCreator().addWindowTask(getWindow());
		getViewRegistrar().addViewLoaderTasks(getTaskCreator());

		frontend.createTasks(getTaskCreator());
		
		this.frontend = frontend;
	}

	@Override
	public void executeFrontendTasks() {
		getTaskCreator().executeTasks();
		
		frontend.execute();
	}

}
