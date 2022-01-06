package ca.ntro.app.frontend;

public interface ViewRegistrar<V extends View> {

	void addViewLoaderTasks(FrontendTaskCreatorNtro taskCreator);

}
