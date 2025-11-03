<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link REL=STYLESHEET
      HREF="${pageContext.request.contextPath}/JspStyles.css"
      TYPE="text/css">
<title>Add Screening</title>
</head>

<% //In case, if C_Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Cadmin")== null) ) {
%>
<jsp:forward page="../index.jsp"></jsp:forward>
<%} %>

<body>

<div style="text-align: left"> <button type="button" name="back" onclick="history.back()">back</button></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>

    <form method="POST" action='screening' name="frmEditScreening">
    <input type="hidden" name="action" value="edit" />
        Screening ID : <input type="text"  name="ScreeningID" readonly="readonly"
            value="<c:out value="${Screenings.screeningID}" />" /> <br /> 
        Movie ID : <input type="text"   name="MovieID" readonly="readonly"
            value="<c:out value="${Screenings.movieID}" />" /> <br /> 
        Movie Name : <input
            type="text" name="MovieName" readonly="readonly"
            value="<c:out value="${Screenings.movieName}" />" /> <br /> 
        Cinema ID : <input
            type="text" name="CinemaID"
            value="<c:out value="${Screenings.cinemaID}" />" /> <br /> 
        Content Admin ID: <input 
            type="text" name="ContentAdminID" 
            value="<c:out value="${Screenings.contentAdminID}" />" /> <br />
        Screening Date : <input 
            type="text" name="Date"
            value="<c:out value="${Screenings.date}" />" /> <br />
        Available Seats : <input 
            type="text" name="SeatsAvailable"
            value="<c:out value="${Screenings.seatsAvailable}" />" /> <br />
        Screening Hour : <input 
            type="text" name="Time"
            value="<c:out value="${Screenings.time}" />" /> <br />
        <input  type="submit" value="Submit" />
    </form>
</body>
</html>