package haltingProblem.model;

import java.util.*;
//Programa que cuenta hacia abajo
public class CountDown extends Programa {
	final static Random r = new Random();
    private int inicio;

    //Constructor
    public CountDown() {
        super();
        this.inicio = 1000;
    }

    //Método para ejecutar el programa
    @Override
    public void ejecutar() {
        int i = inicio;
        while (i >= 0) {
            i--;
        }
    }
    
}
