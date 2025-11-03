<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link REL=STYLESHEET
      HREF="${pageContext.request.contextPath}/JspStyles.css"
      TYPE="text/css">
<title>Display Screenings</title>
</head>

<% //In case, if customer session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Customer")== null) ) {
%>
<jsp:forward page="../index.jsp"></jsp:forward>
<%} %>

<body>

<div style="text-align: left"> <button type="button" name="back" onclick="history.back()">back</button> </div>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
<table border=1>
        <thead>
            <tr>
                <th>Movie Name</th>
                <th>Cinema ID</th>
                <th>Screening Date</th>
                <th>Available Seats</th>
                <th>Screening Hour</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.Screenings}" var="s">
                <tr>
                    <td><c:out value="${s.movieName}" /></td>
                    <td><c:out value="${s.cinemaID}" /></td>
                    <td><c:out value="${s.date}" /></td>
                    <td><c:out value="${s.seatsAvailable}" /></td>
                    <td><c:out value="${s.time}" /></td>   
                    <td><a href="booking?action=insert&screeningID=<c:out value="${s.screeningID}"/>">Book tickets for this screening</a></td>               
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
</body>
</html>