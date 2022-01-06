package ca.ntro.app.frontend;

public class      FrontendRegistrarFx 

       extends    FrontendRegistrarNtro<ViewRegistrarFx>

       implements FrontendRegistrar<ViewRegistrarFx> {


	@Override
	protected ViewRegistrarFx newViewRegistrarInstance() {
		return new ViewRegistrarFx();
	}

}
