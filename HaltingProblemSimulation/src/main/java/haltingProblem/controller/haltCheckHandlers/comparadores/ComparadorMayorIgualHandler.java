package haltingProblem.controller.haltCheckHandlers.comparadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;

//Handler que verifica si en la sentencia del bucle hay un comparador mayor o igual que
public class ComparadorMayorIgualHandler implements Handler {
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
				System.out.println("Comparador mayor o igual que encontrado");
				return nextHandler.handle(codigoFuente);
			}
			else {
				throw new UnsupportedOperationException("No tengo siguiente Handler");
			}
		}
		System.out.println("No hay comparador mayor o igual que en la sentencia del bucle");
		return nextHandler.handle(codigoFuente);
	}
	
	//Función auxiliar que comprueba si la expresión tiene un comparador mayor o igual que
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile("\\((\\s*\\w+\\s*>=[\\s\\d]*\\w+\\s*)\\)");		
		Matcher matcher = pattern.matcher(codigoFuente);
		if(matcher.find()){
	        return true;
	    }
		return false;
		
	}

}
