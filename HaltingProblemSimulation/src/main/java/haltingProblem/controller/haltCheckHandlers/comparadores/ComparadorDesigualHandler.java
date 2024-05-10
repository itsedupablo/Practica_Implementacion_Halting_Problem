package haltingProblem.controller.haltCheckHandlers.comparadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;

//Handler que se encarga de comprobar si en la expresión del bucle hay un comparador de desigualdad
public class ComparadorDesigualHandler implements Handler {
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
					System.out.println("Comparador de desigualdad encontrado");
					return nextHandler.handle(codigoFuente);
				} else {
					throw new UnsupportedOperationException("No tengo siguiente Handler");
				}
			}
			System.out.println("No hay comparador de desigualdad en la sentencia del bucle");
			return nextHandler.handle(codigoFuente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	// Función auxiliar que comprueba si la expresión tiene un comparador de desigualdad
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile("\\((\\s*\\w+\\s*!=\\s*\\w+\\s*)\\)");
		Matcher matcher = pattern.matcher(codigoFuente);
		if (matcher.find()) {
			return true;
		}
		return false;

	}

}