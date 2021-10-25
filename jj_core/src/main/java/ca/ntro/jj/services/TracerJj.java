package ca.ntro.jj.services;

import ca.ntro.jj.initialization.DependencyRegistrar;
import ca.ntro.jj.initialization.InitializedObject;
import ca.ntro.jj.values.ObjectMap;

public class TracerJj implements Tracer, InitializedObject {
	
	private Logger logger = new LoggerNull();
	private StackAnalyzer stackAnalyzer = new StackAnalyzerNull();

	@Override
	public void registerDependencies(DependencyRegistrar registrar) {
		registrar.addDependency(Logger.classId());
		registrar.addDependency(StackAnalyzer.classId());
	}

	@Override
	public void initialize(ObjectMap resolvedDependencies) {
		logger = resolvedDependencies.getSingleton(Logger.classId());
		stackAnalyzer = resolvedDependencies.getSingleton(StackAnalyzer.classId());
	}

	@Override
	public void trace(Object calledClassOrObject, Object[] arguments) {

		// TODO: actual stacktrace analysis
		stackAnalyzer.analyzeCall(calledClassOrObject);

		logger.trace("TODO");
	}
}
