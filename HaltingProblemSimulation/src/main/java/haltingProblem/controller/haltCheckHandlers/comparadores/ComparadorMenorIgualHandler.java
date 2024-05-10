package haltingProblem.controller.haltCheckHandlers.comparadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;

//Handler que maneja los comparadores menor o igual que
public class ComparadorMenorIgualHandler implements Handler {
	private Handler nextHandler;

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
				System.out.println("Comparador menor o igual que encontrado");
				return nextHandler.handle(codigoFuente);
			}
			else {
				throw new UnsupportedOperationException("No tengo siguiente Handler");
			}
		}
		System.out.println("No hay comparador menor o igual que en la sentencia del bucle");
		 throw new UnsupportedOperationException("No tengo siguiente Handler");
	}
	
	// Función auxiliar para comprobar si el código fuente contiene un comparador menor o igual que
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile("(?i)\\\\b(?!\\\\s)\\\\w+\\\\s*<=\\\\s*(?!\\\\s)\\\\w+\\\\b");
		Matcher matcher = pattern.matcher(codigoFuente);
		if(matcher.find()){
	        return true;
	    }
		return false;
		
	}


}

