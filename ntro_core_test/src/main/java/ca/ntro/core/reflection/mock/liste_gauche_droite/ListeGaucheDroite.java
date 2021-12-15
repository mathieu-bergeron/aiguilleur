package ca.ntro.core.reflection.mock.liste_gauche_droite;

import ca.ntro.core.reflection.mock.SimpleList;

public class ListeGaucheDroite<V extends Object>
        
       implements SimpleList<V> {


	protected ElementListeGaucheDroite<V> gauche = null;;
	protected ElementListeGaucheDroite<V> droite = null;;

	protected int tailleGauche = 0;
	protected int tailleDroite = 0;

	public ElementListeGaucheDroite<V> getGauche() {
		return gauche;
	}

	public void setGauche(ElementListeGaucheDroite<V> gauche) {
		this.gauche = gauche;
	}

	public ElementListeGaucheDroite<V> getDroite() {
		return droite;
	}

	public void setDroite(ElementListeGaucheDroite<V> droite) {
		this.droite = droite;
	}

	public int getTailleGauche() {
		return tailleGauche;
	}

	public void setTailleGauche(int tailleGauche) {
		this.tailleGauche = tailleGauche;
	}

	public int getTailleDroite() {
		return tailleDroite;
	}

	public void setTailleDroite(int tailleDroite) {
		this.tailleDroite = tailleDroite;
	}

	@Override
	public void add(V valeur) {
		insert(size(), valeur);
	}

	@Override
	public void addAll(V[] valeurs) {
		for(V valeur : valeurs) {
			add(valeur);
		}
	}
	
	public void deplacerUnElementADroite() {
		ElementListeGaucheDroite<V> ancienneGauche = gauche;
		ElementListeGaucheDroite<V> ancienneDroite = droite;
		
		if(ancienneGauche != null) {
			gauche = ancienneGauche.getNext();
		}
		
		droite = ancienneGauche;
		
		if(droite != null) {
			droite.setNext(ancienneDroite);
		}

		tailleGauche--;
		tailleDroite++;
		
	}

	protected void deplacerUnElementAGauche() {
		ElementListeGaucheDroite<V> ancienneGauche = gauche;
		ElementListeGaucheDroite<V> ancienneDroite = droite;
		
		if(ancienneDroite != null) {
			droite = ancienneDroite.getNext();
		}
		
		gauche = ancienneDroite;
		
		if(gauche != null) {
			gauche.setNext(ancienneGauche);
		}
		
		tailleDroite--;
		tailleGauche++;
	}

	private ElementListeGaucheDroite<V> atteindreElement(int indiceListe) {
		ElementListeGaucheDroite<V> curseur = null;

		if(doitAllerAGauche(indiceListe)) {

			curseur = atteindreAPartirDe(gauche, versIndiceGauche(indiceListe));

		}else {

			curseur = atteindreAPartirDe(droite, versIndiceDroite(indiceListe));
		}

		return curseur;
	}

	protected ElementListeGaucheDroite<V> atteindreAPartirDe(ElementListeGaucheDroite<V> debut, int nombreDePas){
		ElementListeGaucheDroite<V> curseur = null;
		
		if(nombreDePas >= 0) {
			curseur = debut;
		}
		
		for(int i = 0; i < nombreDePas; i++) {

			if(curseur != null) {

				curseur = curseur.getNext();

			}
		}
		
		return curseur;
	}

	protected int indiceAPartirDe(ElementListeGaucheDroite<V> debut, int tailleChaine, Object o){
		int index = -1;

		ElementListeGaucheDroite<V> curseur = debut;
		
		for(int i = 0; i < tailleChaine; i++) {

			if(curseur.getValue().equals(o)) {
				index = i;
				break;
			}

			curseur = curseur.getNext();
		}

		return index;
	}
	
	protected void insererAGauche(int indiceGauche, ElementListeGaucheDroite<V>  nouvelElement) {
		ElementListeGaucheDroite<V> elementAvant = atteindreAPartirDe(gauche, indiceGauche);

		if(elementAvant != null) {

			elementAvant.insererApres(nouvelElement);

		}else {
			
			nouvelElement.setNext(gauche);
			gauche = nouvelElement;
		}
	}

	protected void insererADroite(int indiceDroite, ElementListeGaucheDroite<V>  nouvelElement) {
		ElementListeGaucheDroite<V> elementAvant = atteindreAPartirDe(droite, indiceDroite-1);

		if(elementAvant != null) {

			elementAvant.insererApres(nouvelElement);

		}else {
			
			nouvelElement.setNext(droite);
			droite = nouvelElement;
		}
	}

	@Override
	public void insert(int listIndex, V value) {
		
		ElementListeGaucheDroite<V> nouvelElement = new ElementListeGaucheDroite<V>(value);
		
		if(doitAllerAGauche(listIndex)) {

			insererAGauche(versIndiceGauche(listIndex), nouvelElement);
			tailleGauche++;

		}else {

			insererADroite(versIndiceDroite(listIndex), nouvelElement);
			tailleDroite++;
		}
		
		equilibrer();
	}

	protected void equilibrer() {
		if(plusLourdAGauche()) {

			deplacerUnElementADroite();

		}else if(plusLourdADroite()) {

			deplacerUnElementAGauche();
		}
	}

	protected boolean plusLourdADroite() {
		return (tailleDroite - tailleGauche) >= 2;
	}

	protected boolean plusLourdAGauche() {
		return (tailleGauche - tailleDroite) >= 2;
	}

	protected int versIndiceDroite(int listIndex) {
		return listIndex - tailleGauche;
	}

	protected int versIndiceGauche(int listIndex) {
		return (tailleGauche - 1) - listIndex;
	}

	protected int indiceListeAPartirDeIndiceDroite(int indiceDroite) {
		return tailleGauche + indiceDroite;
	}

	protected int indiceListeAPartirDeIndiceGauche(int indiceGauche) {
		return (tailleGauche - 1) - indiceGauche;
	}

	protected boolean doitAllerAGauche(int listIndex) {
		return listIndex < tailleGauche;
	}

	@Override
	public void set(int indiceListe, V valeur) {
		ElementListeGaucheDroite<V> curseur = atteindreElement(indiceListe);
		
		if(curseur != null) {
			curseur.setValue(valeur);
		}
	}


	@Override
	public V get(int indiceListe) {
		V result = null;
		ElementListeGaucheDroite<V> curseur = atteindreElement(indiceListe);
		
		if(curseur != null) {
			result = curseur.getValue();
		}

		return result;
	}

	@Override
	public void clear() {
		gauche = null;
		droite = null;
		tailleGauche = 0;
		tailleDroite = 0;
		
	}

	@Override
	public int size() {
		return tailleGauche + tailleDroite;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public int indexOf(Object o) {
		int indiceListe = -1;

		int indiceGauche = indiceAPartirDe(gauche, tailleGauche, o);
		
		if(indiceGauche != -1) {

			indiceListe = indiceListeAPartirDeIndiceGauche(indiceGauche);

		}else {

			int indiceDroite = indiceAPartirDe(droite, tailleDroite, o);

			if(indiceDroite != -1) {

				indiceListe = indiceListeAPartirDeIndiceDroite(indiceDroite);
			}
		}

		return indiceListe;
	}

	@Override
	public void removeValue(Object o) {
		int index = indexOf(o);

		if(index != -1) {
			remove(index);
		}
	}

	@Override
	public void remove(int indiceListe) {
		if(doitAllerAGauche(indiceListe)) {

			retirerAGauche(versIndiceGauche(indiceListe));
			tailleGauche--;

		}else {

			retirerADroite(versIndiceDroite(indiceListe));
			tailleDroite--;
		}
		
		equilibrer();
		
	}

	protected void retirerADroite(int indiceDroite) {
		ElementListeGaucheDroite<V> elementBefore = atteindreAPartirDe(droite, indiceDroite-1);

		if(elementBefore != null) {

			elementBefore.retirerApres();

		}else {
			if(droite != null) {
				droite = droite.getNext();
			}
		}
		
	}

	protected void retirerAGauche(int indiceGauche) {
		ElementListeGaucheDroite<V> elementBefore = atteindreAPartirDe(gauche, indiceGauche-1);

		if(elementBefore != null) {

			elementBefore.retirerApres();

		}else {
			if(gauche != null) {
				gauche = gauche.getNext();
			}
		}
	} 
}
