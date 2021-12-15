package ca.ntro.core.reflection.mock.liste_array;

import ca.ntro.core.reflection.mock.SimpleList;
import ca.ntro.core.reflection.mock.array_list.ArrayFactory;

public class      ListeArray<V extends Object> 

       implements SimpleList<V> {

	
	private static final int TAILLE_INITIALE = 8;
	
	private ArrayFactory<V> factory;

	private V[] grosTableau = null;
	private int indicePremierElement;
	private int indiceDernierElement;

	public V[] getGrosTableau() {
		return grosTableau;
	}

	public void setGrosTableau(V[] grosTableau) {
		this.grosTableau = grosTableau;
	}

	public int getIndicePremierElement() {
		return indicePremierElement;
	}

	public void setIndicePremierElement(int indicePremierElement) {
		this.indicePremierElement = indicePremierElement;
	}

	public int getIndiceDernierElement() {
		return indiceDernierElement;
	}

	public void setIndiceDernierElement(int indiceDernierElement) {
		this.indiceDernierElement = indiceDernierElement;
	}

	public ListeArray(ArrayFactory<V> factory) {
		this.factory = factory;

		initialiser();
	}
	
	private void initialiser() {
		grosTableau = factory.newInstance(TAILLE_INITIALE);

		indicePremierElement = TAILLE_INITIALE / 2;
		indiceDernierElement = TAILLE_INITIALE / 2 - 1;
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
	
	
	private void copier(V[] src, int debutSrc, int finSrc, V[] dst, int debutDst) {
		int decalage = debutDst - debutSrc;

		for(int i = debutSrc; i <= finSrc; i++){
			dst[i+decalage] = src[i];
		}
	}
	
	private boolean doitAgrandir() {
		return indicePremierElement == 0
				|| indiceDernierElement == grosTableau.length - 1;
	}

	private void agrandir() {
		int ancienneTaille = size();
		V[] nouveauTableau = factory.newInstance(grosTableau.length * 2);
		
		int nouvelIndicePremierElement = (nouveauTableau.length / 2) - ancienneTaille / 2;
		int nouvelIndiceDernierElement = nouvelIndicePremierElement + ancienneTaille - 1;
		
		copier(grosTableau, indicePremierElement, indiceDernierElement, nouveauTableau, nouvelIndicePremierElement);
		
		grosTableau = nouveauTableau;
		indicePremierElement = nouvelIndicePremierElement;
		indiceDernierElement = nouvelIndiceDernierElement;
	}
	
	public void decalerAGauche(int indiceDebut, int indiceFin) {
		for(int i = indiceDebut; i <= indiceFin; i++) {
			grosTableau[i-1] = grosTableau[i];
		}
	}

	public void decalerADroite(int indiceDebut, int indiceFin) {
		for(int i = indiceFin; i >= indiceDebut; i--) {
			grosTableau[i+1] = grosTableau[i];
		}
	}
	
	
	private int indiceGrosTableau(int indiceListe) {
		return indicePremierElement + indiceListe;
		
	}

	private int indiceListe(int indiceGrosTableau) {
		return indiceGrosTableau - indicePremierElement;
		
	}

	private boolean doitAllerAGauche(int indiceListe) {
		return indiceListe <= (size() / 2);
	}

	@Override
	public void insert(int indiceListe, V valeur) {
		if(doitAgrandir()) {
			agrandir();
		}
		
		int ancienneTaille = size();
		int nouvelleTaille = ancienneTaille + 1;
		
		if(doitAllerAGauche(indiceListe)) {
			
			decalerAGauche(indicePremierElement, indiceGrosTableau(indiceListe) - 1);
			
			indicePremierElement--;
			indiceDernierElement = indicePremierElement + (nouvelleTaille - 1);
			
			grosTableau[indiceGrosTableau(indiceListe)] = valeur;
			
		}else {
			
			decalerADroite(indiceGrosTableau(indiceListe), indiceDernierElement);
			
			indiceDernierElement++;
			indicePremierElement = indiceDernierElement - (nouvelleTaille - 1);
			
			grosTableau[indiceGrosTableau(indiceListe)] = valeur;
		}
	}

	@Override
	public void set(int indiceListe, V valeur) {
		grosTableau[indiceGrosTableau(indiceListe)] = valeur;
	}

	@Override
	public V get(int listIndex) {
		return grosTableau[indiceGrosTableau(listIndex)];
	}

	@Override
	public void clear() {
		initialiser();
	}

	@Override
	public int size() {
		return indiceDernierElement - indicePremierElement + 1;
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
		int indiceGrosTableau = -1;

		for(int i = indicePremierElement; i <= indiceDernierElement; i++) {
			if(grosTableau[i].equals(o)) {
				indiceGrosTableau = i;
				break;
			}
		}
		
		if(indiceGrosTableau != -1) {
			indiceListe = indiceListe(indiceGrosTableau);
		}

		return indiceListe;
	}

	@Override
	public void removeValue(Object o) {
		int indiceListe = indexOf(o);
		
		if(indiceListe != -1) {
			remove(indiceListe);
		}
	}

	@Override
	public void remove(int indiceListe) {
		
		if(doitAllerAGauche(indiceListe)) {
			
			decalerADroite(indicePremierElement, indiceGrosTableau(indiceListe) - 1);
			
			indicePremierElement++;
			
		}else {

			decalerAGauche(indiceGrosTableau(indiceListe) + 1, indiceDernierElement);
			
			indiceDernierElement--;

		}
	}
}
