package ca.ntro.core.services;

public class RandomNull implements Random {

	@Override
	public boolean nextBoolean() {
		return false;
	}

	@Override
	public int nextInt() {
		return 0;
	}

	@Override
	public int nextInt(int bound) {
		return 0;
	}

}

