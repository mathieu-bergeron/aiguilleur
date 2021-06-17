package ca.ntro.jj.services;

import ca.ntro.core.models.NtroModel;

public interface ModelObserver {
	
	void onModelUpdate(NtroModel updatedModel);

}
