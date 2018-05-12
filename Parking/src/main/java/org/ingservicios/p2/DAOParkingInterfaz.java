package org.ingservicios.p2;

import java.sql.Timestamp;
import java.util.List;


public interface DAOParkingInterfaz {
	public List<DTOParking> muestraParking();
	public DTOParking buscaMatricula(String mat);
	public void addCoche(DTOParking park);
	public void updateCoche(DTOParking park);
	public DTOParking buscaIdpark(int id);
	public Timestamp tsalida(String mat);
	public Timestamp tentrada(String mat);
	
}
