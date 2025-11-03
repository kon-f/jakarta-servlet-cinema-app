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

    <form method="POST" action='screening' name="frmAddScreening">
    <input type="hidden" name="action" value="insert" />
        Screening ID : <input type="text"   name="ScreeningID"
            value="<c:out value="${Screening.screeningID}" />" /> <br /> 
        Movie ID : <input type="text"   name="MovieID"
            value="<c:out value="${Screening.screeningID}" />" /> <br /> 
        Movie Name : <input
            type="text" name="MovieName"
            value="<c:out value="${screening.movieName}" />" /> <br /> 
        Cinema ID : <input
            type="text" name="CinemaID"
            value="<c:out value="${Screening.cinemaID}" />" /> <br /> 
        Content Admin ID: <input 
            type="text" name="ContentAdminID"
            value="<c:out value="${Screening.contentAdminID}" />" /> <br />
        Screening Date : <input 
            type="text" name="Date"
            value="<c:out value="${Screening.date}" />" /> <br />
        Available Seats : <input 
            type="text" name="SeatsAvailable"
            value="<c:out value="${Screening.availableSeats}" />" /> <br />
        Screening Hour : <input 
            type="text" name="Time"
            value="<c:out value="${Screening.time}" />" /> <br />
        <input  type="submit" value="Submit" />
    </form>
</body>
</html>