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

package ca.ntro.services;

import ca.ntro.core.tasks.NtroTaskSync;
import ca.ntro.jj.services.AssertService;
import ca.ntro.jj.services.CalendarService;
import ca.ntro.jj.services.JsonService;
import ca.ntro.jj.services.SystemService;
import ca.ntro.jj.services.ThreadService;
import ca.ntro.jj.services.ValueFormatter;
import ca.ntro.jj.services.__Ntro;
import ca.ntro.jj.stack.StackAnalyzer;
import ca.ntro.jj.trace.T;
import ca.ntro.jj.trace.__T;

public abstract class InitializationTask extends NtroTaskSync {

	@Override
	protected void runTask() {
		__T.call(InitializationTask.class, "runSyncTask");
		performInitialization();
	}

	@Override
	public void onFailure(Exception e) {
	}

	private void performInitialization() {

		StackAnalyzer stackAnalyzer = provideStackAnalyzer();
		T.__registerStackAnalyzer(stackAnalyzer);
		__Ntro.registerStackAnalyzer(stackAnalyzer);

		Ntro.registerResourceLoader(provideResourceLoader());
		
		//Ntro.registerViewLoaderWebClass(provideViewLoaderWebClass());
		
		Ntro.registerThreadService(provideThreadService());
		Ntro.registerMessageServiceClass(provideMessageServiceClass());
		Ntro.registerBackendServiceClass(provideBackendServiceClass());

		Ntro.registerAssertService(provideAssertService());
		Ntro.registerJsonService(provideJsonService());

		Ntro.registerSessionServiceClass(provideSessionServiceClass());
		
		Ntro.registerModelStore(provideModelStore());
		
		Ntro.registerValueFormatter(provideValueFormatter());
		
		Ntro.registerCalendarService(provideCalendarService());
		
		Ntro.registerRouterService(provideRouterService());
		
		Ntro.registerSystemService(provideSystemService());
	}


	protected abstract StackAnalyzer provideStackAnalyzer();
	protected abstract ResourceLoader provideResourceLoader();
	//protected abstract Class<? extends ViewLoaderWeb> provideViewLoaderWebClass();
	protected abstract ThreadService provideThreadService();
	protected abstract Class<? extends MessageService> provideMessageServiceClass();
	protected abstract Class<? extends BackendService> provideBackendServiceClass();
	protected abstract AssertService provideAssertService();
	protected abstract JsonService provideJsonService();
	protected abstract Class<? extends SessionService> provideSessionServiceClass();
	protected abstract ModelStore provideModelStore();
	protected abstract ValueFormatter provideValueFormatter();
	protected abstract CalendarService provideCalendarService();
	protected abstract RouterService provideRouterService();
	protected abstract SystemService provideSystemService();

}
