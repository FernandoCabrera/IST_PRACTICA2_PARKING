package org.ingservicios.p2;

import java.util.List;


public interface DAOParkingInterfaz {
	public List<DTOParking> muestraParking();
	public DTOParking buscaMatricula(String mat);
	public void addCoche(DTOParking park);
	public void updateCoche(DTOParking park);
	public DTOParking buscaIdpark(int id);
	
}
