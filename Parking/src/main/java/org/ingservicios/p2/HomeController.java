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
	
	
	//Se esperan recibir dos par�metros en los datos de la petici�n parkingid,matricula

	/*Para serializar los datos de respuesta, en la declaraci�n del m�todo
asociado al @RequestMapping se incorpora la etiqueta @ResponseBody.
Adem�s, el m�todo no devuelve un String, sino un objeto de otro tipo que
ser� formateado autom�ticamente en JSON
  */
	
	//PathVariable solo se usa cuando se a�ade algo en la url
	 @RequestMapping(method=RequestMethod.POST, value="/registroMatricula/enviar")
	public String registroMatricula(@RequestBody DTOParking park ) {
		String url="testparking";
	    //1 entrada
	if(dao.buscaMatricula(park.getMatricula())==null && dao.buscaIdpark(park.getParkingId())==null)	{
	//	DTOParking park =  new DTOParking();
		dao.addCoche(park);//a�adimos a la bbdd
		//Salida 1
	}else if(dao.buscaMatricula(park.getMatricula())!=null && park.getParkingId()==1&& dao.buscaIdpark(park.getParkingId())==null) {
			dao.addCoche(park);//a�adimos a la bbdd	
			url="testcoste";
			//otra entrada
		}else if(dao.buscaMatricula(park.getMatricula())!=null && dao.buscaIdpark(park.getParkingId())!=null && park.getParkingId()==0) {
			dao.updateCoche(park);
			//otra salida
		}else {
			
			dao.updateCoche(park);
			url="testcoste";
			
		}
		ResponseEntity<DTOParking> resp=new ResponseEntity <DTOParking> (park,HttpStatus.CREATED);
		
		
		
		
        return url;
	
	}
	 
	 
	 //PARTE OPCIONAL
	// coste/{matricula} 
@RequestMapping(value="coste/{matricula}",method=RequestMethod.GET)
public @ResponseBody long coste(@PathVariable (value="matricula")String matricula) {
	
	//Timestamp tsalida = dao.tsalida(matricula);
	//Timestamp tentrada = dao.tentrada(matricula);//obtener bbdd
	long tiempo=0;
	//tiempo=((tsalida.getTime()-tentrada.getTime())/1000) ;
	long Tarifa=(long) 0.3456;
	long coste= tiempo * Tarifa ;
	
	return coste;
}
	
}
