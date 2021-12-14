package ca.ntro.core.reflection.mock.three_pointer_list;

public class MockLeftRightListElement<I extends Object> 

       implements LeftRightListElement<I> {
	
	private I value;
	private MockLeftRightListElement<I> next;

	public MockLeftRightListElement<I> getNext() {
		return next;
	}

	public void setNext(MockLeftRightListElement<I> next) {
		this.next = next;
	}
	

	public I getValue() {
		return value;
	}

	public void setValue(I value) {
		this.value = value;
	}
	
	
	
	
	public MockLeftRightListElement() {
	}

	public MockLeftRightListElement(I value) {
		setValue(value);
	}


	public void insertAfter(MockLeftRightListElement<I> newNext) {
		if(hasNext()) {

			MockLeftRightListElement<I> newNextNext = getNext();

			newNext.setNext(newNextNext);
			setNext(newNext);

		}else {

			setNext(newNext);
		}
		
	}

	public void removeAfter() {
		if(hasNext()
				&& next().hasNext()) {
			
			MockLeftRightListElement<I> newNext = getNext().getNext();
			setNext(newNext);

		}else {

			setNext(null);
		}
		
	}

	
	
	
	@Override
	public boolean hasNext() {
		return getNext() != null;
	}

	@Override
	public LeftRightListElement<I> next() {
		return getNext();
	}


	@Override
	public I value() {
		return getValue();
	}

}
