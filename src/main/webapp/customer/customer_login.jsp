<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link REL=STYLESHEET
      HREF="${pageContext.request.contextPath}/JspStyles.css"
      TYPE="text/css">
<title>Customer Page</title>
</head>


<body>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
<div align=center>
<h2>Customer Login</h2>
Welcome <%=request.getAttribute("userName") %>
<br>
<br> 
<br> 
<a href="CusIndexDisplay.jsp">Display Movies</a>
<br> 
<br> 
<a href="CusAllScreenings.jsp">Display All Screenings</a>
<br> 
<br> 
<a href="ChooseDates.jsp">Display Screenings Between A Date Range</a>
<br> 
<br> 
<a href="CusReservationsDisplay.jsp">Display Bookings History</a>

</div>

</body>
</html>