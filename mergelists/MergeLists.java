package aed.mergelists;

import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import java.util.Comparator;


public class MergeLists {

  /**
   * Merges two lists ordered using the comparator cmp, returning
   * a new ordered list.
   * @returns a new list which is the ordered merge of the two argument lists
   */
  public static <E> PositionList<E> merge(final PositionList<E> l1,
                                          final PositionList<E> l2,
                                          final Comparator<E> comp) {
	  //First, we check if the given lists are null. If so, return null:
	  if (l1.isEmpty() || l2.isEmpty()) {
		  return null;
	  }
	  //If not null, we create the result list, a list to have the elements of both and two variables to manage the different values of the lists:
	  Position<E> i= l2.first();
	  PositionList<E> result= new NodePositionList<E>(l1);
	  do {
		  result.addLast(i.element());
		  i=l2.next(i);
	  }while (l2.next(i)!=null);
	  //Now result has the elements of both lists, though not sorted. We sort the elements:
	  PositionList<E> cursor;
	  
	  //We return the result list
	  return result;
  }

  /**
   * Merges two lists ordered using the comparator cmp, returning
   * a new ordered list.
   * @returns a new list which is the ordered merge of the two argument lists
   */
  public static <E> IndexedList<E> merge(final IndexedList<E> l1,
                                         final IndexedList<E> l2,
                                         final Comparator<E> comp) {
    // Hay que cambiar este metodo
    return null;
  }
}
