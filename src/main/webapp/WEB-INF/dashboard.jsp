<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix= "t" tagdir="/WEB-INF/tags" %>
 <!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix= "t" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<t:nav></t:nav>
<t:header></t:header>
<div class = "container">
<h1>Welcome User, ${user.firstName}</h1>
<h2>Portfolio Balance $<fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${balance}" /></h2>
<table class = "table">
   <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>Balance</th>
            <th>Price</th>
            <th>Shares</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
       <tr>
         <c:forEach items="${currency}" var="entry"> 
         <c:if test ="${entry.key.name == 'USD'}">
            <td><c:out value="${entry.key.name}"/></td>
            <td>$<fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${entry.value}" /></td>
           <td></td>
            <td></td>
            <td></td>
             </c:if>
            </c:forEach>
            </tr>
        <tr>
        <c:forEach items="${currency}" var="entry"> 
         <c:if test ="${entry.key.name == 'Amazon'}">
            <td class="d-flex flex-column justify-content-center"> <c:out value="${entry.key.name}"/><img src= "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThFAiYdR6nm7111dmJC3Oto7pd7afgzEAkTQ&usqp=CAU" alt ="amazon" width ="200" height="200" /></td>
            <td><c:out value="${entry.value*amazon}"/></td>
           <td>${amazon}</td>
            <td><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${entry.value}" /></td>
           <td><a href="/<c:out value="${entry.key.id}"/>/new">Trade</a></td>
         </c:if>
        </c:forEach>
            </tr>
            <tr>
            <c:forEach items="${currency}" var="entry"> 
         <c:if test ="${entry.key.name == 'Tesla'}">
            <td class="d-flex flex-column"><c:out value="${entry.key.name}"/><img src= "data:image/png;base64,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" alt ="tesla" width ="200" height="200" /></td>
            <td><c:out value="${entry.value*tesla}"/></td>
           <td>${tesla} </td>
            <td><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${entry.value}" /></td>
           <td><a href="/<c:out value="${entry.key.id}"/>/new">Trade</a></td>
             </c:if>
            </c:forEach>
     </tr>
    </tbody>
</table>
        
            
       
         

</div>

</body>
</html>