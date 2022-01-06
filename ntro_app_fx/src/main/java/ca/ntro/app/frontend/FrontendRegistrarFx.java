package ca.ntro.app.frontend;

import javafx.stage.Stage;

public class      FrontendRegistrarFx 

       extends    FrontendRegistrarNtro<ViewRegistrarFx>

       implements FrontendRegistrar<ViewRegistrarFx> {


	public FrontendRegistrarFx() {
		super();
	}

	public FrontendRegistrarFx(WindowFx window) {
		super(window);
	}

	@Override
	protected ViewRegistrarFx newViewRegistrarInstance() {
		return new ViewRegistrarFx();
	}

}
