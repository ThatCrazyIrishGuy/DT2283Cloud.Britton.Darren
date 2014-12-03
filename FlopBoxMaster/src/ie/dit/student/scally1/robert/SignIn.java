package ie.dit.student.scally1.robert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

public class SignIn extends HttpServlet
{
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private String emailAddress = null;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		DisplayWebPage webObj = new DisplayWebPage(res);
		
		// sets up a writer to display text on screen
		PrintWriter writer = res.getWriter();
		
		UserService userService = UserServiceFactory.getUserService();
		
		Principal myPrincipal = req.getUserPrincipal();
		
		// get current url address
		String thisURL = req.getRequestURI();
		
		// using api to login / logout using gmail credentials
		String loginURL = userService.createLoginURL(thisURL);
		String logoutURL = userService.createLogoutURL(thisURL);
		
		// set content type
		res.setContentType("text/html");
		
		// display webpage header
		webObj.displayWebpageComponent(res, "header.jsp");
		
		// if user is not logged in
		if(myPrincipal == null)
		{
			writer.println("<p>You are not logged in</p>");
			writer.println("<p>You can <a href=\""+loginURL+"\">sign in here</a>.</p>");
		} // end if not logged in
		
		// if user is logged in
		if(myPrincipal != null)
		{
			// stores the users email address
			emailAddress = myPrincipal.getName();
			
			writer.println("<p>You are logged in as (email): "+emailAddress+"</p>");
			writer.println("<p>You can <a href=\""+logoutURL+"\">sign out</a>.</p>");
			
		} // end if logged in
		
		// display webpage footer
		webObj.displayWebpageComponent(res, "footer.jsp");
	 }
}
