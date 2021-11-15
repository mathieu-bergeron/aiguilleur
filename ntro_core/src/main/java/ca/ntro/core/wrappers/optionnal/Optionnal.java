package ca.ntro.core.wrappers.optionnal;

public interface Optionnal<V extends Object> {
	
	boolean hasValue();
	V value();
	
	public static <V extends Object> Optionnal<V> fromValue(V value) {
		return new OptionnalNtro<V>(value);
	}
	static <V extends Object> Optionnal<V> none(Class<V> _class) {
		return new OptionnalNtro<V>();
	}
}
