package ca.ntro.core.reflection.mock.objects;

import java.util.HashMap;
import java.util.Map;

public class TestObject02 {

	private TestObject01 testObject01 = new TestObject01(this);
	private Map<String, Character> map = new HashMap<>();
	
	public TestObject02() {
		map.put("key01", 'a');
		map.put("key02", 'a');
		map.put("key03", 'a');
	}
	
	public TestObject01 getTestObject01() {
		return testObject01;
	}

	public void setTestObject01(TestObject01 testObject01) {
		this.testObject01 = testObject01;
	}

	public Map<String, Character> getMap() {
		return map;
	}

	public void setMap(Map<String, Character> map) {
		this.map = map;
	}

}
