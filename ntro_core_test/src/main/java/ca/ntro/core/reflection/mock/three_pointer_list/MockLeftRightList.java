package ca.ntro.core.reflection.mock.three_pointer_list;

public class MockLeftRightList<I extends Object>
        
       implements LeftRightList<I> {
	
	private MockLeftRightListElement<I> left = null;;
	private MockLeftRightListElement<I> right = null;;

	private int sizeLeft = 0;
	private int sizeRight = 0;

	public MockLeftRightListElement<I> getLeft() {
		return left;
	}

	public void setLeft(MockLeftRightListElement<I> left) {
		this.left = left;
	}

	public MockLeftRightListElement<I> getRight() {
		return right;
	}

	public void setRight(MockLeftRightListElement<I> right) {
		this.right = right;
	}

	public int getSizeLeft() {
		return sizeLeft;
	}

	public void setSizeLeft(int sizeLeft) {
		this.sizeLeft = sizeLeft;
	}

	public int getSizeRight() {
		return sizeRight;
	}

	public void setSizeRight(int sizeRight) {
		this.sizeRight = sizeRight;
	}
	
	
	

	@Override
	public void add(I item) {
		insert(size(), item);
	}

	@Override
	public void addAll(I[] items) {
		for(I item : items) {
			add(item);
		}
	}
	
	public void shiftLeft() {
		MockLeftRightListElement<I> oldLeft = left;
		MockLeftRightListElement<I> oldRight = right;
		
		if(oldLeft != null) {
			left = oldLeft.getNext();
		}
		
		right = oldLeft;
		
		if(right != null) {
			right.setNext(oldRight);
		}
		
		sizeLeft--;
		sizeRight++;
	}

	private void shiftRight() {
		MockLeftRightListElement<I> oldLeft = left;
		MockLeftRightListElement<I> oldRight = right;
		
		if(oldRight != null) {
			right = oldRight.getNext();
		}
		
		left = oldRight;
		
		if(left != null) {
			left.setNext(oldLeft);
		}
		
		sizeRight--;
		sizeLeft++;
	}

	private MockLeftRightListElement<I> reachFrom(MockLeftRightListElement<I> from, int steps){
		MockLeftRightListElement<I> cursor = from;
		
		for(int i = 0; i<steps; i++) {
			if(cursor != null) {
				cursor = cursor.getNext();
			}
		}
		
		return cursor;
	}
	
	private void insertLeft(int steps, MockLeftRightListElement<I>  newItem) {
		MockLeftRightListElement<I> elementBefore = reachFrom(left, steps-1);

		if(elementBefore != null) {

			elementBefore.insertAfter(newItem);

		}else {
			
			newItem.setNext(left);
			left = newItem;
		}
	}

	private void insertRight(int steps, MockLeftRightListElement<I>  newItem) {
		MockLeftRightListElement<I> previousElement = reachFrom(right, steps-1);

		if(previousElement != null) {

			previousElement.insertAfter(newItem);

		}else {
			
			newItem.setNext(right);
			right = newItem;
		}
	}

	@Override
	public void insert(int index, I value) {
		
		MockLeftRightListElement<I> newItem = new MockLeftRightListElement<I>(value);
		
		if(index <= size()/2) {

			insertLeft(size()/2 - index, newItem);
			sizeLeft++;

		}else {

			insertRight(index - size()/2, newItem);
			sizeRight++;
		}
		
		if((sizeLeft - sizeRight) >= 2) {

			shiftLeft();

		}else if((sizeRight - sizeLeft) >= 2) {

			shiftRight();
		}
	}
	
	
	

	@Override
	public void set(int index, I item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public I get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		return sizeLeft + sizeRight;
	}

	@Override
	public boolean isEmpty() {
		return size() != 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
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
		
	} 
}
