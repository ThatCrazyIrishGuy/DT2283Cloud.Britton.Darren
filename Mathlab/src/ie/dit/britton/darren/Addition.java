package ie.dit.britton.darren;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Addition extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		String AString = req.getParameter("A");
		String BString = req.getParameter("B");
		
		Double a = 0.0,b = 0.0,ans = 0.0;
		
		try
		{
			a = Double.parseDouble(AString);
		}
		catch(Exception e)
		{
			resp.getWriter().println(e.toString());
		}
		
		try
		{
			b = Double.parseDouble(BString);
		}
		catch(Exception e)
		{
			resp.getWriter().println(e.toString());
		}
		
		try
		{
			ans = a + b;
		}
		catch(Exception e)
		{
			resp.getWriter().println(e.toString());
		}
		
		resp.getWriter().println("Answer = "+ ans);
		
		
	}
}
