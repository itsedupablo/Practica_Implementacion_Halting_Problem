package haltingProblem.controller.haltCheckHandlers.definicionVariables;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;

//Handler para verificar la definición variables booleanas
public class DefinicionBooleanosHandler implements Handler {
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
		try {
			if (comprobarExpresion(codigoFuente)) {
				if (nextHandler != null) {
					System.out.println("Definición de variables booleanas encontrada");
					return nextHandler.handle(codigoFuente);
				} else {
					throw new UnsupportedOperationException("No tengo siguiente Handler");
				}
			}
			System.out.println("No hay definición de variables booleanas en el código");
			return nextHandler.handle(codigoFuente);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	//Función auxiliar para comprobar si hay definición de variables booleanas en el código
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile("boolean\\\\s+\\\\w+(\\\\s+=[^=]+;)?\\\\s*");
		Matcher matcher = pattern.matcher(codigoFuente);
		if (matcher.find()) {
			return true;
		}
		return false;

	}

}
