package ca.ntro.jj.app;

import ca.ntro.jj.common.identifyiers.ClassIdJj;
import ca.ntro.jj.common.values.ObjectMap;
import ca.ntro.jj.initialization.InitializationTask;
import ca.ntro.jj.initialization.InitializorJj;
import ca.ntro.jj.services.logger.Logger;
import ca.ntro.jj.services.tracer.Tracer;
import ca.ntro.jj.wrappers.ExceptionHandler;
import ca.ntro.jj.wrappers.Handler;
import ca.ntro.jj.wrappers.future.Future;
import ca.ntro.jj.wrappers.future.ResultHandler;

public abstract class AppJj<A extends App<?>> implements App<A> {
	
	private Future<ObjectMap> services;
	private Options options = new OptionsJj();

	@SuppressWarnings("unchecked")
	@Override
	public A addOptions(Options options) {

		this.options = options;
		
		// XXX: options could be fetched in any InitializedObject (as a resolvedDependency)
		InitializorJj.getInstance().registerSingleton(Options.classId(), options);
		
		return (A) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public A initialize() {

		InitializorJj.getInstance().registerInitializationTask(new ClassIdJj<>(Tracer.class), provideTracerInitializationTask(options));
		InitializorJj.getInstance().registerInitializationTask(new ClassIdJj<>(Logger.class), provideLoggerInitializationTask(options));
		
		services = InitializorJj.getInstance().initializedObjects();
		
		return (A) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public A onAppInitialized(Handler handler) {
		
		services.handleResult(services -> {

			Log.initialize(services);
			
			handler.handle();
		});

		return (A) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public A onInitializationFailure(ExceptionHandler exceptionHandler) {
		
		services.handleException(exceptionHandler);

		return (A) this;
	}
	
	protected abstract InitializationTask provideTracerInitializationTask(Options options);
	protected abstract InitializationTask provideLoggerInitializationTask(Options options);


}
