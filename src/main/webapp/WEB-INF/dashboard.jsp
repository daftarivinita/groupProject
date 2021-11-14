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
<h2>We have to find how to do arithmetic calculation</h2>

<table class = "table table-striped table-dark table-bordered">
   <thead>
        <tr>
            <th>Name</th>
            <th>Balance</th>
            <th>Price</th>
            <th>Shares</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${currency}" var="entry"> 
        <tr>
            <td><c:out value="${entry.key.name}"/></td>
            
            <c:choose>
            <c:when test = "${entry.key.name == 'USD'}">
            <td><c:out value="${entry.value}"/></td>
           <td></td>
            <td></td>
            <td></td>
         </c:when>
         <c:when test = "${entry.key.name == 'Amazon'}">
         <td><c:out value="${entry.value}"/> * ${amazon}</td>
           <td>${amazon}</td>
            <td><c:out value="${entry.value}"/></td>
            <td><a href="/<c:out value="${entry.key.id}"/>/new">Trade</a></td>
         </c:when>
          
          <c:otherwise>
          <td><c:out value="${entry.value}"/></td>
          <td>${tesla }</td>
            <td><c:out value="${entry.value}"/></td>
            <td><a href="/<c:out value="${entry.key.id}"/>/new">Trade</a></td>
          </c:otherwise>
       </c:choose>
        </tr>
        </c:forEach>
    </tbody>
</table>


</body>
</html>