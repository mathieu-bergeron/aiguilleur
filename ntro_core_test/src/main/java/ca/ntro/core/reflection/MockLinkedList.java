package ca.ntro.core.reflection;

public class MockLinkedList<I extends Object> implements LinkedList<I> {
	
	private int size = 0;
	private MockLinkedListElement<I> head = null;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public MockLinkedListElement<I> getHead() {
		return head;
	}

	public void setHead(MockLinkedListElement<I> head) {
		this.head = head;
	}
	
	@Override
	public void add(I item) {
		insert(0, item);
	}

	@Override
	public void insert(int index, I item) {
		MockLinkedListElement<I> oldHead = head;
		MockLinkedListElement<I> newElement = new MockLinkedListElement<I>(item);

		if(index == 0) {

			if(oldHead != null) {
				newElement.insertAfter(oldHead);
			}
			
			head = newElement;

		} else {
			
			MockLinkedListElement<I> cursor = reachElement(index - 1);
			cursor.insertAfter(newElement);
		}
		
		size++;
	}
	
	private MockLinkedListElement<I> reachElement(int index) {
		MockLinkedListElement<I> cursor = head;
		
		for(int i = 0; i < index; i++) {

			if(cursor != null) {

				cursor = cursor.getNext();
			}
		}
		
		return cursor;
	}

	@Override
	public int size() {
		return size;
	}

}
