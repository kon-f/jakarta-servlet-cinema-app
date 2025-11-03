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
<title>Display Customers</title>
</head>

<% //In case, if Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Admin")== null) ) {
%>
<jsp:forward page="../index.jsp"></jsp:forward>
<%} %>

<body>
<div style="text-align: left"> <button type="button" name="back" onclick="history.back()">back</button> </div>
<div style="text-align: center"> Content Admins </div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
<table border=1>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Username</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.CAdmins}" var="s">
                <tr>
                    <td><c:out value="${s.id}" /></td>
                    <td><c:out value="${s.name}" /></td>
                    <td><c:out value="${s.username}" /></td> 
                     <td><a href="admin?action=edit&Username=<c:out value="${s.username}"/>">Demote to Customer</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    
</body>
</html>