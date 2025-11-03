<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link REL=STYLESHEET
      HREF="${pageContext.request.contextPath}/JspStyles.css"
      TYPE="text/css">
<title>Content Admin Page</title>

</head>


<body>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
<div align=center>
<h2>Content Admin Login</h2>
Welcome <%=request.getAttribute("userName") %> <br>

<a href="IndexDisplay.jsp">Display Movies</a>
&nbsp;
&nbsp;
<a href="ScreeningDisplay.jsp">  Display Screenings</a>  
</div>   


</body>
</html>