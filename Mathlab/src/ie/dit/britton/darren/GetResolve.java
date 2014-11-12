package ie.dit.britton.darren;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GetResolve extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		String ActionString = req.getParameter("Action");
		
		switch (ActionString) 
		{
			case "multiply":
				Multiplication multi = new Multiplication();
				multi.doGet(req, resp);
				break;
			case "divide":
				Division divi = new Division();
				divi.doGet(req, resp);
				break;
			case "subtract":
				Subtraction sub = new Subtraction();
				sub.doGet(req, resp);
				break;
			case "add":
				Addition add = new Addition();
				add.doGet(req, resp);
				break;
		}
		
		
	}
}
