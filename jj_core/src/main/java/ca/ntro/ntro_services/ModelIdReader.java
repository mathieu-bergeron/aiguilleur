package ca.ntro.ntro_services;

import ca.ntro.backend.BackendError;

public interface ModelIdReader  {
	
	void onModelId(String modelId) throws BackendError;

}
