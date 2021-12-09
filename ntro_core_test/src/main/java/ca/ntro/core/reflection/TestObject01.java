package ca.ntro.core.reflection;

import java.util.ArrayList;
import java.util.List;

public class TestObject01 {
	
	private String attribute01 = "asdf";
	private List<String> attr02 = new ArrayList<>();

	public String getAttribute01() {
		return attribute01;
	}

	public void setAttribute01(String test) {
		this.attribute01 = test;
	}
	
	public List<String> getAttr02() {
		return attr02;
	}

	public void setAttr02(List<String> attr02) {
		this.attr02 = attr02;
	}

	public TestObject01() {
		getAttr02().add("listValue00");
		getAttr02().add("listValue01");
		getAttr02().add("listValue02");
	}


}
