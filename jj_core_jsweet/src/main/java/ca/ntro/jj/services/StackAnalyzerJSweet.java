package ca.ntro.jj.services;

import ca.ntro.jj.initialization.ServiceRequester;
import ca.ntro.jj.initialization.ServiceDependant;
import ca.ntro.jj.initialization.SubTaskRegistrar;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.initialization.SubTaskDependant;
import ca.ntro.jj.values.ObjectMap;
import ca.ntro.jj.values.ServiceMap;
import ca.ntro.jj.values.Url;

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
		 * TODO:
		 * 
		 * add a FileFetcher
		 * 
		 */
		
		MultipleFutures loadMapFiles = MultipleFuturesJj();
		
		loadMapFiles.addFuture(fileFetcher.openRemoteTextFile(new Url()));
		
		
		registrar.addSubTask(taskId, loadMapFiles);
	}

	@Override
	public void handleSubTaskResults(ObjectMap subTaskResults) {
		
		BinaryFile binaryFile = subTaskResults.getObject("mapFileFetcherId");

	}
	

	@Override
	public void analyzeCall(Object calledClassOrObject) {
		
	}
}
