<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix= "t" tagdir="/WEB-INF/tags" %>
 <!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Welcome User, ${user.firstName}</h1>

<%-- <c:choose>
         
         <c:when test = "${user.porfolio}">
           
		<c:forEach items="${currency}" var="c">
				<a href= "/${c.id}/new">${c.name}</a>
    	</c:forEach>
         </c:when>
         
        
         
         <c:otherwise>
          <form:form method="POST" action="/createPortfolio" modelAttribute="portfolio">

	<p><button class="btn btn-success">Submit</button></p>
</form:form>
         </c:otherwise>
      </c:choose> --%>


<p>if user donot have portfolio give them a form to add portfolio to thier profile else show what they have in thier portfolio Table. since this is one to one relationship one user should not create more then one portfolio</p>
<form:form method="POST" action="/dashboard" modelAttribute="portfolio">

	<p><button class="btn btn-success">Invest With Us!</button></p>
</form:form>
<%-- <c:forEach items="${currency}" var="c">
				<p><a href= "/${c.id}/new">${c.name}</a> ${c.count }</p>
    	</c:forEach> --%>
</body>
</html>