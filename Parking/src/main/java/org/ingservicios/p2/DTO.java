package org.ingservicios.p2;

import java.io.Serializable;

public class DTO implements Serializable{
	public DTO(int parkingId, String matricula) {
		
		this.parkingId = parkingId;
		this.matricula = matricula;
	}
	
public DTO() {
		
		this.parkingId = -1;
		this.matricula = "";
	}
	
	private int parkingId;
	private String matricula;
	public int getParkingId() {
		return parkingId;
	}

	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	

}
