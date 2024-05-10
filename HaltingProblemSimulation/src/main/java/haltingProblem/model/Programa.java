package haltingProblem.model;

//Clase abstracta que permite la creación de programas
public abstract class Programa {
    // Constructor
	public Programa() {
	}

	// Método que ejecuta el programa
	public abstract void ejecutar();

	// Factory Method
	public static Programa crearPrograma(int tipoPrograma) {
		if (1 == tipoPrograma) {
			return new CountDown();
		} else if (2 == tipoPrograma) {
			return new CountUp();
		} else {
			throw new IllegalArgumentException("Tipo de programa no válido");
		}
	}

}