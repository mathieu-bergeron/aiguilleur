package ca.ntro.jj.services;

import ca.ntro.jj.initialization.DependencyRegistrar;
import ca.ntro.jj.initialization.InitializedObject;
import ca.ntro.jj.values.ObjectMap;

public class StackAnalyzerJSweet implements StackAnalyzer, InitializedObject {
	
	/* TODO: 
	 * 
	 * The dependencies of StackAnalyzerJSWeet
	 * are in fact list of subTasks,
	 * where each subTask loads a .map file
	 * 
	 * registerDependencies() / onDependenciesResolved()   (corresponds to entryTask)
	 * registerSubTasks() / onSubTasksDone()               (corresponds to exitTask)
	 * 
	 */

	@Override
	public void registerDependencies(DependencyRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(ObjectMap resolvedDependencies) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void analyzeCall(Object calledClassOrObject) {
		
	}
}
