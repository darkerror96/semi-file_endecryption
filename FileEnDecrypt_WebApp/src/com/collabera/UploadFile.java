package com.collabera;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 
 * @author rutpatel
 *
 */
@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean isMultipart;
	private String filePath = "/Users/rutpatel/Documents/Rut/Collabera_JuMP/AES_WebApp/Uploaded_Files/";
	private int maxFileSize = 10 * 1048576;
	private int maxMemSize = 10 * 1048576;
	private File file;
	private String key, radioBtn, fileName;

	public UploadFile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sess = request.getSession();

		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);

		if (!isMultipart) {
			sess.setAttribute("fileFlag", "No File uploaded...");
			request.getRequestDispatcher("aesHome.jsp").forward(request, response);
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);

		// Location to save data that is larger than maxMemSize
		factory.setRepository(new File("/Users/rutpatel/Documents/Rut/Collabera_JuMP/AES_WebApp/Temp/"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// maximum file size to be uploaded
		upload.setSizeMax(maxFileSize);

		try {
			// Parse the request to get file items
			List<FileItem> fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator<FileItem> i = fileItems.iterator();

			while (i.hasNext()) {

				FileItem fi = (FileItem) i.next();

				if (fi.isFormField()) {

					if (fi.getFieldName().equals("key")) {
						key = fi.getString();
					}

					if (fi.getFieldName().equals("radioBtn")) {
						radioBtn = fi.getString();
					}
				} else {
					// Get the uploaded file parameters
					fileName = fi.getName();
					file = new File(filePath + fileName);
					fi.write(file);

					System.out.println("'" + fileName + "' Uploaded...");
				}
			}

			String fileOriginal = new String(Files.readAllBytes(Paths.get(filePath + fileName)));
			response.setContentType("text/html");

			if (radioBtn.equals("0")) {
				AES.encrypt(fileOriginal, key).toPath();

//				byte[] outBytes = Files.readAllBytes(AES.encrypt(fileOriginal, key).toPath());
//	 			response.getOutputStream().write(outBytes);
//				response.getOutputStream().flush();

				sess.setAttribute("fileFlag", "'" + fileName + "' Encrypted...");
			} else {

				AES.decrypt(fileOriginal, key).toPath();

//				byte[] outBytes = Files.readAllBytes(AES.decrypt(fileOriginal, key).toPath());
//				response.getOutputStream().write(outBytes);
//				response.getOutputStream().flush();

				sess.setAttribute("fileFlag", "'" + fileName + "' Decrypted...");
			}

			request.getRequestDispatcher("aesHome.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
			sess.setAttribute("fileFlag", "Invalid Encryption File Selected...");
			request.getRequestDispatcher("aesHome.jsp").forward(request, response);
		}
	}
}
