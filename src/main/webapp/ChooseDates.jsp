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

    <form method="POST" action='screening' name="frmChooseDates">
    <input type="hidden" name="action" value="DateRange" />
        Starting Date : <input type="text"   name="StartDate"
            value="<c:out value="${startDate}" />" /> <br /> 
        Ending Date : <input
            type="text" name="EndDate"
            value="<c:out value="${endDate}" />" /> <br />        
        <input  type="submit" value="Submit" />
    </form>
</body>
</html>
