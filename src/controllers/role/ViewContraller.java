package controllers.role;

import java.io.IOException;
import javax.servlet.http.*;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controllers.factura.PMF;
import models.factura.*;

	@SuppressWarnings("serial")
	public class ViewContraller extends HttpServlet {
		public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			resp.setContentType("text/html");
			
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(Factura.class);

			//query.setOrdering("nombre descending");
			// query for the entities by name
			
			
			List<Factura>array = (List<Factura>)query.execute("select from Factura");
			 
			req.setAttribute("array",array);
			try {
				try {
					req.getRequestDispatcher("/WEB-INF/view/view.jsp").forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				query.closeAll();
			
			}
			finally {
				pm.close();
			}
		}
	}


