package aed.secretaria;


import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;


/**
 * Organizes the administration for an asignatura.
 * An asignatura has a name (e.g., "AED"), and the class
 * keeps track of matriculated alumnos, 
 * and of assigned notas for alumnos.
 */
public class AsignaturaAdmin {
	// Name of asignatura
	private String nombreAsignatura;

	// A list of pairs of matricula (String) and notas (integer)
	// Note that the nota should be null if no nota has
	// been assigned yet.
	private PositionList<Pair<String,Integer>> notas;

	/**
	 * Creates an asignatura administration object, 
	 * where the asignatura has a name (e.g. "AED"),
	 * and which keeps tracks of matriculated alumnos (their matriculas), 
	 * and assigned notas.
	 */
	public AsignaturaAdmin(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
		this.notas = new NodePositionList<Pair<String,Integer>>();
	}
	//--------------------------------------------------------------------------------------------------

	//****************!!!!!!!!!!!!!!!!!!!!!!!!!PONER COMO PRIVADO!!!!!!!!!!!!
	private  Position<Pair<String,Integer>>  buscar (String buscado)throws InvalidMatriculaException{//devuelve el nodo de posición donde se ha encontrado la matrícula
		Position<Pair<String, Integer>>res=null;
		boolean latch=false;
		for (Position<Pair<String, Integer>>node=notas.first();(node!=null) && (!latch);node=notas.next(node)) {
			//	System.out.println("dento del for");
			if(node.element().getLeft().equals(buscado)){
				res=node;
				latch=true;

			}

		}
		if(res==null){
//			System.out.println("matricula invalida");
			throw new InvalidMatriculaException();

		}
		return res;
	}
	//	private  Position<Pair<String,Integer>>  buscar (Integer buscado)throws InvalidMatriculaException{
	//		Position<Pair<String, Integer>>node=notas.first();
	//		Position<Pair<String, Integer>>res=null;
	//		boolean latch=false;
	//		for (int j = 0; j<notas.size()&&!latch; j++) {
	//			if(node.element().getRight().equals(buscado)){
	//				res=node;
	//				latch=true;
	//			}
	//			node=notas.next(node);
	//		}
	//		if(res==null){
	//			throw new InvalidMatriculaException();
	//		}
	//
	//
	//
	//		return res;
	//	}



	//--------------------------------------------
	/**
	 * Returns the asignatura name.
	 * @return the asignatura name.
	 */

	public String getNombreAsignatura() {

		return nombreAsignatura;
	}

	/**
	 * Adds a number of matriculas
	 * to the asignatura. Returns a list of the matriculas
	 * that were added,
	 * i.e., the list of matriculas which had not previously been added.
	 * @return a list with the matriculas added.
	 */

	//--------------------------------------MAL------------------------------------------
	public PositionList<String> matricular(PositionList<String> matriculas) {




		PositionList<String> aux= new NodePositionList<String>();
		for(Position<String> node= matriculas.first(); node!=null ;node=matriculas.next(node)){

			if(!estaMatriculado(node.element())){
				aux.addFirst(node.element());
				notas.addLast(new Pair<String,Integer>(node.element(),null) );				
			}
		}
		return aux;
	}
	/**
	 * Removes a list of matriculas from the asignatura.
	 * Returns a list with the matriculas which were successfully
	 * removed. A matricula can be removed IF:
	 * i) the matricula was previously added and has not been removed since, AND
	 * ii) there is NO nota associated with the matricula.
	 * @return a list with the matriculas that were removed.
	 */
	public PositionList<String> desMatricular(PositionList<String> matriculas) {
		Position<Pair<String,Integer>> node = notas.first();
		PositionList<String> aux= new NodePositionList<String>();	

		for(Position<String> nodMatr =matriculas.first();nodMatr!=null;nodMatr=matriculas.next(nodMatr)){
//			System.out.println("entro en el bucle");
			try {
//				System.out.println("try");
//				System.out.println("nodeMatr: "+nodMatr.element().toString());
				node=buscar(nodMatr.element().toString());
//				System.out.println("funciona");
				if (node.element().getRight()==null){
					aux.addFirst(nodMatr.element());
					notas.remove(node);
				}
			} catch (InvalidMatriculaException e) {			
				 
//				 return aux;
			}
		}
		
//		for(Position<String> de = aux.first();de!=null;de=aux.next(de)){
//			System.out.println(de.element());
//		}
//		System.out.println("aux: "+aux.toString());
		return aux;
	}
	
//	public PositionList<String> desMatricular(PositionList<String> matriculas) {
//		PositionList<String> resultado = new NodePositionList<String>();
//		for( Position<String> cursor = matriculas.first() ; cursor != null ; cursor = matriculas.next(cursor) ){
//			if( desMatricula(cursor.element()) ){
//				resultado.addLast(cursor.element());
//			}
//		}
//		return resultado;
//	}
//	
//	private boolean desMatricula(String matricula){
//		boolean encontrado = false, borrado = false;
//		Position<Pair<String,Integer>> cursor = notas.first();
//		while(cursor != null && !encontrado){
//			if(cursor.element().getLeft().equals(matricula)){
//				encontrado = true;
//				if(cursor.element().getRight() == null){
//					borrado = true;
//					notas.remove(cursor);
//				}
//			}else{
//				cursor = notas.next(cursor);
//			}
//		}
//		return borrado;
//	}
	/**
	 * Checks whether a matricula has been added to the asignatura.
	 * @return true if the matricula has been added, false otherwise.
	 */
	public boolean estaMatriculado(String matricula) {
		// Hay que modificar este metodo

		boolean latch=false;

		for( Position<Pair<String,Integer>> node = notas.first() ; node != null && !latch ; node = notas.next(node) ){		
			if(node.element().getLeft().equals(matricula)){
				latch =true;		
			}
		}
		return latch;

	}

