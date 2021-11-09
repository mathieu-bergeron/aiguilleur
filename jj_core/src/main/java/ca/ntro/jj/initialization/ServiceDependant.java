package ca.ntro.jj.initialization;

import ca.ntro.jj.values.ServiceMap;

public interface ServiceDependant {

	void requestServices(ServiceRequester serviceRequester);  
	void handleServices(ServiceMap services);

}
