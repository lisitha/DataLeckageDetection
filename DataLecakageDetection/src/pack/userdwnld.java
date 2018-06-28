package pack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AppUtil;

public class userdwnld extends HttpServlet {
	
	SimpleFTPClient client;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try {

			String path = "C:\\Users\\lisit\\Desktop\\appdownloads\\"; //chanage the path
			client = new SimpleFTPClient();
			client.setHost("ftp.drivehq.com");
			client.setUser("liveieee");
			client.setPassword("Nagasrinu");
			client.setRemoteFile(request.getParameter("file"));

			client.connect();
			
			if (client.downloadFile(path+request.getParameter("file"))) {

				FileInputStream fis=new FileInputStream(path+request.getParameter("file"));
				
				String source=path+"new"+request.getParameter("file");
				
				FileOutputStream fos=new FileOutputStream(source);
				
				try {
					SimpleFTPClient.decrypt(fis, fos);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				fos.flush();
				
				fos.close();
				
				Random random=new Random();
				
				String keysource="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
				
				String key="";
				
				for(int i=0;i<10;i++)
				{
					key=key+keysource.charAt(random.nextInt(60));
				}
				
				String destination=path+request.getParameter("owner")+"-"+request.getParameter("file");
				
				System.out.println(source);
				System.out.println(destination);
				System.out.println(key);
			
				int result=0;
				
				try {
					
					AppUtil.manipulatePdf(source, destination,key);
					
					Connection conn = DbConnector.getConnection();
					Statement st = conn.createStatement();
					
					result=st.executeUpdate("insert into consumerfiles values('"+0+"','"+request.getParameter("owner")+"-"+request.getParameter("file")+"','"+(String)request.getSession().getAttribute("me")+"','"+request.getParameter("owner")+"','"+key+"')");
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				if(result==1)
				{
					response.setContentType("text/html");  

					response.setContentType("APPLICATION/OCTET-STREAM");   

					response.setHeader("Content-Disposition","attachment; filename=\"" + request.getParameter("file") + "\"");   

					FileInputStream fileInputStream = new FileInputStream(destination);  

					int i;   
					
					while ((i=fileInputStream.read()) != -1) {  
						out.write(i);   
					}   
					
					fileInputStream.close();   
					
					out.close();   
				}
				else
				{
					response.sendRedirect("userpage.jsp?dwnn= not sucess..!");
				}
				
			} else {
				response.sendRedirect("userpage.jsp?dwnn= not sucess..!");
			}

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
