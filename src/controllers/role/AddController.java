package controllers.role;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import models.factura.*;

import javax.jdo.PersistenceManager;

/*import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar	;
import java.util.GregorianCalendar;
import java.util.TimeZone;	+'*/


public class AddController extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		try {
			req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	   
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		/*TimeZone tz = TimeZone.getTimeZone ("America / Lima");
		Calendar calendar=new GregorianCalendar(tz);	
		
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		   String da=dateformat.format(calendar.getTime()).toString();*/
		
		String customer = req.getParameter("nombre");
		String pension = req.getParameter("descripcion");
		Double price = Double.parseDouble(req.getParameter("monto"));
		String address = req.getParameter("direccion");
	

		PersistenceManager pm = PMF.get().getPersistenceManager();
	
		Factura fac = new Factura(customer, pension, price, address);
		
		try{
			pm.makePersistent(fac);
			}
		finally{
			pm.close();
		}
			resp.sendRedirect("/view");
	}
}

