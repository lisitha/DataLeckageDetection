package pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
public class upload1 extends HttpServlet {

	SimpleFTPClient client;
	File file;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();

		String my = request.getSession().getAttribute("me").toString();

		try {

			Connection con = DbConnector.getConnection();
			
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			
			diskFileItemFactory.setRepository(file);
			diskFileItemFactory.setSizeThreshold(1 * 1024 * 1024);

			ServletFileUpload newHUpload = new ServletFileUpload(diskFileItemFactory);
			List items = newHUpload.parseRequest(request);
			
			Iterator iterator = items.iterator();
			FileItem fileItem = (FileItem) iterator.next();

			PreparedStatement pstm = null;
			
			client = new SimpleFTPClient();
			client.setHost("ftp.drivehq.com");
			client.setUser("liveieee");
	        client.setPassword("Nagasrinu");
			client.setRemoteFile(fileItem.getName());

			boolean log = client.connect();

			Random r = new Random();
			int ii = r.nextInt(10000000-500000)+500000;
			String k = ii+"";
			String l = "1";

			Iterator itr = items.iterator();
			
			String sql = "insert into files (fileid,name,rank_,key_,userid,filedata)values(null,?,?,?,?,?)";

			pstm = con.prepareStatement(sql);

			FileItem item = (FileItem) itr.next();
			
			System.out.println("getD "+item.getName());

			String itemName=item.getName();

			ResultSet rs=con.createStatement().executeQuery("select count(*) from files where name='"+itemName+"'");


			boolean isHaving=false;

			while(rs.next())
			{
				isHaving=true;
			}

			if(isHaving)
			{
				System.out.println("in if---------");

				pstm.setString(1,itemName);
				pstm.setString(2,l );
				pstm.setString(3,k );
				pstm.setString(4,my);
				pstm.setString(5,"");
;				pstm.execute();

				HttpSession session = request.getSession(true);
				session.setAttribute("nn", k);
				System.out.println("Values inserted");

				if (log) {
					
					InputStream myis=fileItem.getInputStream();
					
					String extension=null;
					
					int i = itemName.lastIndexOf('.');
					if (i > 0) {
					    extension = itemName.substring(i+1);
					}
					
					String path="C:\\Users\\lisit\\Desktop\\appfiles\\original."+extension;
					
					FileOutputStream myos=new FileOutputStream(path);
					
					try {
						SimpleFTPClient.encrypt(myis, myos);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if (client.uploadFile(new FileInputStream(path))) {
						
						response.sendRedirect("setkeyword.jsp?msg= sucess..!");
					} else {
						response.sendRedirect("adminpage.jsp?msgg= NOT sucess..!");
					}
				} else {
					out.println("not connected");
				}

			}
			else
			{
				pstm.setString(1,itemName);
				pstm.setString(2,l );
				pstm.setString(3,k );
				pstm.setString(4,my);
				pstm.execute();

				HttpSession session = request.getSession(true);
				session.setAttribute("nn", k);
				System.out.println("Values inserted");

				response.sendRedirect("setkeyword.jsp?msg= sucess..!");
				//response.setHeader("Content-Disposition", "attachment; filename=" +fileItem.getName());

			}

		} catch (SQLException ex) {
			Logger.getLogger(upload1.class.getName()).log(Level.SEVERE, null, ex);
		} catch (FileUploadException ex) {
			Logger.getLogger(upload1.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
