package haltingProblem.controller.haltCheckHandlers.definicionVariables;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;

// Handler para comprobar si hay definición de variables enteras
public class DefinicionEnterosHandler implements Handler {
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
				System.out.println("Definición de variables enteras encontrada");
				return nextHandler.handle(codigoFuente);
			}
			else {
				throw new UnsupportedOperationException("No tengo siguiente Handler");
			}
		}
		System.out.println("No hay definición de variables enteras en el código");
		return true;
	}
	
	// Función auxiliar para comprobar si una expresión es una definición de variables enteras en el código fuente
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile("(int|long|short|byte)\\s+[a-zA-Z_][a-zA-Z_0-9]*\\s*=?(\\s*[0-9]+)?;");
		Matcher matcher = pattern.matcher(codigoFuente);
		if(matcher.find()){
	        return true;
	    }
		return false;
		
	}
}

