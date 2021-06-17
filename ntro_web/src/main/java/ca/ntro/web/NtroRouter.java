package ca.ntro.web;

import java.util.Map;

import ca.ntro.core.mvc.NtroContext;
import ca.ntro.jj.Path;
import ca.ntro.users.NtroSessionData;
import ca.ntro.users.NtroUser;

public interface NtroRouter<U extends NtroUser, S extends NtroSessionData> {
	
	void route(Path path, Map<String,String[]> parameters, NtroContext<U,S> context);

}
