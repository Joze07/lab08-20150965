package controllers.role;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;


import controllers.factura.PMF;
import models.factura.*;
@SuppressWarnings("serial")

public class DeleteController extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		String codigo = req.getParameter("codigo");
		Long codigoLong = Long.parseLong(codigo);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Query query = pm.newQuery(Factura.class);
			query.setFilter("codigo == codigoParam");
			query.declareParameters("Long codigoParam");
			query.deletePersistentAll(codigoLong);
			query.closeAll();
		}
		finally{
			pm.close();
		}
		resp.sendRedirect("/view");
	}

}

