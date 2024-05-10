package haltingProblem.controller.haltCheckHandlers.incrementoDecremento;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;

//Handler que verifica si hay incrementos de valor asignado en el bucle
public class IncrementoValorAsignadoHandler implements Handler {
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
				System.out.println("Incremento de valor asignado encontrado");
				return nextHandler.handle(codigoFuente);
			} else {
				throw new UnsupportedOperationException("No tengo siguiente Handler");
			}
		}
		System.out.println("No hay incrementos de valor asignado en el bucle");
		return nextHandler.handle(codigoFuente);
	}

	//Función auxiliar que verifica si hay incrementos de valor asignado en el código fuente
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile("(?<!\\w)(\\+\\+)\\s*(\\w+)\\s*=\\s*(\\w+)(\\s*;|[)]|(?=\\s*[+-/*%&|<>!=]{1,2}))|(\\w+)(\\+\\+)\\s*=\\s*(\\w+)(\\s*;|[)]|(?=\\s*[+-/*%&|<>!=]{1,2}))");
		Matcher matcher = pattern.matcher(codigoFuente);
		if (matcher.find()) {
			return true;
		}
		return false;

	}
}
