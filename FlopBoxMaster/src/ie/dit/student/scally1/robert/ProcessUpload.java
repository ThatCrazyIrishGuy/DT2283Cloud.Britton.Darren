package ie.dit.student.scally1.robert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ProcessUpload extends HttpServlet 
{
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		@SuppressWarnings("deprecation")
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		
		// get file uploaded from form with the name myFile
		BlobKey blobKey = blobs.get("myFile");
	
		
		// if no file uploaded, then redirect to base url
		if (blobKey == null) 
		{
		         res.sendRedirect("/");
		} 
		// else if file uploaded
		else 
		{
			// print to system the blobkey of the uploaded file
		    System.out.println("Uploaded a file with blobKey: "+blobKey.getKeyString());
		  
		    // redirect to home page
		    res.sendRedirect("/");
		}	
	}
}
