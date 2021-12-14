package ca.ntro.core.reflection.mock;

public interface SimpleList<I extends Object> {
	
	void    add(I item);                 
	void    addAll(I[] items);       
	void    insert(int index, I item);  
	void    set(int index, I item);     
	I       get(int index);          
	void    clear();                    
	int     size();                     
	boolean isEmpty();                  
	boolean contains(Object o);         
	int     indexOf(Object o);          
	void    removeValue(Object o);      
	void    remove(int index);       

}
