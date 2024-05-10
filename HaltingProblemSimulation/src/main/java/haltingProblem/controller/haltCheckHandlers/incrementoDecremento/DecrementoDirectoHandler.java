package haltingProblem.controller.haltCheckHandlers.incrementoDecremento;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;

//Handler que verifica si hay decrementos directos 
public class DecrementoDirectoHandler implements Handler {
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
				System.out.println("Decremento directo encontrado");
				return nextHandler.handle(codigoFuente);
			}
			else {
				throw new UnsupportedOperationException("No tengo siguiente Handler");
			}
		}
		System.out.println("No hay decrementos en el bucle");
		return nextHandler.handle(codigoFuente);
	}
	
	//Función auxiliar que comprueba si hay decrementos directos en el código fuente
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile("(?<!\\w)(\\-\\-)\\s*(\\w+)(\\s*;|[)]|(?=\\s*[+-/*%&|<>!=]{1,2}))|(\\w+)(\\-\\-)(\\s*;|[)]|(?=\\s*[+-/*%&|<>!=]{1,2}))\n");
		Matcher matcher = pattern.matcher(codigoFuente);
		if(matcher.find()){
	        return true;
	    }
		return false;
		
	}
}

