package org.ingservicios.p2;

import java.io.Serializable;
import java.sql.Timestamp;

public class DTOParking implements Serializable {
//Datos de nuestra tabla
	
	private int parkingid;//identificador barrera 0-entrada 1-salida
	private String matricula;
	private Timestamp fecha;
	
	
public DTOParking() {
		
		this.parkingid = 0;
		this.matricula = "";
		this.fecha = null;
	}


	public DTOParking(int parkingid, String matricula, Timestamp fecha) {
		
		this.parkingid = parkingid;
		this.matricula = matricula;
		this.fecha = fecha;
	}


	public int getParkingid() {
		return parkingid;
	}


	public void setParkingid(int parkingid) {
		this.parkingid = parkingid;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public Timestamp getFecha() {
		return fecha;
	}


	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	

}