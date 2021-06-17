package ca.ntro.jj.assertions;

public class IsNotEqualToExpression extends IsEqualToExpression {
	
	public IsNotEqualToExpression(ValueExpression valueExpression, Object otherValue) {
		super(valueExpression, otherValue);
	}
	
	@Override
	public boolean shouldFail() {
		return !super.shouldFail();
	}

}
