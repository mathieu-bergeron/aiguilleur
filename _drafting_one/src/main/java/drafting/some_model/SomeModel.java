package drafting.some_model;

import models.Accessor;
import models.Model;
import models.ModelRegistrar;
import models.Mutator;

public class SomeModel implements Model {
	
	static {

		// FIXME: the registration process is in fact 
		//        done in a registerModel method in the initialization code
		//        in can be partially done in code generation
		ModelRegistrar.registerModel(SomeModel.class, SomeModelAccessor.class, SomeModelAccessor.class);
		
		// XXX: the accessor is created by the ModelStore 
		//      for readModel and extractFromModel 
		//
		// NOTE: the modelStore creates a copy of anything that
		//       is read from the Model (using JsonIO)
		
		// XXX: the mutator is created by the ModelStore
		//      for createModel and updateModel 
	}

	// XXX: attributes must be private
	private int someAttribute = 10;
	
	// XXX: getters must be protected here
	// XXX: @Accessor annotation helps with code generation
	@Accessor
	protected int getSomeAttribute() {
		return someAttribute;
	}

	// XXX: setters must be protected here
	// XXX: @Mutator annotation helps with code generation
	@Mutator
	protected void setSomeAttribute(int someAttribute) {
		this.someAttribute = someAttribute;
	}

	// XXX: some mutators are not SETTERS
	//      but rather part of the logic of the model
	@Mutator
	protected void updateNumberOfBlah(int increment) {
		this.someAttribute += increment;
	}
}
