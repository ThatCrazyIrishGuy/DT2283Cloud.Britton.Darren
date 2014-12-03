package ie.dit.student.scally1.robert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class DisplayWebPage 
{
	private String header = "header.jsp";
	private String footer = "footer.jsp";
	private PrintWriter writer;
	
	public DisplayWebPage(HttpServletResponse res) throws IOException
	{
		writer = res.getWriter();
	}
	
	public int displayWebpageComponent(HttpServletResponse resp, String filename) 
	{
		String line;
		
		BufferedReader input = null;
		
		try 
		{
			input =  new BufferedReader(new FileReader(filename));
			
			while (( line = input.readLine()) != null) 
			{
				writer.println("\t"+line);
			}
			
			input.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
}
