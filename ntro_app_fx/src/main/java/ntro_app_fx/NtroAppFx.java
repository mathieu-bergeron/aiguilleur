package ntro_app_fx;

import ca.ntro.app.App;
import ca.ntro.app.frontend.ViewRegistrarFx;

public interface NtroAppFx extends App<ViewRegistrarFx> {
	
	public static void launch(String[] args) {
		AppFx.launch(args);
	}

}
