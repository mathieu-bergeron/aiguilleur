package ca.ntro.app.frontend;

public abstract class FrontendRegistrarNtro<VR extends ViewRegistrar<?>> 

       implements     FrontendRegistrar<VR>,
                      FrontendExecutor {

	
	private EventRegistrarNtro eventRegistrar = new EventRegistrarNtro();
	private FrontendTaskCreatorNtro taskCreator = new FrontendTaskCreatorNtro();
	private VR viewRegistrar = newViewRegistrarInstance();
	private Window window = new WindowNull();
	
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
	
	
	
	
	
	public FrontendRegistrarNtro() {
	}

	public FrontendRegistrarNtro(Window window) {
		setWindow(window);
	}






	@Override
	public void registerFrontend(Frontend<VR> frontend) {
		frontend.registerEvents(getEventRegistrar());
		frontend.registerViews(getViewRegistrar());
		frontend.createTasks(getTaskCreator());
	}

	@Override
	public void executeFrontendTasks() {
		
		getViewRegistrar().addViewLoaderTasks(getTaskCreator());
		getTaskCreator().addWindowTask(getWindow());

		getTaskCreator().executeTasks();
	}

}
