package ca.ntro.core.mvc;

import source.T;

public class Handler<C extends NtroAbstractController> {

	private C controller;

	void setController(C controller) {
		T.call(this);
		
		this.controller = (C) controller;
	}
	
	protected C getController() {
		T.call(this);

		return controller;
	}

}
