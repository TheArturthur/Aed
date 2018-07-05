package aed.altiterator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.management.ManagementFactory;
import java.util.Iterator;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;


public class TesterLabJulio {
	/**
	 * Runs the test suite.
	 */
	public static void main(String args[]) {
		try {
			String[] ids = ManagementFactory.getRuntimeMXBean().getName()
					.split("@");
			BufferedWriter bw = new BufferedWriter(new FileWriter("pid"));
			bw.write(ids[0]);
			bw.close();
		} catch (Exception e) {
			System.out.println("Avisa al profesor de fallo sacando el PID");
		}

		doTest();
	}

	/**
	 * Executes the test suite.
	 */
	public static void doTest() {
		System.out.println("Testing AlternatingIterator...");

		Integer a0[] = {8,3,22,5,3};
		Integer a0_answer[] = {8,3,3,5,22};
		PositionList<Integer> l0 = mkPositionList(a0);
		PositionList<Integer> l0_copy = mkPositionList(a0);
		PositionList<Integer> l0_answer = mkPositionList(a0_answer);
		Iterator<Integer> it0 = new AlternatingIterator<Integer>(l0);
		PositionList<Integer> l0_op = toList(it0);

		if (!eqLists(l0_answer,l0_op)) {
			System.out.println
			("*** Error: the iterator for ("+printList(l0_copy)+
					") should return the elements "+printList(l0_answer)+
					" but returns the elements "+printList(l0_op));
			throw new Error();
		}

		Iterator<Integer> l0_it = new AlternatingIterator<Integer>(l0);

		l0_it = new AlternatingIterator<Integer>(l0);
		l0_it.next(); l0_it.next(); l0_it.next(); l0_it.next();
		if (!l0_it.hasNext()) {
			System.out.println
			("*** Error: hasNext at the fourth position of "+
					printList(l0)+
					" returns false");
			throw new Error();
		}

		if (!l0_it.hasNext()) {
			System.out.println
			("*** Error: hasNext at the new fourth position of "+
					printList(l0)+
					" returns false");
			throw new Error();
		}

		l0 = mkPositionList(a0);
		l0_it = new AlternatingIterator<Integer>(l0);
		l0_it.next(); l0_it.next(); l0_it.next(); l0_it.next();
		l0_it.next(); 
		if (l0_it.hasNext()) {
			System.out.println
			("*** Error: hasNext at the final third position of "+
					printList(l0)+
					" returns true");
			throw new Error();
		}


		Integer s_a0[] = {8,3,22,5,3};
		Integer s_a0_answer[] = {8,3,3,5,22};
		PositionList<Integer> s_l0 = mkPositionList(s_a0);
		PositionList<Integer> s_l0_copy = mkPositionList(s_a0);
		PositionList<Integer> s_l0_answer = mkPositionList(s_a0_answer);
		Iterator<Integer> s_it0 = new AlternatingIterator<Integer>(s_l0);
		PositionList<Integer> s_l0_op = toList(s_it0);

		if (!eqLists(s_l0_answer,s_l0_op)) {
			System.out.println
			("*** Error: the iterator for ("+printList(s_l0_copy)+
					") should return the elements "+printList(s_l0_answer)+
					" but returns "+printList(s_l0_op));
			throw new Error();
		}

		Integer s_a1[] = {8,1,3,22,5,6,3};
		Integer s_a1_answer[] = {8,3,1,6,3,5,22};
		PositionList<Integer> s_l1 = mkPositionList(s_a1);
		PositionList<Integer> s_l1_copy = mkPositionList(s_a1);
		PositionList<Integer> s_l1_answer = mkPositionList(s_a1_answer);
		Iterator<Integer> s_it1 = new AlternatingIterator<Integer>(s_l1);
		PositionList<Integer> s_l1_op = toList(s_it1);

		if (!eqLists(s_l1_answer,s_l1_op)) {
			System.out.println
			("*** Error: the iterator for ("+printList(s_l1_copy)+
					") should return the elements "+printList(s_l1_answer)+
					" but returns "+printList(s_l1_op));
			throw new Error();
		}

		Integer s_a2[] = {8,7,6,5};
		Integer s_a2_answer[] = {8,5,7,6};
		PositionList<Integer> s_l2 = mkPositionList(s_a2);
		PositionList<Integer> s_l2_copy = mkPositionList(s_a2);
		PositionList<Integer> s_l2_answer = mkPositionList(s_a2_answer);
		Iterator<Integer> s_it2 = new AlternatingIterator<Integer>(s_l2);
		PositionList<Integer> s_l2_op = toList(s_it2);

		if (!eqLists(s_l2_answer,s_l2_op)) {
			System.out.println
			("*** Error: the iterator for ("+printList(s_l2_copy)+
					") should return the elements "+printList(s_l2_answer)+
					" but returns "+printList(s_l2_op));
			throw new Error();
		}

		Integer s_a3[] = {4};
		Integer s_a3_answer[] = {4};
		PositionList<Integer> s_l3 = mkPositionList(s_a3);
		PositionList<Integer> s_l3_copy = mkPositionList(s_a3);
		PositionList<Integer> s_l3_answer = mkPositionList(s_a3_answer);
		Iterator<Integer> s_it3 = new AlternatingIterator<Integer>(s_l3);
		PositionList<Integer> s_l3_op = toList(s_it3);

		if (!eqLists(s_l3_answer,s_l3_op)) {
			System.out.println
			("*** Error: the iterator for ("+printList(s_l3_copy)+
					") should return the elements "+printList(s_l3_answer)+
					" but returns "+printList(s_l3_op));
			throw new Error();
		}

		Integer s_a5[] = {};
		Integer s_a5_answer[] = {};
		PositionList<Integer> s_l5 = mkPositionList(s_a5);
		PositionList<Integer> s_l5_copy = mkPositionList(s_a5);
		PositionList<Integer> s_l5_answer = mkPositionList(s_a5_answer);
		Iterator<Integer> s_it5 = new AlternatingIterator<Integer>(s_l5);
		PositionList<Integer> s_l5_op = toList(s_it5);

		if (!eqLists(s_l5_answer,s_l5_op)) {
			System.out.println
			("*** Error: the iterator for ("+printList(s_l5_copy)+
					") should return the elements "+printList(s_l5_answer)+
					" but returns "+printList(s_l5_op));
			throw new Error();
		}


		Integer a4[] = {8,3,22,9,5,6};
		Integer a4_answer[] = {8,6,3,5,22,9};
		PositionList<Integer> l4 = mkPositionList(a4);
		PositionList<Integer> l4_copy = mkPositionList(a4);
		PositionList<Integer> l4_answer = mkPositionList(a4_answer);
		Iterator<Integer> it4a = new AlternatingIterator<Integer>(l4);
		Iterator<Integer> it4b = new AlternatingIterator<Integer>(l4);

		it4a.next(); it4b.next(); it4b.next();
		Integer e4a = it4a.next();
		Integer e4b = it4b.next();

		if (e4a != 6) {
			System.out.println
			("*** Error: the second element in "+
					printList(l4_copy)+" should be 3 but is "+e4a);
			throw new Error();
		}

		if (e4b != 3) {
			System.out.println
			("*** Error: the third element in "+
					printList(l4_copy)+" should be 22 but is "+e4b);
			throw new Error();
		}


		System.out.println("Test finalizado correctamente.");
	}

