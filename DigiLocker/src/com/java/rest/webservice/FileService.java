package com.java.rest.webservice;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.java.DAO.MyFileDAO;
import com.java.Helper.Encryption;
import com.java.Helper.GetFileExtension;
import com.java.Helper.SessionAttribute;
import com.java.exception.MyExceptionHandler;
import com.java.model.FileModel;
import com.java.model.ResponseMessage;
import com.java.resource.property.PropertyLoader;
import com.oreilly.servlet.MultipartRequest;

public class FileService {

	private boolean result;
	private String output;
	final static Logger logger = Logger.getLogger(FileService.class);
	private String fileName = "";

	public ResponseMessage uploadFile(HttpServletRequest request)
			throws MyExceptionHandler {
		String fileLocation = "";
		String fileName = "";
		try {
			// saving file on server.
			MultipartRequest m = new MultipartRequest(request, PropertyLoader
					.getPropertyValue("dcryptedfilepath"));

			// get File Name Receive
			Enumeration files = m.getFileNames();
			while (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				fileName = m.getFilesystemName(name);

			}

			fileLocation = PropertyLoader.getPropertyValue("dcryptedfilepath")
					+ fileName;
			
			//getting username from session.
			SessionAttribute sess=new SessionAttribute();
			String username=sess.getUsernameFormSession(request);
			
			// getting parameter values into FileModel object.
			FileModel f1 = new FileModel();
			f1.setFileName(m.getParameter("filename"));
			f1.setUserID(username);
			f1.setDescription(m.getParameter("file_description"));
			
			//getting categoryid from database.
			MyFileDAO dao=new MyFileDAO();
			int id=dao.getCategoryID(m.getParameter("category"));
			
			f1.setCategory_ID(id);			
			f1.setFileType(GetFileExtension.getFileExtension(fileLocation));// getting
			// file_extension.

			FileService service = new FileService();// object of FileService
			// class.
			if (m.getParameter("secure") != null) {
				f1.setSecure("yes");
				String oldFileLocation = fileLocation;
				// encrypting file.
				Encryption enc = new Encryption();
				fileLocation = enc.encryptFile(fileLocation, f1.getFileName());

				// deleting old file from server.
				service.delete(oldFileLocation);

			} else {
				f1.setSecure("no");
			}

			// file uploading in database.
			MyFileDAO d = new MyFileDAO();
			result = d.uploadFile(fileLocation, f1);

			// deleting file from server.
			service.delete(fileLocation);

		} catch (IOException e) {
			logger.error("Error during uploading file.", e);
			// throw new MyExceptionHandler("IOException", "uploadFile");
			e.printStackTrace();
		}
		if (result == true) {
			output = "File successfully uploaded!";
		} else {
			output = "Uploading Failed!";
		}
		ResponseMessage res = new ResponseMessage();
		res.setStatusCode(200);
		res.setMessage(output + " " + fileLocation);
		return res;
	}

	public String downloadFile(HttpServletRequest request) {

		//getting username from session.
		SessionAttribute sess=new SessionAttribute();
		String username=sess.getUsernameFormSession(request);
		
		FileModel f1 = new FileModel();

		f1.setFileName(request.getParameter("FileName"));
		f1.setUserID(username);
		f1.setCategory_ID(Integer.parseInt(request.getParameter("CategoryID")));

		MyFileDAO d = new MyFileDAO();
		f1 = d.viewFile(f1);// extracting all details of file.

		String path = d.downloadFile(f1);

		fileName = f1.getFileName() + "." + f1.getFileType();

		return path;

	}

	public void delete(String FileLocation) {
		File file = new File(FileLocation);
		file.delete();
	}

	public String getFileName() {

		return fileName;
	}

}