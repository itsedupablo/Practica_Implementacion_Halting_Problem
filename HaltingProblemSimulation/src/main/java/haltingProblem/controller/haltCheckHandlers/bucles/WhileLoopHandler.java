package haltingProblem.controller.haltCheckHandlers.bucles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;
//Manejador que se encarga de buscar un bucle while en el código fuente.
public class WhileLoopHandler implements Handler {
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
		try {
		if(comprobarExpresion(codigoFuente)){
			if (nextHandler != null) {
				System.out.println("Bucle while encontrado");
				return nextHandler.handle(codigoFuente);
			}
			else {
				throw new UnsupportedOperationException("No tengo siguiente Handler");
			}
		}
		throw new UnsupportedOperationException("No hay bucles while en el código");
		} catch (Exception e) {
	        
	        System.out.println(e.getMessage());
	        return false;
	    }
	}
	
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile("while\\s*\\(.*\\)\\s*\\{");
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
