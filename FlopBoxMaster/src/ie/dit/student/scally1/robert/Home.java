package ie.dit.student.scally1.robert;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Home extends HttpServlet
{
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private String header = "header.jsp";
	private String footer = "footer.jsp";
	private PrintWriter writer;
	private String key;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		DisplayWebPage webObj = new DisplayWebPage(res);
	
		UserService userService = UserServiceFactory.getUserService();
	
		Principal myPrincipal = req.getUserPrincipal();
		
		// get current url address
		String thisURL = req.getRequestURI();
		
		// using api to login / logout using gmail credentials
		String loginURL = userService.createLoginURL(thisURL);
		String logoutURL = userService.createLogoutURL(thisURL);
		
		// set content type of the page
		res.setContentType("text/html");
		
		writer = res.getWriter();
		
		// if user is logged in, display users email address and logout option
		if(myPrincipal != null)
		{
			writer.println("<div id=\"login_bar\">Hi " + myPrincipal.getName()+"&nbsp;&nbsp;<a href=\""+logoutURL+"\">Sign Out</a></div>");
		}
		// else if user is not signed in display the option to login
		else
		{
			writer.println("<div id=\"login_bar\">Hi Guest &nbsp;&nbsp;<a href=\""+loginURL+"\">Sign In</a></div>");
		}
		
		// display webpage header
		webObj.displayWebpageComponent(res, header);
		
		// list which will be used to store blob info
		List<BlobInfo> filesList = new LinkedList<BlobInfo>(); 
		
		// iterator to store queried blob info
		Iterator<BlobInfo> iterator = new BlobInfoFactory().queryBlobInfos();
		
		// proceed through iterator containing the blob query result and store each in the files list
		while(iterator.hasNext()) 
		  filesList.add(iterator.next()); 
		
		// index used in the loop 
		int i = 0;
		
		// display beginning of html table
		writer.println("<table><tr class=\"top\"><td>name</td><td>filetype</td><td>size</td><td></td></tr>");
		
		
		// loop through files information returned from query
		for(BlobInfo blobInfo : filesList)
		{	
			// retrieves blob key as a string with added characters which are not required
			key = filesList.get(i).getBlobKey().toString();
			
			// extract the key from returned key string
			key = key.substring(10, key.length()-1);
			
			// display a row with blob filename and info inside the html table
			writer.println("<tr><td>"+filesList.get(i).getFilename()+"</td><td>"+filesList.get(i).getContentType()+"</td><td>"+filesList.get(i).getSize()+"</td><td><input onClick=\"location.href='download?blob-key="+key+"'\" type=\"button\" value=\"Download\" /></td></tr>");
			i++;
		}
		
		// display end of html table
		writer.println("</table>");
		
		// display webpage footer
		webObj.displayWebpageComponent(res, footer);
	}
}
