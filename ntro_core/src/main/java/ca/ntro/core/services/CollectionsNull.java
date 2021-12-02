package ca.ntro.core.services;

import java.util.List;

public class CollectionsNull implements Collections {

	@Override
	public <I> List<I> sortList(List<I> input) {
		return input;
	}

}
