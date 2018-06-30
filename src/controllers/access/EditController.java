package controllers.access;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.factura.PMF;
import models.factura.*;
@SuppressWarnings("serial")
public class EditController  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			
			String codigo = req.getParameter("codigo");
			Long codigoLong = Long.parseLong(codigo); 
			Factura fac = pm.getObjectById(Factura.class,codigoLong);
			req.setAttribute("factura",fac);
			req.getRequestDispatcher("/WEB-INF/view/edit.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			pm.close();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
			//Realizar la persistencia
		String codigo = req.getParameter("codigo");
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		Double monto =Double.parseDouble(req.getParameter("monto")); 
		String direccion = req.getParameter("direccion");
		String estado=req.getParameter("estado");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Long codigoLong = Long.parseLong(codigo);
			
			Factura fac = pm.getObjectById(Factura.class,codigoLong);
			fac.setCustomer(nombre);
			fac.setPension(descripcion);
			fac.setPrice(monto);
			fac.setAddress(direccion);
			if (estado.equals("c")) {
				fac.setStatus(true);
				
			}else if(estado.equals("p")){
				fac.setStatus(false);
				}else{
				fac.setStatus(true);
			}
			fac.setDate();			
		
			
		}
		catch(Exception e){
			System.out.println("Se produjo un Error");
		}
		finally{
			pm.close();
		}
			resp.sendRedirect("/view");
	}
}

