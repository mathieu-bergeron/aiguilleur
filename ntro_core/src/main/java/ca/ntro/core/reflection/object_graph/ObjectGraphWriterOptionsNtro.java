package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptionsNtro;

public class      ObjectGraphWriterOptionsNtro 

       extends    DirectedGraphWriterOptionsNtro 
       
       implements ObjectGraphWriterOptions {
	
	private boolean objectAsStructure = true;
	private boolean stringAsSimpleValue = true;
	private boolean mapAsSimpleValue = true;
	private boolean listAsSimpleValue = true;

	public boolean getObjectAsStructure() {
		return objectAsStructure;
	}

	public void setObjectAsStructure(boolean objectAsStructure) {
		this.objectAsStructure = objectAsStructure;
		
		setStringAsSimpleValue(true);
		setListAsSimpleValue(true);
		setMapAsSimpleValue(true);
	}

	public boolean getStringAsSimpleValue() {
		return stringAsSimpleValue;
	}

	public void setStringAsSimpleValue(boolean stringAsSimpleValue) {
		this.stringAsSimpleValue = stringAsSimpleValue;
	}

	public boolean getMapAsSimpleValue() {
		return mapAsSimpleValue;
	}

	public void setMapAsSimpleValue(boolean mapAsSimpleValue) {
		this.mapAsSimpleValue = mapAsSimpleValue;
	}

	public boolean getListAsSimpleValue() {
		return listAsSimpleValue;
	}

	public void setListAsSimpleValue(boolean listAsSimpleValue) {
		this.listAsSimpleValue = listAsSimpleValue;
	}

	@Override
	public boolean objectAsStructure() {
		return getObjectAsStructure();
	}

	@Override
	public boolean stringAsSimpleValue() {
		return getStringAsSimpleValue();
	}

	@Override
	public boolean mapAsSimpleValue() {
		return getMapAsSimpleValue();
	}

	@Override
	public boolean listAsSimpleValue() {
		return getListAsSimpleValue();
	}
}
