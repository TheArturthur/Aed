package aed.actasnotas;

import java.util.Comparator;

import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
//import aed.actasnotas.*;

public class ActaNotasAED implements ActaNotas {

	private IndexedList<Calificacion>calificacionesList;

	public ActaNotasAED() {
		this.calificacionesList = new ArrayIndexedList<Calificacion>();
	}

	private int pos=0;
	private boolean chkMatricula (String matricula) {
		boolean itIs=false;
		for (int i=0; i<calificacionesList.size() && !itIs; i++) {
			if (calificacionesList.get(i).matricula.equals(matricula)) {
				itIs= true;
				pos= i;
			}
		}
		return itIs;
	}
	private IndexedList<Calificacion> copy(IndexedList<Calificacion> calificacionesList) {
		IndexedList<Calificacion> list= new ArrayIndexedList<Calificacion>(); //creates the list to use
		for (int i=0; i<calificacionesList.size(); i++) {
			list.add(i, calificacionesList.get(i));
		}
		return list;
	}

	//----------------------------------------------------------------------------------------------------------------
	@Override
	public void addCalificacion(String nombre, String matricula, int nota) throws CalificacionAlreadyExistsException {
		// TODO Auto-generated method stub
		if (chkMatricula(matricula)) { //checks if matricula is already in the list. If true, then thorws the exception
			throw new CalificacionAlreadyExistsException();
		} else { //if false, adds the new calification to the list
			Calificacion cal= new Calificacion(nombre, matricula, nota);
			calificacionesList.add(calificacionesList.size(), cal);
		}
	}

	@Override
	public void updateNota(String matricula, int nota) throws InvalidMatriculaException {
		// TODO Auto-generated method stub
		if (!chkMatricula(matricula)) { //if the given matricula is not in the list, throws exception
			throw new InvalidMatriculaException();
		}else {
			calificacionesList.get(pos).setNota(nota);
		}
	}

	@Override
	public void deleteCalificacion(String matricula) throws InvalidMatriculaException {
		// TODO Auto-generated method stub
		if (!chkMatricula(matricula)) { //if the given matricula is not in the list, throws the exception
			throw new InvalidMatriculaException();
		}else {
			calificacionesList.removeElementAt(pos);
		}
	}

	@Override
	public Calificacion getCalificacion(String matricula) throws InvalidMatriculaException {
		// TODO Auto-generated method stub
		if (!chkMatricula(matricula)) { //if the given matricula is not in the list, throws exception
			throw new InvalidMatriculaException();
		}else {
			return calificacionesList.get(pos);
		}
	}

	@Override

	public IndexedList<Calificacion> getCalificaciones(Comparator<Calificacion> cmp) {
		Calificacion aux;
		//IndexedList<Calificacion>res = new ArrayIndexedList<Calificacion>();
		IndexedList<Calificacion>copia = copy(calificacionesList);
		for (int i = 0; i < copia.size()-1; i++) {
			if(cmp.compare(copia.get(i), copia.get(i+1))>0){
				aux=copia.get(i);
				copia.set(i,copia.get(i+1));
				copia.set(i+1, aux);
				i=-1;
			}

		}
		
		return copia;
	}
	@Override
	public IndexedList<Calificacion> getAprobados(int notaMinima) {
		// TODO Auto-generated method stub
		IndexedList<Calificacion> resList= new ArrayIndexedList<Calificacion>(); //creates the list to return
		IndexedList<Calificacion> aux= copy(calificacionesList);
		int j= 0;
		for (int i=0; i<aux.size(); i++) {
			if(aux.get(i).getNota()>=notaMinima) {
				resList.add(j, aux.get(i));
				j++;
			}
		}
		return resList;
	}

}
