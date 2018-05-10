package org.ingservicios.p2;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	DAOParkingInterfaz dao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		
		return "testparking";
	}
	
	
	//Se esperan recibir dos parámetros en los datos de la petición parkingid,matricula

	/*Para serializar los datos de respuesta, en la declaración del método
asociado al @RequestMapping se incorpora la etiqueta @ResponseBody.
Además, el método no devuelve un String, sino un objeto de otro tipo que
será formateado automáticamente en JSON
  */
	//PathVariable solo se usa cuando se añade algo en la url
	 @RequestMapping(method=RequestMethod.POST, value="/registroMatricula/enviar")
	public ResponseEntity< DTOParking> registroMatricula(@RequestBody DTOParking park ) {
//		public ResponseEntity< DTOParking> registroMatricula(@RequestBody DTO coche) {
	    System.out.println("Dentro de servicio");
		//Obtenemos fecha
		//Timestamp date = (Timestamp) new Date();
	    //1 entrada
	if(dao.buscaMatricula(park.getMatricula())==null && dao.buscaIdpark(park.getParkingId())==null)	{
	//	DTOParking park =  new DTOParking();
		dao.addCoche(park);//añadimos a la bbdd
		//Salida 1
	}else if(dao.buscaMatricula(park.getMatricula())!=null && park.getParkingId()==1&& dao.buscaIdpark(park.getParkingId())==null) {
			dao.addCoche(park);//añadimos a la bbdd	
			//url="";
			//otra entrada
		}else if(dao.buscaMatricula(park.getMatricula())!=null && dao.buscaIdpark(park.getParkingId())!=null && park.getParkingId()==0) {
			dao.updateCoche(park);
			//otra salida
		}else {
			
			dao.updateCoche(park);
			//url="";
			
		}
		
		
		
		
		
        return new ResponseEntity<DTOParking>(park,HttpStatus.CREATED);
	
	}
	
}
