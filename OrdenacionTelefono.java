package fp2.poo.pfpooadrdelvil1;

import java.util.Comparator;

import fp2.poo.utilidades.ContactoInterfaz;

public class OrdenacionTelefono implements Comparator<ContactoInterfaz>{
	public int  compare(ContactoInterfaz contacto1, ContactoInterfaz contacto2){
		String contacto01 = contacto1.getTelefono().getTelefono();
		String contacto02 = contacto2.getTelefono().getTelefono();
	
		return contacto01.compareTo(contacto02);
	}
}
