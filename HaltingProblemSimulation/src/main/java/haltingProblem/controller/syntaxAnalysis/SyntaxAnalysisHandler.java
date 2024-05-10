package haltingProblem.controller.syntaxAnalysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import haltingProblem.controller.Handler;

//Manejador de análisis sintáctico
public class SyntaxAnalysisHandler implements Handler {
	private Handler nextHandler;
	
	//Función para leer el texto de un archivo y convertirlo en un String
    public static String leerTexto(String archivo) {
        StringBuilder codigoFuente = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                codigoFuente.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codigoFuente.toString();
    }

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
		if (nextHandler != null) {
			System.out.println("Análisis sintáctico realizado");
			return nextHandler.handle(codigoFuente);
		} else {
			throw new UnsupportedOperationException("No tengo siguiente Handler");
		}
	}
	
}

