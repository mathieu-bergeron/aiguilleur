package ca.ntro.core.wrappers;

public class PairNtro<L extends Object, R extends Object> implements Pair<L,R> {
	
	private L left;
	private R right;

	public L getLeft() {
		return left;
	}

	public void setLeft(L left) {
		this.left = left;
	}

	public R getRight() {
		return right;
	}

	public void setRight(R right) {
		this.right = right;
	}


	public PairNtro() {
	}

	public PairNtro(L left, R right) {
		setLeft(left);
		setRight(right);
	}

	@Override
	public L left() {
		return getLeft();
	}

	@Override
	public R right() {
		return getRight();
	}
}
