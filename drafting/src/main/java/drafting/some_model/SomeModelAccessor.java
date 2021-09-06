package drafting.some_model;

public class SomeModelAccessor extends SomeModel {

	// XXX: getter must be public ONLY in the accessor
	@Override
	public int getSomeAttribute() {
		return super.getSomeAttribute();
	}
}
