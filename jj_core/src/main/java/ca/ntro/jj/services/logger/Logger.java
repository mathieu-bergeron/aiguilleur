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

import ca.ntro.core.tasks.NtroTask;
import ca.ntro.core.tasks.NtroTaskAsync;
import ca.ntro.jj.services.service_factory.ServiceFactory;
import ca.ntro.jj.services.tracer.Tracer;
import ca.ntro.jj.tasks.TaskJj;
import ca.ntro.jj.tasks.base.AtomicTask;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.wrappers.result.ExceptionHandler;

public interface Logger {


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
		
		Task initializationTaskTracer = factory.initializationTaskFor(Tracer.class);
		
		Task initializationTaskLogger = new TaskJj();
		
		initializationTaskLogger.addPreviousTask(initializationTaskTracer);

		return initializationTaskLogger;
	}
}
