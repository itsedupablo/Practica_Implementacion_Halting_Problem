package haltingProblem.model;

//Clase Reverser para invertir el resultado de haltCheck
public class Reverser {
    private static HaltChecker haltChecker;

    //Constructor
    public Reverser(HaltChecker haltChecker) {
        this.haltChecker = haltChecker;
    }

    public static boolean reverse() {
        // Invertir el resultado de haltCheck
        return !haltChecker.haltCheck();
    }
}
