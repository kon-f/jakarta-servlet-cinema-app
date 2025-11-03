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
<title>Display Movies for Content Admins</title>
</head>

<% //In case, if C_Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Cadmin")== null) ) {
%>
<jsp:forward page="../index.jsp"></jsp:forward>
<%} %>

<body>

<div style="text-align: left"> <button type="button" name="back" onclick="history.back()">back</button> </div>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
<table border=1>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Content</th>
                <th>Length</th>
                <th>Type</th>
                <th>Summary</th>
                <th>Content Admin ID</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.Movies}" var="s">
                <tr>
                    <td><c:out value="${s.id}" /></td>
                    <td><c:out value="${s.name}" /></td>
                    <td><c:out value="${s.content}" /></td>
                    <td><c:out value="${s.length}" /></td>
                    <td><c:out value="${s.type}" /></td>
                    <td><c:out value="${s.summary}" /></td>
                    <td><c:out value="${s.cadmin_id}" /></td>
                     <td><a href="movie?action=edit&ID=<c:out value="${s.id}"/>">Edit Movie</a></td>
                    <td><a href="movie?action=DisplayMovieScreenings&ID=<c:out value="${s.id}"/>">Display Movie Screenings</a></td>
                    <td><a href="movie?action=delete&ID=<c:out value="${s.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="movie?action=insert">Add movie</a></p>
    
</body>
</html>