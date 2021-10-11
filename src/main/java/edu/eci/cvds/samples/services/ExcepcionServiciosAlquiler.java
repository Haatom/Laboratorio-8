package edu.eci.cvds.samples.services;

public class ExcepcionServiciosAlquiler extends Exception{
    public static final  String NO_ITEM = "Ese item es inexistente .";
    public static final  String NO_CLIENTE = "Ese cliente es inexistente";
    public static final  String NO_TIPOITEM = "Ese tipoItem es inexistente";
    public static final  String NO_ALQUILERITEM = "Ese Alquiler es inexistente ";
    public static final  String FECHA_LIMITE_INVALIDA = "La fecha es mayor a la de entrega";
    public static final  String DIAS_INVALIDOS = "No puede ingresar dias menores a 0";
    public static final  String TARIFA_INVALIDA = "La tarifa no puede ser menor a 0";
    public ExcepcionServiciosAlquiler(String message){
        super(message);
    }

    public ExcepcionServiciosAlquiler(String message, Exception e){
        super(message,e);
    }
}
