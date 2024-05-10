package haltingProblem.controller;

//Interfaz que define contrato para todos los eslabones(manejadores) de la cadena de responsabilidad.
public interface Handler {
	//Método para obtener el siguiente eslabón de la cadena.
	public Handler getNext();
	//Método para asignar el siguiente eslabón de la cadena.
    public void setNext(Handler nextHandler);
    //Método para manejar la petición.
    public boolean handle(String codigoFuente);
	
}

