package ie.dit.britton.darren;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Multiplication extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		String AString; //= req.getParameter("A");
		String BString; //= req.getParameter("B");
		
		// get the default A parameter from the Deployment Descriptor
		AString = getServletConfig().getInitParameter("A");
		// get the B parameter from the url which called the servlet
		// get the default B parameter from the Deployment Descriptor
		BString = getServletConfig().getInitParameter("B");
		
		Integer a = 0,b = 0,ans = 0;
		
		try
		{
			a = Integer.parseInt(AString);
		}
		catch(Exception e)
		{
			resp.getWriter().println("input A was NaN or too long");
		}
		
		try
		{
			b = Integer.parseInt(BString);
		}
		catch(Exception e)
		{
			resp.getWriter().println("input B was NaN or too long");
		}
		
		try
		{
			ans = a * b;
		}
		catch(Exception e)
		{
			resp.getWriter().println("Answer was too long to be displayed or some other error...");
		}
		
		resp.getWriter().println("Answer = "+ ans);
		
	}
}
