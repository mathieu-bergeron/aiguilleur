package ca.ntro.core.reflection;

import java.util.ArrayList;
import java.util.List;

public class TestObject01 {
	
	private String attr01 = "asdf";
	private List<String> attr02 = new ArrayList<>();
	private TestObject02 attr03 = null;

	public String getAttr01() {
		return attr01;
	}

	public void setAttr01(String attr01) {
		this.attr01 = attr01;
	}

	public List<String> getAttr02() {
		return attr02;
	}

	public void setAttr02(List<String> attr02) {
		this.attr02 = attr02;
	}

	public TestObject02 getAttr03() {
		return attr03;
	}

	public void setAttr03(TestObject02 attr03) {
		this.attr03 = attr03;
	}

	public TestObject01() {
		getAttr02().add("listValue00");
		getAttr02().add("listValue01");
		getAttr02().add("listValue02");
		setAttr03(new TestObject02());
	}


	public TestObject01(TestObject02 attr03) {
		setAttr03(attr03);
	}
}
