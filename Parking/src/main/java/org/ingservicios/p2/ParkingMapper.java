package org.ingservicios.p2;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;




public class ParkingMapper implements RowMapper <DTOParking>{
public DTOParking mapRow(ResultSet rs, int rowNum) throws SQLException{
DTOParking park = new DTOParking();
park.setParkingid(rs.getInt("ParkingId"));//nombre en la tabla sql
park.setMatricula(rs.getString("Matricula"));//nombre en la tabla sql
park.setFecha(rs.getTimestamp("Timestamp"));//nombre en la tabla sql
return park;
}
}