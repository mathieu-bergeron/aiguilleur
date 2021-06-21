// Copyright (C) (2020) (Mathieu Bergeron) (mathieu.bergeron@cmontmorency.qc.ca)
//
// This file is part of tutoriels4f5
//
// tutoriels4f5 is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// tutoriels4f5 is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with aquiletour.  If not, see <https://www.gnu.org/licenses/>

package ca.ntro.jj.services.logger;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.jj.services.Service;
import ca.ntro.jj.services.service_factory.ServiceDescriptor;
import ca.ntro.jj.services.service_factory.ServiceDescriptorJj;
import ca.ntro.jj.services.service_factory.ServiceFactory;
import ca.ntro.jj.services.tracer.Tracer;
import ca.ntro.jj.tasks.base.Task;

public interface Logger extends Service {

	static List<Class<? extends Service>> dependencies(){

		List<Class<? extends Service>> dependencies = new ArrayList<>();

		dependencies.add(Tracer.class);
		
		return dependencies;
	}


	/*
	public abstract void info(String... messages);
	public abstract void debug(String... messages);
	public abstract void warning(String... messages);
	public abstract void error(String... messages);
	*/

	void text(String text);
	
	void value(Object value);

	void trace(Object calledObjectOrClass);


	static Task initializationTask(ServiceFactory factory, 
			                       Class<? extends Logger> implementationClass) {

		ServiceDescriptor<Logger> serviceDescriptor = new ServiceDescriptorJj<Logger>(Logger.class, implementationClass);

		factory.addInitializationTask(serviceDescriptor, dependencies());

		return factory.initializationTaskFor(Logger.class);
	}
}
