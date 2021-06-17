package fp2.poo.pfpooadrdelvil1;
import java.util.*;

import fp2.poo.utilidades.ContactoInterfaz;

public class OrdenacionDomicilio implements Comparator <ContactoInterfaz> {
	public int compare (ContactoInterfaz contacto1, ContactoInterfaz contacto2) {
		String contacto01 = contacto1.getDomicilio().getDomicilio();
		String contacto02 = contacto2.getDomicilio().getDomicilio();
		
		return contacto01.compareTo(contacto02);
	}
	}
