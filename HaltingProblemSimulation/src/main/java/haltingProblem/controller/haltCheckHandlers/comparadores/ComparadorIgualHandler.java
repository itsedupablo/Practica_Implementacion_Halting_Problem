package haltingProblem.controller.haltCheckHandlers.comparadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;

//Handler que verifica si en el código fuente hay un comparador de igualdad
public class ComparadorIgualHandler implements Handler {
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
		if (comprobarExpresion(codigoFuente)) {
			if (nextHandler != null) {
				System.out.println("Comparador de igualdad encontrado");
				return nextHandler.handle(codigoFuente);
			} else {
				throw new UnsupportedOperationException("No tengo siguiente Handler");
			}
		}
		System.out.println("No hay comparador de igualdad en la sentencia del bucle");
		return nextHandler.handle(codigoFuente);

	}

	// Función auxiliar que comprueba si en el código fuente hay un comparador de igualdad
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile("\\((\\s*\\w+\\s*==\\s*\\w+\\s*)\\)");
		Matcher matcher = pattern.matcher(codigoFuente);
		if (matcher.find()) {
			return true;
		}
		return false;

	}

}