	/**
	 * Checks whether a matricula has received a nota.
	 * @return true if the matricula has a nota, and false otherwise.
	 * @throws InvalidMatriculaException if the matricula 
	 * has not been added to the asignatura (or was removed)
	 */
	public boolean tieneNota(String matricula) throws InvalidMatriculaException {

		Position<Pair<String,Integer>> node = buscar(matricula);		
		return node!=null&&node.element().getRight()!=null;

	}


	/**
	 * Returns the nota of a matricula.
	 * @return the nota of an matrciula.
	 * @throws InvalidMatriculaException if the matricula has no nota assigned,
	 * or the matricula has not been added to the asignatura (or was removed).
	 */
	public int getNota(String matricula) throws InvalidMatriculaException {
		Position<Pair<String,Integer>> node = buscar(matricula);
		//		if (node==null)return null;
		if(node.element().getRight()==null)throw new InvalidMatriculaException();
		return node.element().getRight();

	}



	/**
	 * Assigns a nota for a matricula.
	 * @throws InvalidMatriculaException if the matricula has not
	 * been added to the asignatura (or was removed).
	 */
	public void setNota(String matricula, int nota) throws InvalidMatriculaException {
		//		 Hay que modificar este metodo
		Position<Pair<String,Integer>> node=buscar(matricula);
		node.element().setRight(nota);



	}

	/**
	 * Returns a list with the matriculas who has a nota between 
	 * the minimum nota minNota (including it) and the maximum nota maxNota
	 * (including it).
	 * @return a list with the matriculas
	 * with notas between (including) minNota...maxNota.
	 */
	public PositionList<String> alumnosEnRango(int minNota, int maxNota) {

		PositionList<String>res=new NodePositionList<String>();

		for(Position<Pair<String,Integer>>node= notas.first();node!=null;node=notas.next(node)) {
			Integer notanode=node.element().getRight();
			if( notanode != null  && notanode <= maxNota && notanode>= minNota){//si la nota está en rango 


				res.addFirst(node.element().getLeft());
			}
		}
		// Hay que modificar este metodo

		return res;
	}

	/**
	 * Calculates the average grade of the notas in the asignatura.
	 * NOTE. Does not count alumnos (matriculas) that have not been assigned
	 * a nota.
	 * NOTE. The average grade for an empty set of notas is defined to be 0.
	 * @return the average grade of the asignatura.
	 */
	public double notaMedia() {

		Position<Pair<String,Integer>>b_notas= notas.first();
		double res=0;
		int contadorNotas=0;
		if(notas.size()==0)return 0.0;
		for (int i = 0; i < notas.size(); i++) {
			if(b_notas.element().getRight()!=null){
				res=res+b_notas.element().getRight();
				contadorNotas++;
			}
			b_notas=notas.next(b_notas);

		}
		if(contadorNotas==0)return 0.0;
		return res/contadorNotas;
	}
	public static void main (String[]args) throws InvalidMatriculaException{

		//una forma de hacer listas
		PositionList<String> matriculas = new NodePositionList<String>();
		matriculas.addFirst("hola");
		matriculas.addLast("caracola");
		matriculas.addFirst("dea");


		//forma easy peasy
		PositionList<String> test = new NodePositionList<String>(new String[]{"2222222"});
		PositionList<String> test1 = new NodePositionList<String>(new String[]{"111","222","333","444"});
		PositionList<String> test2 = new NodePositionList<String>(new String[]{"5555555","2222222", "3333333", "1111111"});
		AsignaturaAdmin asignaturatest= new AsignaturaAdmin("try");
		asignaturatest.matricular(test);
		
		System.out.println("/////////////////////");
		System.out.println("BUCLE TEST");	
		for(Position<Pair<String,Integer>> node = asignaturatest.notas.first();node!=null;node=asignaturatest.notas.next(node)){
			System.out.println(node.element().getLeft());
		}
		System.out.println("/////////////////////");
		asignaturatest.desMatricular(test2);
		//asignaturatest.setNota("22222", 3);
//System.out.println(asignaturatest.getNota("22222"));
//		asignaturatest.matricular(test1);
//		asignaturatest.setNota("111", 3);
//		asignaturatest.desMatricular(test);
//				asignaturatest.matricular(test);
//				asignaturatest.matricular(test2);
//				asignaturatest.setNota("333", 3);
//				asignaturatest.setNota("5555", 3);
//		asignaturatest.desMatricular(test2);
//				asignaturatest.setNota("5555", 2);
//				asignaturatest.desMatricular(test1);
//		System.out.println(asignaturatest.getNota("111"));
//			System.out.println("estamatriculadotest : "+asignaturatest.estaMatriculado("111"));




//		System.out.println("BUCLE TEST");	
//		for(Position<Pair<String,Integer>> node = asignaturatest.notas.first();node!=null;node=asignaturatest.notas.next(node)){
//			System.out.println(node.element().getLeft());
//		}

		//		System.out.println("\n");
		//		System.out.println("test buscar:"+asignaturatest.buscar("111").element().toString());
		//
		//		//		System.out.println("\n");
		//		//		System.out.println("test buscar:"+asignaturatest.buscar("333").element().toString());
		//		//
		//		//		System.out.println("\n");
		//		//		System.out.println("test buscar:"+asignaturatest.buscar("444").element().toString());
		//
		//		System.out.println("\n");
		//		System.out.println("test buscar:"+asignaturatest.buscar("000").element().toString());
		//		System.out.println("tieneNota(111) "+asignaturatest.tieneNota("111") );


	}



}

