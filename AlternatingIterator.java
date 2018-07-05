package aed.altiterator;

import java.util.NoSuchElementException;
import java.util.Iterator;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;



public class AlternatingIterator<E> implements Iterator<E> {

	private PositionList<E> list;
	private Iterator<E> iterator;
	
	/** Crea un iterador de elementos para la lista pasada como argumento. */
	public AlternatingIterator(PositionList<E> list) {
		
		
		boolean tocaIzda = true;
		
		//PositionList<E> aux = list;
		Position<E> izquierda = list.first();
		Position<E> derecha = list.last();
		PositionList<E> res = new NodePositionList<E>();
		for(int i = 0; i<list.size(); i++){
			if(tocaIzda){
				if(i==0){
					res.addFirst(izquierda.element());
					izquierda = list.next(izquierda);
					tocaIzda = false;
				}else{
					res.addLast(izquierda.element());
					izquierda = list.next(izquierda);
					tocaIzda = false;
				}
			}else{
				res.addLast(derecha.element());
				derecha = list.prev(derecha);
				tocaIzda = true;
			}
		}
		this.list= res;
		this.iterator = this.list.iterator();
	}
	/** Dice si hay un siguiente elemento. */ 
	public boolean hasNext() {
		return iterator.hasNext();
	} 
																 
	/** Devuelve el siguiente elemento y actualiza el cursor. */
	public E next() throws NoSuchElementException {
		return this.iterator.next();
	}

	/** Elimina el ultimo elemento devuelto por next() */
	public void remove() throws IllegalStateException {
		throw new IllegalStateException("remove");
	}
}
