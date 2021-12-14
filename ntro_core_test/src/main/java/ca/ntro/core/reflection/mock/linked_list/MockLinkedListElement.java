package ca.ntro.core.reflection.mock.linked_list;

public class MockLinkedListElement<E extends Object> 

       implements LinkedListElement<E> {

	E value;
	MockLinkedListElement<E> next = null;

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public MockLinkedListElement<E> getNext() {
		return next;
	}

	public void setNext(MockLinkedListElement<E> next) {
		this.next = next;
	}

	public MockLinkedListElement() {
	}

	public MockLinkedListElement(E value) {
		setValue(value);
	}

	@Override
	public E value() {
		return getValue();
	}

	@Override
	public LinkedListElement<E> next() {
		return getNext();
	}

	@Override
	public boolean hasNext() {
		return getNext() != null;
	}

	public void insertAfter(MockLinkedListElement<E> newNext) {
		if(hasNext()) {

			MockLinkedListElement<E> newNextNext = getNext();

			newNext.setNext(newNextNext);
			setNext(newNext);

		}else {

			setNext(newNext);
		}
		
	}

	public void removeAfter() {
		if(hasNext()
				&& next().hasNext()) {
			
			MockLinkedListElement<E> newNext = getNext().getNext();
			setNext(newNext);

		}else {

			setNext(null);
		}
		
	}
}
