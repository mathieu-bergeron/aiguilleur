package ca.ntro.ntro_services;

import ca.ntro.core.models.NtroModel;

public interface ModelObserver {
	
	void onModelUpdate(NtroModel updatedModel);

}
