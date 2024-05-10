package haltingProblem.model;

import haltingProblem.controller.haltCheckHandlers.bucles.WhileLoopHandler;
import haltingProblem.controller.haltCheckHandlers.comparadores.ComparadorMayorHandler;
import haltingProblem.controller.haltCheckHandlers.incrementoDecremento.IncrementoDirectoHandler;

public class HaltChecker {
	private WhileLoopHandler whileLoopHandler;
	private IncrementoDirectoHandler incrementoDirectoHandler;
	private ComparadorMayorHandler comparadorMayorHandler;

	public HaltChecker(WhileLoopHandler whileLoopHandler, IncrementoDirectoHandler incrementoDirectoHandler,
			ComparadorMayorHandler comparadorMayorHandler) {
		this.whileLoopHandler = whileLoopHandler;
		this.incrementoDirectoHandler = incrementoDirectoHandler;
		this.comparadorMayorHandler = comparadorMayorHandler;
	}

	public boolean haltCheck() {
		boolean flag1 = WhileLoopHandler.isPosibleInfinito();
		boolean flag2 = IncrementoDirectoHandler.isPosibleInfinito();
		boolean flag3 = ComparadorMayorHandler.isPosibleInfinito();
		if (flag1 && flag2 && flag3) {
			return false;
		} else {
			return true;
		}
		
	}
}
