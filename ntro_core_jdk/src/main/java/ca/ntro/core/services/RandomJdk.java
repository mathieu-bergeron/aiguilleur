package ca.ntro.core.services;

public class RandomJdk implements Random {
	
	private java.util.Random random = new java.util.Random(System.currentTimeMillis());

	@Override
	public boolean nextBoolean() {
		return random.nextBoolean();
	}

	@Override
	public int nextInt() {
		return random.nextInt();
	}

	@Override
	public int nextInt(int bound) {
		return random.nextInt(bound);
	}

}
