package drafting.some_model;

import models.AccessorRegistrar;
import models.MutatorRegistrar;

public class SomeModel {
	
	static {

		// XXX: by default, the accessor is only
		//      created by the ModelStore (for readModel!)
		//
		// XXX: by default, the ModelStore creates a copy
		//      of anything read from the model
		//
		// NOTE: the copy can be done via Json serialization/deserialization
		//
		
		AccessorRegistrar.registerAccessorClass(SomeModel.class, SomeModelAccessor.class);
		MutatorRegistrar.registerMutatorClass(SomeModel.class, SomeModelAccessor.class);
	}

	// XXX: attributes must be private
	private int someAttribute = 10;
	
	// XXX: getters must be protected here
	protected int getSomeAttribute() {
		return someAttribute;
	}

	// XXX: setters must be protected here
	protected void setSomeAttribute(int someAttribute) {
		this.someAttribute = someAttribute;
	}
}
