package org.ingservicios.p2;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
//Indica que el bean es un dao
@Repository
public class DAOParking implements DAOParkingInterfaz {
	//Añadir libreria spring-jdbc y dependencia junto a la versión
	public JdbcTemplate jdbcTemplate;
	//private DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
		public List<DTOParking> muestraParking(){
			String sql = "select * from parking";
			ParkingMapper mapper = new ParkingMapper();
			List<DTOParking> par = this.jdbcTemplate.query(sql,mapper);
			return par;
		}
		
		
		public DTOParking buscaMatricula(String mat){ //Devuelve el coche buscado o null si no existe
			String sql = "select * from parking where Matricula = ?";
			Object[ ] parametros = {mat}; //Array de objetos
			ParkingMapper mapper = new ParkingMapper();
			List<DTOParking> park = this.jdbcTemplate.query(sql, parametros, mapper);
			if (park.isEmpty()) return null;
			else return park.get(0);
			}
		
		
	
		
		//Añadir parking, en jdbcTemplate se utiliza ??
		public void addCoche(DTOParking park) {			
			String sql = "insert into parking(ParkingId,Matricula) values(?,?)";
			Object[ ] parametros = {park.getParkingId(),park.getMatricula()}; //Array de objetos
			//Para operaciones INSERT, UPDATE o DELETE se usa el método jdbcTemplate.update
			this.jdbcTemplate.update(sql,parametros);
		}
		public void updateCoche(DTOParking park){
			String sql = "update parking SET ParkingId = ?, Matricula = ? ,TimeStamp =? where ParkingId=?";
			//Obtenemos fecha actual
			Calendar calendar = Calendar.getInstance ();
			Timestamp currentTimestamp = new java.sql.Timestamp (calendar.getTime (). getTime ());
			Object[ ] parametros = {park.getParkingId(),park.getMatricula(),currentTimestamp,park.getParkingId()}; //Array de objetos
			//Para operaciones INSERT, UPDATE o DELETE se usa el método jdbcTemplate.update
			this.jdbcTemplate.update(sql,parametros);
		}
		
		public DTOParking buscaIdpark(int id){ //Devuelve el coche buscado o null si no existe
			String sql = "select * from parking where ParkingId= ?";
			Object[ ] parametros = {id}; //Array de objetos
			ParkingMapper mapper = new ParkingMapper();
			List<DTOParking> park = this.jdbcTemplate.query(sql, parametros, mapper);
			if (park.isEmpty()) return null;
			else return park.get(0);
		}
		public DTOParking buscaIdpark(int id,String matricula){ 
			String sql = "select * from parking where ParkingId= ? and Matricula=?";
			Object[ ] parametros = {id,matricula}; //Array de objetos
			ParkingMapper mapper = new ParkingMapper();
			List<DTOParking> park = this.jdbcTemplate.query(sql, parametros, mapper);
			if (park.isEmpty()) return null;
			else return park.get(0);
		}
			
			//Obtener tiempo de salida
			public Timestamp tsalida(String matricula, int parkingID){
				String sql = "select * from parking where ParkingID = ? AND Matricula = ?";
				Object[] parametros = {parkingID, matricula}; 
				ParkingMapper mapper = new ParkingMapper();
				List<DTOParking> park = this.jdbcTemplate.query(sql, parametros, mapper);
				if (park.isEmpty()) return null;
				else return park.get(0).getFecha();
				}
			
			//Obtener tiempo de entrada
			public Timestamp tentrada(String matricula, int parkingID){
				String sql = "select * from parking where ParkingID = ? AND Matricula = ?";
				Object[] parametros = {parkingID, matricula};
				ParkingMapper mapper = new ParkingMapper();
				List <DTOParking> park= this.jdbcTemplate.query(sql, parametros, mapper);
				if (park.isEmpty()) return null;
				else return park.get(0).getFecha();
				}		
		
}

