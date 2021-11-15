package ca.ntro.core.wrappers.optionnal;

public class OptionnalNtro<V extends Object> implements Optionnal<V> {

	private boolean hasValue = false;
	private V value = null;
	
	public OptionnalNtro(V value) {
		registerValue(value);
	}
	
	public OptionnalNtro() {
	}

	public void registerValue(V value) {
		this.hasValue = true;
		this.value = value;
	}

	public void forgetValue() {
		this.hasValue = false;
		this.value = null;
	}

	@Override
	public boolean hasValue() {
		return hasValue;
	}

	@Override
	public V value() {
		return value;
	}
}
