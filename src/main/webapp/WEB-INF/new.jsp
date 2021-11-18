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
<title>Trade</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<t:nav></t:nav>
<t:header></t:header>

<div class = "container mt-5">
<p>${errors}</p>
<h2 class="text-center">${currency.name}</h2>

<form:form class="border px-3 pt-4" method="POST" action="/${currency.id}/new" modelAttribute="transaction">

<div class="form-group row">

	<form:label class="col-sm-2 col-form-label" path="count">Coins</form:label>
	<form:errors path="count"/>
	 <div class="col-sm-10">
	<form:select type="text" class="form-control" path="count">
		<c:forEach var = "i" begin = "1" end = "10">
    		<form:option value = "${i}">${i} </form:option>
    	</c:forEach>
    </form:select>
    </div>
</div>
<div class="form-group row">
	<form:label class="col-sm-2 col-form-label" path="currentRate">Current Price</form:label>
	<form:errors path="currentRate"/>
	 <div class="col-sm-10">
	<form:input  class="form-control" path="currentRate" value="${price}" readOnly="true"/>
	</div>
</div>
<div class = "form-group row">
	<form:label class="col-sm-2 col-form-label" path="transactionType">transactionType</form:label>
	<form:errors path="transactionType"/>
	 <div class="col-sm-10">
	<form:select type="text" class="form-control" path="transactionType">
		<c:forEach items="${ transaction.possibleTransaction }" var="priorityOption">
		<form:option value="${ priorityOption }">${ priorityOption }</form:option>
    </c:forEach>
    </form:select>
    </div>
</div>
<div class= "d-flex flex-row-reverse">
	<p><button class="btn btn-success">Submit</button></p>
	</div>
</form:form>
</div>
</body>
</html>
       		