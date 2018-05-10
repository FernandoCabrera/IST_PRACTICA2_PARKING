package org.ingservicios.p2;

import java.io.Serializable;
import java.sql.Timestamp;

public class DTOParking implements Serializable {
//Datos de nuestra tabla
	
	private int parkingId;//identificador barrera 0-entrada 1-salida
	private String matricula;
	private Timestamp fecha;
	
	
public DTOParking() {
		
		this.parkingId = 0;
		this.matricula = "";
		this.fecha = null;
	}


	public DTOParking(int parkingid, String matricula, Timestamp fecha) {
		
		this.parkingId = parkingid;
		this.matricula = matricula;
		this.fecha = fecha;
	}


	public int getParkingId() {
		return parkingId;
	}


	public void setParkingId(int parkingid) {
		this.parkingId = parkingid;
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