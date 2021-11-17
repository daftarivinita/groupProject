<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<nav class="navbar">
      <div class="container">
	<ul class="navbar-nav mb-0 mb-lg-0">
		<li><strong>Welcome ${user.firstName} ${user.lastName}</strong></li>
		<li><strong>Portfolio Balance $<fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${balance}" /></strong></li>
	</ul>
	<a href="/chart"><img src="https://cdn.onlinewebfonts.com/svg/img_424736.png" alt="" width="80" heigth="50"></a>
	<h3>Dashboard Fintech Application</h3>
	<a href="/logout"><img src="https://cdn4.iconfinder.com/data/icons/proglyphs-computers-and-development/512/Logout-128.png" alt="" width="80" heigth="50"></a>

    </div>
 </nav>
<div class="container">
<img src="/img/stock3.jpg" class="responsive">
<table class="table">
    <thead class="table-dark">
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
         <c:if test ="${entry.key.name == 'Amazon'}">
            <td class="d-flex flex-column justify-content-center"> <c:out value="${entry.key.name}"/><img src="https://investorplace.com/wp-content/uploads/2016/01/amazon-amzn-logo.jpg" alt="" width="100" height="100"></td>
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
            <td class="d-flex flex-column"><c:out value="${entry.key.name}"/><img src="https://lh6.googleusercontent.com/proxy/nos8QlZmbfqlejFq3OciE_80JyBPyBSk7LVawYEA7LRnjS0-zOtS1ULPPV_Iv5kVjdaZdazue2--WlCAq15gKGnUNbKVINT9WIlQHO9lAexDkcG39sPkntF6Z3Zbls_-hbYcAThEN6-yNlF8lTQJHx-z6UDdEJKA2Hav321nLia3=w1200-h630-p-k-no-nu" alt="" width="100" height="100"></td>
            <td><c:out value="${entry.value*tesla}"/></td>
           	<td>${tesla} </td>
            <td><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${entry.value}" /></td>
           	<td><a href="/<c:out value="${entry.key.id}"/>/new">Trade</a></td>
           </c:if>
          </c:forEach>
      </tr>
      <tr>
        <c:forEach items="${currency}" var="entry"> 
         <c:if test ="${entry.key.name == 'USD'}">
            <td class="d-flex flex-column"><c:out value="${entry.key.name}"/><img src="https://i1.wp.com/www.finsavvypanda.com/wp-content/uploads/2019/01/how-to-get-free-money.jpg?fit=1000,734&ssl=1" alt="" width="100" height="100"></td>
            <td>$<c:out value="${entry.value}"/></td>
           	<td></td>
            <td></td>
            <td></td>
          </c:if>
         </c:forEach>
      </tr>
     </tbody>
     </table>
     </div>
</body>
</html>
