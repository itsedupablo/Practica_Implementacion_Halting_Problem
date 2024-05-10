package haltingProblem.controller.haltCheckHandlers.comparadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;

//Handler que verifica si en la sentencia del bucle hay un comparador mayor que
public class ComparadorMayorHandler implements Handler {
	private Handler nextHandler;
	private static boolean posibleInfinito = false;

	@Override
	public Handler getNext() {
		return nextHandler;
	}

	@Override
	public void setNext(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

	@Override
	public boolean handle(String codigoFuente) {
		if(comprobarExpresion(codigoFuente)){
			if (nextHandler != null) {
				System.out.println("Comparador mayor que encontrado");
				return nextHandler.handle(codigoFuente);
			}
			else {
				throw new UnsupportedOperationException("No tengo siguiente Handler");
			}
		}
		System.out.println("No hay comparador mayor que en la sentencia del bucle");
		return nextHandler.handle(codigoFuente);
	}
	
	// Función auxiliar que comrpueba si en el código fuente hay un comparador mayor que
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile("\\((\\s*\\w+\\s*>[\\s\\d]*\\w+\\s*)\\)");
		Matcher matcher = pattern.matcher(codigoFuente);
		if(matcher.find()){
			posibleInfinito = true;
	        return true;
	    }
		return false;
		
	}

	// Función que devuelve si es posible que el bucle sea infinito
	public static boolean isPosibleInfinito() {
		return posibleInfinito;
	}

}

