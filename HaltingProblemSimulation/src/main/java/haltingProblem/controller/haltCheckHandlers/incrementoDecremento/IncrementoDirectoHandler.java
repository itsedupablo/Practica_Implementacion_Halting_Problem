package haltingProblem.controller.haltCheckHandlers.incrementoDecremento;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import haltingProblem.controller.Handler;

//Handler que se encarga de detectar incrementos directos en el código fuente
public class IncrementoDirectoHandler implements Handler {
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
		if (comprobarExpresion(codigoFuente)) {
			if (nextHandler != null) {
				System.out.println("Incremento directo encontrado");
				return nextHandler.handle(codigoFuente);
			} else {
				throw new UnsupportedOperationException("No tengo siguiente Handler");
			}
		}
		System.out.println("No hay incrementos en el bucle");
		return nextHandler.handle(codigoFuente);
	}

	// Función auxiliar que comprueba si existe un incremento directo en el código
	// fuente
	private boolean comprobarExpresion(String codigoFuente) {
		Pattern pattern = Pattern.compile(
				"(?<!\\w)(\\*\\*)\\s*(\\w+)\\s*=\\s*(\\w+)(\\s*;|[)]|(?=\\s*[+-/*%&|<>!=]{1,2}))|(\\w+)(\\*\\*)\\s*=\\s*(\\w+)(\\s*;|[)]|(?=\\s*[+-/*%&|<>!=]{1,2}))|(?<!\\w)(\\+\\+)\\s*(\\w+)(\\s*;|[)]|(?=\\s*[+-/*%&|<>!=]{1,2}))|(\\w+)(\\+\\+)(\\s*;|[)]|(?=\\s*[+-/*%&|<>!=]{1,2}))");
		Matcher matcher = pattern.matcher(codigoFuente);
		if (matcher.find()) {
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
