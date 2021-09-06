package drafting.other_model;

import drafting.some_model.SomeModel;

public class OtherModel {
	
	protected int otherAttribute = 0;
	protected SomeModel someModel = new SomeModel();
	
	public OtherModel() {

		// XXX: by default, the accessor is only
		//      created by the ModelStore (for readModel!)
		//
		// XXX: by default, the ModelStore creates a copy
		//      of anything read from the model
		//
		// NOTE: the copy can be done via Json serialization/deserialization
		//
		// otherAttribute = someModel.accessor().getSomeAttribute();
	}
}
