package ca.ntro.core.reflection;

public class TestObjectCycle {

	private TestObjectCycle testObjectCycle = this;

	public TestObjectCycle getTestObjectCycle() {
		return testObjectCycle;
	}

	public void setTestObjectCycle(TestObjectCycle testObjectCycle) {
		this.testObjectCycle = testObjectCycle;
	}

}
