<%@page import="pack.DbConnector"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<!DOCTYPE HTML>
<html>

<head>
<title></title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Tangerine&amp;v1" />
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz" />
<link rel="stylesheet" type="text/css" href="style/style.css" />

</head>

<body>
	<div id="main">
		<div id="header">
			<div id="logo">
				<div class="slogan">DataLeakageDetection</div>
			</div>
			<div id="menubar">
				<ul id="menu">
					<!-- put class="current" in the li tag for the selected page - to highlight which page you're on -->

					<li><a class="current" href="adminpage.jsp">Home</a></li>
					<li class="hvr-sweep-to-bottom"><a href="view.jsp">View
							Data Details</a></li>
					<li class="hvr-sweep-to-bottom"><a href="viewreq.jsp">View
							User Request</a></li>
					<li class="hvr-sweep-to-bottom"><a href="logout.jsp">Logout</a></li>

				</ul>
			</div>
		</div>
		<div id="site_content">
			<div id="content">

				<table class="imagetable">
					<tr>
						<th>REQUEST ID</th>
						<th>PUBLIC KEY</th>
						<th>USERNAME</th>
						<th>FILENAME</th>
						<th>Send</th>
						<th>CANCEL</th>
					</tr>
					<%
						String fname = null, r = null, k = null, t = null, key = null, cat = null;

						String owner = (String) request.getSession().getAttribute("me");

						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DbConnector.getConnection();
						Statement st1 = conn.createStatement();

						ResultSet rs1 = st1
								.executeQuery("select * from  request where owner='"
										+ owner + "'");

						while (rs1.next()) {

							fname = rs1.getString("id");
							r = rs1.getString("name");
							k = rs1.getString("user");
							t = rs1.getString("filename");
					%>
					<tr>
						<td><label><%=fname%></label> <input type="hidden" name="r"
							value="<%=fname%>"></td>

						<td><label><%=r%></label><input type="hidden" name="r"
							value="<%=r%>"></td>

						<td><label><%=k%></label><input type="hidden" name="k"
							value="<%=k%>"></td>

						<td><label><%=t%></label><input type="hidden" name="t"
							value="<%=t%>"></td>

						<td><a href="send.jsp?r=<%=r%>&k=<%=k%>&t=<%=t%>">Send
								Keys</a></td>

						<td><a href="delete.jsp?<%=fname%>">delete request</a></td>
					</tr>
					<%
						}
					%>
				</table>

			</div>
		</div>
		<div id="footer">
			<p>
				Copyright 2017 | <a href="http://www.voidmaintechnologies.com">info</a>
			</p>
		</div>
	</div>
</body>
</html>


