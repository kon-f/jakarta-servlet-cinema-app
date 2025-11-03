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
<title>Add Content Admins</title>
</head>

<% //In case, if Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Admin")== null) ) {
%>
<jsp:forward page="../index.jsp"></jsp:forward>
<%} %>

<body>
<div style="text-align: left"> <button type="button" name="back" onclick="history.back()">back</button></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>

 <form method="POST" action='admin' name="frmAddCAdmin">
    <input type="hidden" name="action" value="editc" />
        Content Admin ID : <input type="text"   name="ID"
            value="<c:out value="${User.id}" />" /> <br /> 
        Content Admin Name : <input
            type="text" name="Name"
            value="<c:out value="${User.name}" />" /> <br /> 
        Content Admin Username: <input
            type="text" name="Username" readonly="readonly"
            value="<c:out value="${User.username}" />" /> <br /> 
        <input  type="submit" value="Submit" />
    </form>

</body>
</html>