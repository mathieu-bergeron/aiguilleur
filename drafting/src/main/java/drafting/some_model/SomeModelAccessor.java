package drafting.some_model;

public class SomeModelAccessor extends SomeModel {
	
	private SomeModel model;
	
	public SomeModelAccessor(SomeModel model) {
		this.model = model;
	}

	// XXX: getter must be public ONLY in the accessor
	public int getSomeAttribute() {
		return model.getSomeAttribute();
	}
}
