package ca.ntro.app;

import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.static_imports.NtroJdk;
import javafx.application.Application;

public interface NtroAppFx extends App<ViewRegistrarFx> {
	
	@SuppressWarnings("unchecked")
	public static void launch(String[] args) {

		try {

			NtroJdk.initializer().executeBlocking();

		} catch (Throwable e) {

			throw new RuntimeException(e);
		}
		
		AppWrapperFx.appClass = (Class<? extends NtroAppFx>) Ntro.stackAnalyzer().callerClass();

		Application.launch(AppWrapperFx.class, args);
	}
}
