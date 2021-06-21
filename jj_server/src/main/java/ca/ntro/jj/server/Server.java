package ca.ntro.jj.server;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.jj.services.Service;
import ca.ntro.jj.services.logger.Logger;

public interface Server {

	static List<Class<? extends Service>> dependencies(){

		List<Class<? extends Service>> dependencies = new ArrayList<>();

		dependencies.add(Logger.class);
		
		return dependencies;
	}

}
