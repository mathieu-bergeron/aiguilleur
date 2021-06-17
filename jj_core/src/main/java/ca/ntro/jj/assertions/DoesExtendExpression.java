package ca.ntro.jj.assertions;

import ca.ntro.jj.introspection.NtroClass;

public class DoesExtendExpression extends SimpleAssertExpression {
	
	private NtroClassExpression classSignatureExpression;
	private Class<?> _class;

	public DoesExtendExpression(NtroClassExpression classSignatureExpression, Class<?> _class) {
		this.classSignatureExpression = classSignatureExpression;
		this._class = _class;
	}

	@Override
	public String failMessage() {
		NtroClass classSignature = classSignatureExpression.evaluate();
		
		if(!classSignature.ifExtends(_class)) {

			return "doesExtend(" + Ntro.introspector().ntroClassFromJavaClass(_class).simpleName() + ")";
		}

		return null;
	}

}
