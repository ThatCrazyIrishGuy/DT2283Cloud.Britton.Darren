package ie.dit.britton.darren;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LabServlet4_1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		resp.setContentType("text/plain");
		
	}
	
	public String displayTextFile()
	{
		BufferedReader input = null;
		
		try{
			input = new BufferedReader(new FileReader(new File ("sample.txt")));
		}
		catch(Exception e)
		{
			return "File not Found";
		}
		
		String returnString = "";
		
		String line;
		
		try{
			while ((line = input.readLine()) != null)
				returnString += ("\n"+line);
			input.close();
		}
		catch (Exception e)
		{
			
		}
		return returnString;
	}
}
