
package fp2.poo.pfpooadrdelvil1;

import fp2.poo.utilidades.AgendaAbstracta;
import fp2.poo.utilidades.TelefonoInterfaz;
import fp2.poo.utilidades.ContactoInterfaz;
import fp2.poo.utilidades.DatosDeEntrada;

import fp2.poo.utilidades.Excepciones.OperacionNoPermitidaExcepcion;

import java.util.*;
import java.io.File;

public class Agenda extends AgendaAbstracta{

    private ArrayList<ContactoInterfaz> listaContactos = null;
    private int numContactos = 0;

     /** 
     * Constructor de Agenda
     *
     *@param args[] parametros introducidos
     *@throws NoSuchElementException por si no encuentra contactos
     *@throws OperacionNoPermitidaExcepcion errores relacionados 
     * con el telefono o con el correo
     */

    public Agenda ( String args[] ) throws OperacionNoPermitidaExcepcion, NoSuchElementException {
	this.listaContactos = new ArrayList<ContactoInterfaz>(MAX_NUM_CONTACTOS);
	DatosDeEntrada obj = null;
	/*
	 * Tratamiento de los errores en ejecución de:
	 * - Numero de argumentos incorrecto.
	 * - Fichero inexistente.
	 */
	if( args.length != 1)
	    throw new OperacionNoPermitidaExcepcion("Numero de argumentos incorrecto"); 
 
	File archivo = new File(AgendaAbstracta.RUTA + args[0]);
	if (!archivo.exists()) {
	    throw new OperacionNoPermitidaExcepcion("Fichero inexistente."); 
	} 
 
	try {
	    obj = new DatosDeEntrada( args[0] );
	} catch (Exception e) {
	    throw new OperacionNoPermitidaExcepcion();
	} 
	while(obj.hasNext()) {
	    try {
		ContactoInterfaz contacto = obj.next();
		this.insertarContacto ( contacto ); 
	    } catch (OperacionNoPermitidaExcepcion e) {
		throw e;
	    }
	}
    }

    /**
     *  Inserta un contacto.
     *
     *@param contacto de tipo ContactoInterfaz
     *@throws OperacionNoPermitidaExcepcion por si se supera
     * el numero maximo de contactos a listar o el numero
     * ya se encuentra registrado
     *
     */
    public void insertarContacto(ContactoInterfaz contacto) throws OperacionNoPermitidaExcepcion{
	int j = 0;
	numContactos = listaContactos.size();
	try{
	if(numContactos == MAX_NUM_CONTACTOS){
	    throw new OperacionNoPermitidaExcepcion("La agenda esta completa, no caben mas contactos");
	}
	else
	    {	
		for(int i = 0; i < listaContactos.size(); i++) {
			if(listaContactos.get(i).getTelefono().getTelefono().equals(contacto.getTelefono().getTelefono())) {
				j++;
			    }
		    }
	    }
	if(j == 1){
		throw new OperacionNoPermitidaExcepcion("Error: Numero de telefono ya estaba registrado");
	    }
	else if(j == 0) {
		listaContactos.add(contacto);
	    }    
	} catch (OperacionNoPermitidaExcepcion e){
	    System.err.println(e);
	}
    }

    /**
     *  Modificacion sobre el telefono de un contacto
     * ya existente.
     *
     *@param telefono de tipo TelefonoInterfaz
     *@param nuevoTelefono de tipo TelefonoInterfaz
     *@throws OperacionNoPermitidaExcepcion si el numero de telefono no existe
     */
    public void modificarTelefono(TelefonoInterfaz telefono,TelefonoInterfaz nuevoTelefono) throws OperacionNoPermitidaExcepcion{
	int x=0;
	try{
	    for(int i=0; i<listaContactos.size(); i++){
		if(listaContactos.get(i).getTelefono().getTelefono().equals(telefono.getTelefono())){
		    listaContactos.get(i).setTelefono(nuevoTelefono);
		    x++;
		}
		
	    }
	    if (x==0){
		throw new OperacionNoPermitidaExcepcion("El telefono a modificar no se encuentra registrado en la agenda");
	    }
	} catch(OperacionNoPermitidaExcepcion onp){
	    System.err.println(onp);
	}
    }

    /**
     *  Eliminamos un contacto que esta registrado en la agenda.
     *
     *@param telefono de tipo TelefonoInterfaz
     *@throws OperacionNoPermitidaExcepcion si el telefono a eliminar no esta
     * registrado en la agenda
     */
    public void eliminarContacto(TelefonoInterfaz telefono) throws OperacionNoPermitidaExcepcion{
	try{
	    int x=0;
	    for(int i=0;i<listaContactos.size();i++){
		if(listaContactos.get(i).getTelefono().getTelefono().equals(telefono.getTelefono())){
		    ContactoInterfaz contacto =listaContactos.get(i);
		    listaContactos.remove(contacto);
		    x++;
		}
	    }
	    if(x==0){
		throw new OperacionNoPermitidaExcepcion("El telefono a borrar no esta registrado en la agenda");
	    } 
	} catch(OperacionNoPermitidaExcepcion onp) {
		System.err.println(onp);
	    }

	
    }

    /**
     * Muestra por salida estandar los contactos registrados 
     * en la agenda. Siguiendo el orden de: Apellidos,
     * Nombre, Telefono.
     */ 
    public void mostrarContactos() {
	for (ContactoInterfaz contacto: listaContactos){
	    System.out.println(contacto.getPersona().getPrimerApellido()+" "+contacto.getPersona().getSegundoApellido()+" "+contacto.getPersona().getNombre()+" "+contacto.getTelefono().getTelefono()+" "+contacto.getCorreoElectronico().getCorreoElectronico());
	}
    }

    /**
     * Ordena los contactos registrados en la agenda
     * tomando como criterio el orden alfabetico
     * de los apellidos
     */
    public void ordenarContactosPorNombre(){
	Comparator<ContactoInterfaz> comparar = new OrdenacionNombre();
	Collections.sort(listaContactos,comparar);
    }
    
    public void ordenarContactosPorCorreo(){
    	Comparator<ContactoInterfaz> comparar = new OrdenacionCorreo();
    	Collections.sort(listaContactos,comparar);
        }
}
