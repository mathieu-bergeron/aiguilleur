package ca.ntro.core.reflection.mock.objects;

import java.util.ArrayList;
import java.util.List;

public class TestObject01 {
	
	private String attr01 = "asdf";
	private List<List<String>> attr02 = new ArrayList<>();

	private TestObject02 attr03 = null;

	private List<List<TestObject02>> attr04 = new ArrayList<>();
	
	private TestObject02[] attr05 = new TestObject02[4];

	private String[][] attr06 = new String[2][4];
	
	
	public String[][] getAttr06() {
		return attr06;
	}

	public void setAttr06(String[][] attr06) {
		this.attr06 = attr06;
	}

	public TestObject02[] getAttr05() {
		return attr05;
	}

	public void setAttr05(TestObject02[] attr05) {
		this.attr05 = attr05;
	}

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
		
		for(int i = 0; i < 4; i++) {
			getAttr05()[i] = new TestObject02();
		}
		
		for(int i = 0; i < getAttr06().length; i++) {
			for(int j = 0; j < getAttr06()[i].length; j++) {
				
				getAttr06()[i][j] = String.valueOf('a' + i + j);
			}
		}

	}


	public TestObject01(TestObject02 attr03) {
		setAttr03(attr03);
	}
}
