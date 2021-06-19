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

package ca.ntro.jdk.services;

import ca.ntro.jj.services.JsonService;
import ca.ntro.jj.services.Logger;
import ca.ntro.ntro_services.Ntro;

public class LoggerJdk extends Logger {

	@Override
	public void value(Object value) {
		trace(this);
		
		StringBuilder builder = new StringBuilder();
		
		Ntro.valueFormatter().format(builder, value);
		
		System.out.println(builder.toString());
	}

	@Override
	public void trace(Object calledObjectOrClass) {
		
	}
}
