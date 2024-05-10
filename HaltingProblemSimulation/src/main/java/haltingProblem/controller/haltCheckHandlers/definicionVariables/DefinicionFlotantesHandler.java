package haltingProblem.controller.haltCheckHandlers.definicionVariables;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;

//Handler que verifica si hay definición de variables de coma flotante en el código
public class DefinicionFlotantesHandler implements Handler {
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
				System.out.println("Definición de variables de coma flotante encontrada");
				return nextHandler.handle(codigoFuente);
			}
			else {
				throw new UnsupportedOperationException("No tengo siguiente Handler");
			}
		}
		System.out.println("No hay definición de variables de coma flotante en el código");
		return nextHandler.handle(codigoFuente);
	}
	
	//Función auxiliar que verifica si hay definición de variables de coma flotante en el código
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile("(double|float)\\s+[a-zA-Z_][a-zA-Z_0-9]*\\s*=?(\\s*[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?)?;");
		Matcher matcher = pattern.matcher(codigoFuente);
		if(matcher.find()){
	        return true;
	    }
		return false;
		
	}
}