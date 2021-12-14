package ca.ntro.core.reflection;

import java.util.ArrayList;
import java.util.List;

public class TestObject01 {
	
	private String attr01 = "asdf";
	private List<List<String>> attr02 = new ArrayList<>();

	private TestObject02 attr03 = null;

	private List<List<TestObject02>> attr04 = new ArrayList<>();

	public List<List<TestObject02>> getAttr04() {
		return attr04;
	}

	public void setAttr04(List<List<TestObject02>> attr04) {
		this.attr04 = attr04;
	}

	public String getAttr01() {
		return attr01;
	}

	public void setAttr01(String attr01) {
		this.attr01 = attr01;
	}

	public List<List<String>> getAttr02() {
		return attr02;
	}

	public void setAttr02(List<List<String>> attr02) {
		this.attr02 = attr02;
	}

	public TestObject02 getAttr03() {
		return attr03;
	}

	public void setAttr03(TestObject02 attr03) {
		this.attr03 = attr03;
	}

	public TestObject01() {
		List<String> subList01 = new ArrayList<>();
		List<String> subList02 = new ArrayList<>();
		List<String> subList03 = new ArrayList<>();
		
		subList01.add("a");
		subList01.add("b");
		subList01.add("c");

		subList02.add("d");
		subList02.add("e");

		subList03.add("f");
		subList03.add(null);
		subList03.add("g");
		subList03.add("h");

		getAttr02().add(subList01);
		getAttr02().add(subList02);
		getAttr02().add(subList03);
		setAttr03(new TestObject02());

		List<TestObject02> objectList01 = new ArrayList<>();
		List<TestObject02> objectList02 = new ArrayList<>();
		
		objectList01.add(new TestObject02());
		objectList01.add(new TestObject02());

		objectList02.add(new TestObject02());
		
		getAttr04().add(objectList01);
		getAttr04().add(objectList02);

	}


	public TestObject01(TestObject02 attr03) {
		setAttr03(attr03);
	}
}
