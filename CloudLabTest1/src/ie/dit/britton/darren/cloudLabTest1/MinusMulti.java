package ie.dit.britton.darren.cloudLabTest1;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MinusMulti extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		String AString = req.getParameter("A");
		String BString = req.getParameter("B");
		String CString = req.getParameter("C");
		String DString = req.getParameter("D");
		
		Integer a = 0,b = 0,c = 0, d = 0,ans = 0;
		
		try
		{
			a = Integer.parseInt(AString);
		}
		catch(Exception e)
		{
			resp.getWriter().println(e.toString());
		}
		
		try
		{
			b = Integer.parseInt(BString);
		}
		catch(Exception e)
		{
			resp.getWriter().println(e.toString());
		}
		
		try
		{
			c = Integer.parseInt(CString);
		}
		catch(Exception e)
		{
			resp.getWriter().println(e.toString());
		}
		
		try
		{
			d = Integer.parseInt(DString);
		}
		catch(Exception e)
		{
			resp.getWriter().println(e.toString());
		}
		
		try
		{
			ans = (a - b) * (c - d);
		}
		catch(Exception e)
		{
			resp.getWriter().println(e.toString());
		}
		
		resp.getWriter().println("Answer = "+ ans);
		
	}
}
