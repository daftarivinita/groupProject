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
${currency.name}
<form:form method="POST" action="/${currency.id}/new" modelAttribute="transaction">
<div class="form-group row">
	<form:label path="currency.count">Number Of Coin ${currency.name}</form:label>
	<form:errors path="currency.count"/>
	<form:select type="text" class="form-control" path="currency.count">
		<c:forEach var = "i" begin = "1" end = "5">
    		<form:option value = "${currency.id}">${i} </form:option>
    	</c:forEach>
    </form:select>
</div>
<div class="form-group row">
	<form:label path="price">Current Price</form:label>
	<form:errors path="price"/>
	<form:input type="number" class="form-control" path="price"/>
</div>

<div class = "form-group row">
	<form:label path="transactionType">transactionType</form:label>
	<form:errors path="transactionType"/>
	<form:select type="text" class="form-control" path="transactionType">
		<c:forEach items="${ transaction.possibleTransaction }" var="priorityOption">
		<form:option value="${ priorityOption }">${ priorityOption }</form:option>
    </c:forEach>
    </form:select>
</div>

	<p><button class="btn btn-success">Submit</button></p>
</form:form>
</body>
</html>