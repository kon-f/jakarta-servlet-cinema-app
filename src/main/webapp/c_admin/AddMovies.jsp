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
<title>Add Movies</title>
</head>

<% //In case, if C_Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Cadmin")== null) ) {
%>
<jsp:forward page="../index.jsp"></jsp:forward>
<%} %>

<body>

<div style="text-align: left"> <button type="button" name="back" onclick="history.back()">back</button></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>

    <form method="POST" action='movie' name="frmAddMovie">
    <input type="hidden" name="action" value="insert" />
        ID : <input type="text"   name="ID"
            value="<c:out value="${Movie.id}" />" /> <br /> 
        Name : <input
            type="text" name="Name"
            value="<c:out value="${Movie.name}" />" /> <br /> 
        Content : <input
            type="text" name="Content"
            value="<c:out value="${Movie.content}" />" /> <br /> 
        Length : <input 
            type="text" name="Length"
            value="<c:out value="${Movie.length}" />" /> <br />
        Type : <input 
            type="text" name="Type"
            value="<c:out value="${Movie.type}" />" /> <br />
        Summary : <input 
            type="text" name="Summary"
            value="<c:out value="${Movie.summary}" />" /> <br />
        Content Admin ID : <input 
            type="text" name="Content_admin_Id"
            value="<c:out value="${Movie.cadmin_id}" />" /> <br />
        <input  type="submit" value="Submit" />
    </form>
</body>
</html>