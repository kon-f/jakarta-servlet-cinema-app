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
<title>Add Reservation</title>
</head>

<% //In case, if customer session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Customer")== null) ) {
%>
<jsp:forward page="../index.jsp"></jsp:forward>
<%} %>

<body>

<div style="text-align: left"> <button type="button" name="back" onclick="history.back()">back</button></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>

    <form method="POST" action='booking' name="frmAddBooking">
    <input type="hidden" name="action" value="insert" />
        Screening ID : <input type="text" readonly="readonly"  name="ScreeningID"
            value="<c:out value="${Booking.screeningID}" />" /> <br /> 
        Customer ID : <input
            type="text" name="CustomerID" readonly="readonly"
            value="<c:out value="${Booking.customerID}" />" /> <br /> 
        Number of seats : <input
            type="text" name="SeatsBooked"
            value="<c:out value="${Booking.seatsBooked}" />" /> <br /> 
        Reservation ID : <input 
            type="text" name="ReservationID" readonly="readonly"
            value="<c:out value="${Booking.reservationID}" />" /> <br />
            	<span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
        <input  type="submit" value="Submit" />
    </form>
    
</body>
</html>