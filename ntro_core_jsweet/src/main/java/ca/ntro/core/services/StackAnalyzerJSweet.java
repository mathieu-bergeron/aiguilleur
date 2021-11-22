package ca.ntro.core.services;

import ca.ntro.core.initialization.ServiceRequester;
import ca.ntro.core.initialization.ServiceDependant;
import ca.ntro.core.initialization.SubTaskRegistrar;
import ca.ntro.core.initialization.SubTaskDependant;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.values.ServiceMap;

public class StackAnalyzerJSweet extends StackAnalyzer implements ServiceDependant, SubTaskDependant {
	
	private FileFetcher fileFetcher = new FileFetcherNull();
	
	@Override
	public void requestServices(ServiceRequester registrar) {
		registrar.requestService(fileFetcher.serviceId());
	}

	@Override
	public void handleServices(ServiceMap services) {
		fileFetcher = services.getService(fileFetcher.serviceId());
	}

	@Override
	public void registerSubTasks(SubTaskRegistrar registrar) {
		/*
		
		MultipleFutures loadMapFiles = MultipleFuturesJj();
		
		loadMapFiles.addFuture(fileFetcher.openRemoteTextFile(new Url()));
		
		
		registrar.addSubTask(taskId, loadMapFiles);
		*/
	}

	@Override
	public void handleSubTaskResults(ObjectMap subTaskResults) {
		
		/*
		
		BinaryFile binaryFile = subTaskResults.getObject("mapFileFetcherId");
		
		*/

	}
	

	@Override
	public void analyzeCall(Object calledClassOrObject) {
		
	}

	@Override
	public Class<?> callerClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
