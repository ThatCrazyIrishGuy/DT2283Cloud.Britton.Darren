package ie.dit.student.scally1.robert;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class Upload extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private String header = "header.jsp";
	private String footer = "footer.jsp";
	private PrintWriter writer;
	String emailAddress = null;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		DisplayWebPage webObj = new DisplayWebPage(res);
		
		// set content type of the page
		res.setContentType("text/html");
		writer = res.getWriter();
		
		UserService userService = UserServiceFactory.getUserService();
		
		Principal myPrincipal = req.getUserPrincipal();
		
		// get current url address
		String thisURL = req.getRequestURI();
		
		// using api to login / logout using gmail credentials
		String loginURL = userService.createLoginURL(thisURL);
		String logoutURL = userService.createLogoutURL(thisURL);
		
		// if user is not logged in then redirect to the sign in page
		if(myPrincipal == null)
		{
			// redirect to sign in page
			res.sendRedirect("/signin");
		}
		else
		{
			// set email address to be that of current user
			emailAddress = myPrincipal.getName();
			
			// display users email address and logout option
			writer.println("<div id=\"login_bar\">Hi " +emailAddress+"&nbsp;&nbsp;<a href=\""+logoutURL+"\">Sign Out</a></div>");
		}
		
		// if email address is of the two users who have privileges to upload
		if(emailAddress.equalsIgnoreCase("rob.scally@gmail.com") || emailAddress.equalsIgnoreCase("mark.deegan.dit@gmail.com"))
		{	
			// display header of webpage
			webObj.displayWebpageComponent(res, header);
			
			// display form to upload file to user
			writer.println("<form action=\""+blobstoreService.createUploadUrl("/processupload")+"\" method=\"post\" enctype=\"multipart/form-data\"><input type=\"file\" name=\"myFile\"><input type=\"submit\" value=\"Submit\"></form>");
		
			// display footer of webpage
			webObj.displayWebpageComponent(res, footer);
		}		
		else
		{
			// display header of webpage
			webObj.displayWebpageComponent(res, header);
			
			// display warning to unauthorised user
			writer.println("<div id=\"warning\">"+emailAddress+" is Unauthorised to upload files</div>");
		
			// display footer of webpage
			webObj.displayWebpageComponent(res, footer);
		}
	}
}
