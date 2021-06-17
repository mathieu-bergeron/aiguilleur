package ca.ntro.jj.assertions;

public abstract class ValueExpression<O extends Object> extends SimpleAssertExpression {
	
	public abstract O evaluate();

}
