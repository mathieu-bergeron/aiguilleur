package ca.ntro.core.reflection.mock.linked_list;

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
		insert(size(), item);
	}

	@Override
	public void insert(int index, I item) {
		MockLinkedListElement<I> oldHead = head;
		MockLinkedListElement<I> newElement = new MockLinkedListElement<I>(item);

		if(index == 0) {

			if(oldHead != null) {
				newElement.insertAfter(oldHead);
			}
			
			setHead(newElement);

		} else {
			
			MockLinkedListElement<I> cursor = reachElement(index - 1);
			cursor.insertAfter(newElement);
		}
		
		setSize(getSize() + 1);
	}
	
	private MockLinkedListElement<I> reachElement(int index) {
		MockLinkedListElement<I> cursor = getHead();
		
		for(int i = 0; i < index; i++) {

			if(cursor != null) {

				cursor = cursor.getNext();
			}
		}
		
		return cursor;
	}

	@Override
	public int size() {
		return getSize();
	}

	@Override
	public void addAll(I[] items) {
		for(I item : items) {
			add(item);
		}
		
	}

	@Override
	public void set(int index, I item) {
		MockLinkedListElement<I> cursor = reachElement(index);
		cursor.setValue(item);
	}

	@Override
	public I get(int index) {
		I item = null;

		MockLinkedListElement<I> cursor = reachElement(index);

		item = cursor.getValue();
		
		return item;
	}

	@Override
	public void clear() {
		setHead(null);
		setSize(0);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public int indexOf(Object o) {
		int index = -1;

		MockLinkedListElement<I> cursor = getHead();
		
		int i = 0;
		
		while(cursor != null) {

			if(cursor.getValue().equals(o)) {
				index = i;
				break;
				
			}else {
				
				i++;
				cursor = cursor.getNext();
			}
		}
		
		return index;
	}

	@Override
	public void removeValue(Object o) {
		int index = indexOf(o);
		if(index != -1) {
			remove(index);
		}
	}

	@Override
	public void remove(int index) {
		if(index == 0) {

			setHead(getHead().getNext());

		}else {

			MockLinkedListElement<I> cursor = reachElement(index - 1);
			cursor.removeAfter();
		}
	}
}
