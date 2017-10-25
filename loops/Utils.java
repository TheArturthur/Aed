package aed.loops;

public class Utils {
	public static int maxNumRepeated(Integer[] l, Integer elem)  {
		// Hay que modificar este metodo
		int res=0;
		
		/*if (l.length>0 && l[0].equals(elem)) {
			res++;
		}*/
		for (int i=1; i<l.length;i++) {
			if (((res==0 && l[i].equals(elem)) || (l[i].equals(elem) && l[i-1].equals(elem))) && !(res==0 && l[i].equals(elem)) && (l[i].equals(elem) && l[i-1].equals(elem))) {
				res++;
			}
		}
		return res;
	}
}
