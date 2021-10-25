package ca.ntro.jj.services;

import ca.ntro.jj.initialization.ServiceRequester;
import ca.ntro.jj.initialization.ServiceDependant;
import ca.ntro.jj.initialization.SubTaskRegistrar;
import ca.ntro.jj.initialization.SubTaskDependant;
import ca.ntro.jj.values.ObjectMap;
import ca.ntro.jj.values.ServiceMap;

public class StackAnalyzerJSweet extends StackAnalyzer implements ServiceDependant, SubTaskDependant {
	
	@Override
	public void requestServices(ServiceRequester registrar) {
	}

	@Override
	public void handleServices(ServiceMap services) {
	}

	@Override
	public void registerSubTasks(SubTaskRegistrar registrar) {
	}

	@Override
	public void handleSubTaskResults(ObjectMap subTaskResults) {
	}
	

	@Override
	public void analyzeCall(Object calledClassOrObject) {
		
	}
}
