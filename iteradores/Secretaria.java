package aed.iteradores;


import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.positionlist.*;
import java.util.Iterator;


/**
 * Administra una coleccion de asignaturas.
 */
public class Secretaria {
	private Iterable<AsignaturaAdmin> asignaturas;

	/**
	 * Empieza administrar una coleccion de asignaturas.
	 */
	public Secretaria(Iterable<AsignaturaAdmin> asignaturas) {
		this.asignaturas = asignaturas;
	}

	private AsignaturaAdmin findAsignatura(String asignatura) {
		Iterator<AsignaturaAdmin> it = asignaturas.iterator();
		AsignaturaAdmin res = null; 
		while (it.hasNext() && res == null) {
			AsignaturaAdmin admin = it.next();
			if (admin.getNombreAsignatura().equals(asignatura)) {
				res = admin;
			}
		}
		return res;
	}

	private AsignaturaAdmin getAsignatura(String asignatura)
			throws InvalidAsignaturaException {
		AsignaturaAdmin admin = findAsignatura(asignatura);
		if (admin == null) throw new InvalidAsignaturaException();
		else return admin;
	}



	private PositionList<AsignaturaAdmin> buscar(String matricula) {//devuelve la lista de asignaturas en las que está matriculado
		PositionList<AsignaturaAdmin> res= new NodePositionList<AsignaturaAdmin>();
		Iterator<AsignaturaAdmin> it= asignaturas.iterator();
		while(it.hasNext()){
			AsignaturaAdmin aux=it.next();
			if(aux.estaMatriculado(matricula)){
				res.addFirst(aux);
				res.first().element().matriculados();
			}
		}
		return res;
	}

	//	private double calcularNotamediaAsignatura(AsignaturaAdmin a1){
	//		double res=a1.notaMedia();
	//		return res;
	//	}

	/**
	 * Matricula una coleccion de alumnos (representados por el
	 * parametro matriculas) en una asignatura.
	 * @return los numeros de matricula de los alumnos matriculados.
	 * @throws InvalidAsignaturaException si la asignatura no
	 * esta siendo administrada por la secretaria.
	 */
	public Iterable<String> matricular(String asignatura, Iterable<String> matriculas)
			throws InvalidAsignaturaException {
		return getAsignatura(asignatura).matricular(matriculas);
	}

	/**
	 * Desmatricula una coleccion de alumnos (representados por el
	 * parametro matriculas) de una asignatura.
	 * @return las matriculas desmatriculados (que debian estar
	 * matriculados y no tener nota).
	 * @throws InvalidAsignaturaException si la asignatura no esta
	 * siendo administrado por la secretaria.
	 */
	public Iterable<String> desMatricular(String asignatura, Iterable<String> matriculas)
			throws InvalidAsignaturaException {
		return getAsignatura(asignatura).desMatricular(matriculas);
	}

	/**
	 * Calcula la nota media de un alumno (representado por su
	 * identificador de matricula) en todas asignaturas en las que esta
	 * matriculado.  Si el alumno no tiene ninguna nota en ninguna
	 * asignatura, el metodo debe devolver 0.
	 * @return la nota media del alumno.
	 */
	public double notaMediaExpediente (String matricula) {
		double res=0.0;
		int contador=0;
		Iterator<AsignaturaAdmin> it=asignaturas.iterator();
		while(it.hasNext()){
			try {
				res=res+it.next().getNota(matricula);
				contador=contador+1;
			} catch (InvalidMatriculaException e) {
				e.getLocalizedMessage();
			}
		}
		if(contador==0)contador=1;
		return res/contador;
	}

