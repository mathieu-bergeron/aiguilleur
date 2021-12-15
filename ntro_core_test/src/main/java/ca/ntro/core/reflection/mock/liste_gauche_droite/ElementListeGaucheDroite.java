package ca.ntro.core.reflection.mock.liste_gauche_droite;


public class ElementListeGaucheDroite<I extends Object> {
	
	private I value;
	private ElementListeGaucheDroite<I> next;

	public ElementListeGaucheDroite<I> getNext() {
		return next;
	}

	public void setNext(ElementListeGaucheDroite<I> next) {
		this.next = next;
	}
	

	public I getValue() {
		return value;
	}

	public void setValue(I value) {
		this.value = value;
	}
	
	
	
	
	public ElementListeGaucheDroite() {
	}

	public ElementListeGaucheDroite(I value) {
		setValue(value);
	}


	public void insererApres(ElementListeGaucheDroite<I> newNext) {
		if(hasNext()) {

			ElementListeGaucheDroite<I> newNextNext = getNext();

			newNext.setNext(newNextNext);
			setNext(newNext);

		}else {

			setNext(newNext);
		}
		
	}

	public void retirerApres() {
		if(hasNext()
				&& next().hasNext()) {
			
			ElementListeGaucheDroite<I> newNext = getNext().getNext();
			setNext(newNext);

		}else {

			setNext(null);
		}
		
	}
	
	public boolean hasNext() {
		return getNext() != null;
	}

	public ElementListeGaucheDroite<I> next() {
		return getNext();
	}


	public I value() {
		return getValue();
	}

}
