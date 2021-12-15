package ca.ntro.core.reflection.mock.three_pointer_list;

public class MockLeftRightList<I extends Object>
        
       implements LeftRightList<I> {
	
	protected MockLeftRightListElement<I> left = null;;
	protected MockLeftRightListElement<I> right = null;;

	protected int sizeLeft = 0;
	protected int sizeRight = 0;

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

	protected void shiftRight() {
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

	private MockLeftRightListElement<I> reachItem(int index) {
		MockLeftRightListElement<I> cursor = null;

		if(shouldReachLeft(index)) {
			cursor = reachFrom(left, toLeftIndex(index));

		}else {

			cursor = reachFrom(right, toRightIndex(index));
		}
		return cursor;
	}

	protected MockLeftRightListElement<I> reachFrom(MockLeftRightListElement<I> from, int steps){
		MockLeftRightListElement<I> cursor = null;
		
		if(steps >= 0) {
			cursor = from;
		}
		
		for(int i = 0; i<steps; i++) {
			if(cursor != null) {
				cursor = cursor.getNext();
			}
		}
		
		return cursor;
	}

	protected int indexFrom(MockLeftRightListElement<I> from, int size, Object o){
		int index = -1;

		MockLeftRightListElement<I> cursor = from;
		
		for(int i = 0; i < size; i++) {
			if(cursor.getValue().equals(o)) {
				index = i;
				break;
			}

			cursor = cursor.getNext();
		}

		return index;
	}
	
	protected void insertLeft(int steps, MockLeftRightListElement<I>  newItem) {
		MockLeftRightListElement<I> elementBefore = reachFrom(left, steps);

		if(elementBefore != null) {

			elementBefore.insertAfter(newItem);

		}else {
			
			newItem.setNext(left);
			left = newItem;
		}
	}

	protected void insertRight(int steps, MockLeftRightListElement<I>  newItem) {
		MockLeftRightListElement<I> previousElement = reachFrom(right, steps-1);

		if(previousElement != null) {

			previousElement.insertAfter(newItem);

		}else {
			
			newItem.setNext(right);
			right = newItem;
		}
	}

	@Override
	public void insert(int listIndex, I value) {
		
		MockLeftRightListElement<I> newItem = new MockLeftRightListElement<I>(value);
		
		if(shouldInsertLeft(listIndex)) {

			insertLeft(toLeftIndex(listIndex), newItem);
			sizeLeft++;

		}else {

			insertRight(toRightIndex(listIndex), newItem);
			sizeRight++;
		}
		
		balance();
	}

	private void balance() {
		if(shouldShiftLeft()) {

			shiftLeft();

		}else if(shouldShiftRight()) {

			shiftRight();
		}
	}

	protected boolean shouldShiftRight() {
		return (sizeRight - sizeLeft) >= 2;
	}

	protected boolean shouldShiftLeft() {
		return (sizeLeft - sizeRight) >= 2;
	}

	protected int toRightIndex(int listIndex) {
		return listIndex - sizeLeft;
	}

	protected int toLeftIndex(int listIndex) {
		return (sizeLeft - 1 ) - listIndex;
	}

	protected int listIndexFromRightIndex(int indexRight) {
		return sizeLeft + indexRight;
	}

	protected int listIndexFromLeftIndex(int indexLeft) {
		return (sizeLeft - 1)  - indexLeft;
	}

	protected boolean shouldReachLeft(int index) {
		return index < sizeLeft;
	}

	protected boolean shouldInsertLeft(int index) {
		return index < sizeLeft;
		//return index <= size() / 2;
	}


	@Override
	public void set(int index, I item) {
		MockLeftRightListElement<I> cursor = reachItem(index);
		
		if(cursor != null) {
			cursor.setValue(item);
		}
	}


	@Override
	public I get(int index) {
		I result = null;
		MockLeftRightListElement<I> cursor = reachItem(index);
		
		if(cursor != null) {
			result = cursor.getValue();
		}

		return result;
	}

	@Override
	public void clear() {
		left = null;
		right = null;
		sizeLeft = 0;
		sizeRight = 0;
		
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
		int index = -1;
		
		index = indexFrom(left, sizeLeft, o);
		
		if(index != -1) {
			
			index = indexFrom(right, sizeRight, o);
			
			if(index != -1) {
				index = listIndexFromRightIndex(index);
			}
			
		}else {
			
			index = listIndexFromLeftIndex(index);
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
		if(shouldReachLeft(index)) {

			removeLeft(toLeftIndex(index));
			sizeLeft--;

		}else {

			removeRight(toRightIndex(index));
			sizeRight--;
		}
		
		balance();
		
	}

	protected void removeRight(int rightIndex) {
		MockLeftRightListElement<I> elementBefore = reachFrom(right, rightIndex-1);

		if(elementBefore != null) {

			elementBefore.removeAfter();

		}else {
			if(right != null) {
				right = right.getNext();
			}
		}
		
	}

	protected void removeLeft(int leftIndex) {
		MockLeftRightListElement<I> elementBefore = reachFrom(left, leftIndex-1);

		if(elementBefore != null) {

			elementBefore.removeAfter();

		}else {
			if(left != null) {
				left = left.getNext();
			}
		}
		
	} 
}
