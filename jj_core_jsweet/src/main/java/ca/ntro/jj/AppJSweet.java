package ca.ntro.jj;

import ca.ntro.jj.app.App;
import ca.ntro.jj.app.AppJj;
import ca.ntro.jj.app.Options;
import ca.ntro.jj.common.identifyiers.ClassIdJj;
import ca.ntro.jj.common.values.ObjectMap;
import ca.ntro.jj.initialization.InitializationTask;
import ca.ntro.jj.initialization.InitializorJj;
import ca.ntro.jj.services.logger.Logger;
import ca.ntro.jj.services.tracer.Tracer;
import ca.ntro.jj.wrappers.ExceptionHandler;
import ca.ntro.jj.wrappers.future.ResultHandler;

public class AppJSweet extends AppJj<AppJSweet> implements App<AppJSweet> {


	@Override
	protected InitializationTask provideTracerInitializationTask(Options options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InitializationTask provideLoggerInitializationTask(Options options) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
