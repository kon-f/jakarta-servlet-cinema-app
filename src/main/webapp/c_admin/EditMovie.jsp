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
<title>Insert title here</title>
</head>

<% //In case, if C_Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Cadmin")== null) ) {
%>
<jsp:forward page="../index.jsp"></jsp:forward>
<%} %>

<body>

<div style="text-align: left"> <button type="button" name="back" onclick="history.back()">back</button></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>

    <form method="POST" action='movie' name="frmEditMovie">
    <input type="hidden" name="action" value="edit" />
        ID : <input type="text"   name="ID" readonly="readonly"
            value="<c:out value="${Movies.id}" />" /> <br /> 
        Name : <input
            type="text" name="Name"
            value="<c:out value="${Movies.name}" />" /> <br /> 
        Content : <input
            type="text" name="Content"
            value="<c:out value="${Movies.content}" />" /> <br /> 
        Length : <input 
            type="text" name="Length"
            value="<c:out value="${Movies.length}" />" /> <br />
        Type : <input 
            type="text" name="Type"
            value="<c:out value="${Movies.type}" />" /> <br />
        Summary : <input 
            type="text" name="Summary"
            value="<c:out value="${Movies.summary}" />" /> <br />
        Content Admin ID : <input 
            type="text" name="Content_admin_Id"
            value="<c:out value="${Movies.length}" />" /> <br />
        <input  type="submit" value="Submit" />
    </form>
</body>
</html>