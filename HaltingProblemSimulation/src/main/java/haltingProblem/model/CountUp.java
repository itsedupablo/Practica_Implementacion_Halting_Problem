package haltingProblem.model;

//Programa que cuenta hacia arriba
public class CountUp extends Programa {
	private static final int INICIO = 0;

   //Constructor
	public CountUp() {
		super();
	}

	// Método que ejecuta el programa
	@Override
	public void ejecutar() {
		int i = INICIO + 1;
		while (i > INICIO) {
			i++;

		}
	}

}
