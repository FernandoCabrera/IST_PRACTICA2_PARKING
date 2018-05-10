package org.ingservicios.p2;


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
			String sql = "update parking SET ParkingId = ?, Matricula = ?";
			
			Object[ ] parametros = {park.getParkingId(),park.getMatricula()}; //Array de objetos
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
		//buscar matricula salida 
		public DTOParking tsalida(String mat){ //Devuelve el coche buscado o null si no existe
			String sql = "select TimeStamp from parking where Matricula= ? and ParkingId=1";
			Object[ ] parametros = {mat}; //Array de objetos
			ParkingMapper mapper = new ParkingMapper();
			List<DTOParking> park = this.jdbcTemplate.query(sql, parametros, mapper);
			if (park.isEmpty()) return null;
			else return park.get(0);
			}
		//buscar matricula entrada 
				public DTOParking tentrada(String mat){ //Devuelve el coche buscado o null si no existe
					String sql = "select TimeStamp from parking where Matricula= ? and ParkingId=0";
					Object[ ] parametros = {mat}; //Array de objetos
					ParkingMapper mapper = new ParkingMapper();
					List<DTOParking> park = this.jdbcTemplate.query(sql, parametros, mapper);
					if (park.isEmpty()) return null;
					else return park.get(0);
					}
		
}

