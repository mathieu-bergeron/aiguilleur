package ca.ntro.core.reflection.mock.array_list;

public class      MockArrayList<I extends Object> 

       implements SimpleArrayList<I> {
	
	private static final int INITIAL_SIZE = 8;

	private ArrayFactory<I> factory;

	private I[] table = null;
	private int firstIndex;
	private int lastIndex;

	public I[] getTable() {
		return table;
	}

	public void setTable(I[] table) {
		this.table = table;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	
	
	
	

	public MockArrayList() {
	}
	
	public MockArrayList(ArrayFactory<I> factory) {
		this.factory = factory;
		initialize();
	}
	
	
	private void initialize() {
		setTable(factory.newInstance(INITIAL_SIZE));

		setFirstIndex(INITIAL_SIZE / 2);
		setLastIndex(INITIAL_SIZE / 2 - 1);
	}

	@Override
	public void add(I item) {
		insert(size(),item);
	}

	@Override
	public void addAll(I[] items) {
		for(I item : items) {
			add(item);
		}
	}
	
	
	private void copy(I[] src, int srcFirstIndex, int srcLastIndex, I[] dst, int dstFirstIndex) {
		int offset = dstFirstIndex - srcFirstIndex;

		for(int i = srcFirstIndex; i <= srcLastIndex; i++){
			dst[i+offset] = src[i];
		}
	}
	
	private boolean needsToGrow() {
		return firstIndex == 0
				|| lastIndex == table.length - 1;
	}

	private void growTable() {
		int oldSize = size();
		I[] newTable = factory.newInstance(table.length * 2);
		
		int newFirstIndex = (newTable.length / 2) - oldSize / 2;
		int newLastIndex = newFirstIndex + oldSize - 1;
		
		copy(table, firstIndex, lastIndex, newTable, newFirstIndex);
		
		setTable(newTable);
		setFirstIndex(newFirstIndex);
		setLastIndex(newLastIndex);
	}
	
	public void shiftLeft(int startIndex, int endIndex) {
		for(int i = startIndex; i <= endIndex; i++) {
			table[i-1] = table[i];
		}
	}

	public void shiftRight(int startIndex, int endIndex) {
		for(int i = endIndex; i >= startIndex; i--) {
			table[i+1] = table[i];
		}
	}
	
	
	private int tableIndex(int listIndex) {
		return firstIndex + listIndex;
		
	}

	private int listIndex(int tableIndex) {
		return tableIndex - firstIndex;
		
	}

	public boolean listIndexBeforeListMiddle(int listIndex) {

		return listIndex <= (size() / 2);
	}

	@Override
	public void insert(int listIndex, I item) {
		if(needsToGrow()) {
			growTable();
		}
		
		int oldSize = size();
		int newSize = oldSize + 1;

		if(listIndexBeforeListMiddle(listIndex)) {
			
			shiftLeft(firstIndex, tableIndex(listIndex) - 1);
			
			firstIndex--;
			lastIndex = (newSize-1) + firstIndex;

			table[tableIndex(listIndex)] = item;
			
		}else {

			shiftRight(tableIndex(listIndex), lastIndex);
			
			lastIndex++;
			firstIndex = lastIndex - (newSize-1);

			table[tableIndex(listIndex)] = item;
		}
	}

	@Override
	public void set(int listIndex, I item) {
		table[tableIndex(listIndex)] = item;
	}

	@Override
	public I get(int listIndex) {
		return table[tableIndex(listIndex)];
	}

	@Override
	public void clear() {
		initialize();
	}

	@Override
	public int size() {
		return lastIndex - firstIndex + 1;
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
		int tableIndex = -1;

		for(int i = firstIndex; i <= lastIndex; i++) {
			if(table[i].equals(o)) {
				tableIndex = i;
				break;
			}
		}

		return listIndex(tableIndex);
	}

	@Override
	public void removeValue(Object o) {
		int index = indexOf(o);
		
		if(index != -1) {

			remove(index);
		}
	}

	@Override
	public void remove(int listIndex) {
		
		if(listIndexBeforeListMiddle(listIndex)) {
			
			shiftRight(firstIndex, tableIndex(listIndex) - 1);
			
			firstIndex++;

		}else {

			shiftLeft(tableIndex(listIndex) + 1, lastIndex);
			
			lastIndex--;

		}
	}
}