	static PositionList<Integer> mkPositionList(Integer arr[]) {
		PositionList<Integer> list = new NodePositionList<Integer>();

		for (int i=0; i<arr.length; i++)
			list.addLast(arr[i]);
		return list;
	}

	static <E> void checkOrig(PositionList<E> orig, PositionList<E> l) {
		if (!eqLists(orig,l)) {
			System.out.println
			("*** Error: the reverse() method has modified the original "+
					"list.\nIt should still be "+printList(orig)+
					" but is "+printList(l));
			throw new Error();
		}
	}


	/**
	 * Returns a string representation of the position list argument.
	 */
	static <E> String printList(PositionList<E> l) {
		StringBuffer sl = new StringBuffer(); 

		if (l == null) sl.append("null");
		else {
			int size = l.size();
			if (size == 0) sl.append("[]");
			else {
				sl.append("[");
				Position<E> currPos = l.first();
				while (size > 0) {
					sl.append(currPos.element());
					if (--size > 0) {
						currPos = l.next(currPos);
						sl.append(",");
					}
				}
				sl.append("]");
			}
		}
		return sl.toString();
	}

	static <E> PositionList<E> toList(Iterator<E> it) {
		PositionList<E> l = new NodePositionList<E>();
		while (it.hasNext()) {
			E element = null;
			try { element = it.next(); }
			catch (Exception exc) { 
				System.out.println
				("*** Error: the iterator threw an exception "+
						"on calling next even though hasNext returned true");
				throw new Error();
			}
			l.addLast(element);
		}
		return l;
	}

	static <E> boolean eqLists(PositionList<E> l1,
			PositionList<E> l2) {

		if (l1==null && l2==null) return true;
		else if (l1 == null) return false;
		else if (l2 == null) return false;

		int size_l1 = l1.size();
		int size_l2 = l2.size();
		boolean equal = true;

		if (size_l1 != size_l2) 
			equal=false;
		else {
			Position<E> l1pos = null;
			Position<E> l2pos = null;

			if (size_l1 > 0) l1pos = l1.first();
			if (size_l2 > 0) l2pos = l2.first();

			while (size_l1 > 0 && equal) {
				if (!l1pos.element().equals(l2pos.element()))
					equal = false;
				if (--size_l1 > 0) {
					l1pos = l1.next(l1pos);
					l2pos = l2.next(l2pos);
				}
			}
		}
		return equal;
	}


}
