package drafting.some_model;

import models.ModelWrapper;

public class SomeModelMutator extends ModelWrapper<SomeModel> {

	public SomeModelMutator(SomeModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	// XXX: setter must be public ONLY in mutators
	public void setSomeAttribute(int someAttribute) {
		model().setSomeAttribute(someAttribute);
	}

	// XXX: mutator methods should be generated
	public void updateNumberOfBlah(int increment) {
		model().updateNumberOfBlah(increment);
	}
}