	/**
	 * Devuelve el nombre de la asignatura que tiene la mejor nota
	 * media, calculada usando las notas de todos los alumnos que tienen
	 * notas para esa asignatura.  Si la secretaria no esta
	 * administrando ninguna asignatura, el metodo debe devolver
	 * null. Similarmente, si ningun alumno tiene nota en ninguna
	 * asignatura, el metodo tambien debe devolver null.
	 * @return el nombre de la asignatura con la mejor nota media.
	 * 
	 */
	public String mejorNotaMedia() {
		String result= null;
		if(this.asignaturas==null) {
			return result;
		}
		double notaMedia= 0;
		Iterator<AsignaturaAdmin> it= asignaturas.iterator();
		while(it.hasNext()) {
			AsignaturaAdmin asignatura= it.next();
			double notaMedia1= asignatura.notaMedia();
			if(notaMedia<notaMedia1) {
				notaMedia=notaMedia1;
				result= asignatura.getNombreAsignatura();
			}
		}
		return result;
	}

	/**
	 * Devuelve todas las notas de un alumno (representado por su
	 * identificador de matricula) como una coleccion de objetos
	 * Pair(NombreAsignatura, Nota).
	 * @return una coleccion de pares de las notas de la matricula en
	 * todas las asignaturas.
	 */
	public Iterable<Pair<String,Integer>> expediente(String matricula) {
		// Completar este metodo
		PositionList<Pair<String,Integer>> result= new NodePositionList<Pair<String,Integer>>();
		try {
			PositionList<AsignaturaAdmin> asignaturasAlumno= new NodePositionList<AsignaturaAdmin>(this.buscar(matricula));
			Iterator<AsignaturaAdmin> it= asignaturasAlumno.iterator();
			while(it.hasNext()) {
				AsignaturaAdmin ptr= it.next();
				Pair<String, Integer> cursor= null;
				cursor = new Pair<String,Integer>(ptr.getNombreAsignatura(),ptr.getNota(matricula));
				if (cursor!=null) {
					result.addLast(cursor);
				}
			}
		} catch (InvalidMatriculaException e) {
			e.getLocalizedMessage();
		}
		return result;
	}

	/**
	 * Devuelve una coleccion con todas los pares de asignaturas --
	 * representados como Pair(NombreAsignatura1, NombreAsignatura2) --
	 * que no tienen alumnos en comun.  El metodo NO debe devolver nunca
	 * un par Pair(NombreAsignatura,NombreAsignatura), es decir, con
	 * nombres iguales de asignaturas.  Si dos asignaturas A1 y A2 no
	 * tienen ningun alumno en comun, para ellas se puede devolver: (i)
	 * Pair(A1,A2), o (ii) Pair(A1,A2), Pair(A2,A1), o (iii)
	 * Pair(A2,A1).
	 * @return una coleccion que contiene todos los pares de asignaturas
	 * que no tienen ningun alumno en comun.
	 */
	public Iterable<Pair<String,String>> asignaturasNoConflictivas () {
		// Completar este metodo
		PositionList<Pair<String,String>> result= new NodePositionList<Pair<String,String>>();
		Iterator<AsignaturaAdmin>it1= this.asignaturas.iterator();
		Iterator<AsignaturaAdmin>it2= this.asignaturas.iterator();
		while(it1.hasNext()) {
			AsignaturaAdmin a1= it1.next();
			while(it2.hasNext()) {
				AsignaturaAdmin a2= it2.next();
				if(a1!=a2) {
					if(!compartenAlumnos(a1, a2)) {
						result.addLast(new Pair<String,String>(a1.getNombreAsignatura(),a2.getNombreAsignatura()));
					}
				}
			}
			it2= this.asignaturas.iterator();
		}
		return result;
	}

	/**
	 * Devuelve true si dos asignaturas a1 y a2 tienen algun alumno en
	 * comun.
	 * @return true si las dos asignaturas no tienen ningun alumno en comun.
	 */
	private boolean compartenAlumnos (AsignaturaAdmin a1, AsignaturaAdmin a2) {
		Iterator<String> it1= a1.matriculados().iterator(); 
		while(it1.hasNext()) {
			String aux1= it1.next();
			Iterator<String> it2= a2.matriculados().iterator();
			while(it2.hasNext()) {
				String aux2=it2.next();
				if(aux1.equals(aux2)){
					return true;
				}
			}
		}
		return false;
	}
}
