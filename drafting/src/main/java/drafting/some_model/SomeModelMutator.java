package drafting.some_model;

public class SomeModelMutator extends SomeModel {

	// XXX: setter must be public ONLY in mutators
	public void setSomeAttribute(int someAttribute) {
		super.setSomeAttribute(someAttribute);
	}
}
