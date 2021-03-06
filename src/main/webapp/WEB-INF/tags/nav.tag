<%@ tag language="java" pageEncoding="UTF-8"%>
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
<title>Login</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<header class="pb-2">
<nav class="navbar navbar-expand-md fixed-top navbar-dark bg-dark border border-success border-5">
  <div class="container-fluid">
    <h4 class= "text-white">Fintech</h4>
    <div class="collapse navbar-collapse" id="navbarCollapse">
    	<ul class="nav navbar-nav mr-auto">
       		<li class="nav-item active px-3">
        		<a class="nav-link " aria-current="page" href="/dashboard">Dashboard</a>
        	</li>
        	<li class="nav-item active px-3">
        		<a class="nav-link " aria-current="page" href="/chart">Graph</a>
        	</li>
      	</ul>
      	<ul class="nav navbar-nav ">
       		<li class="nav-item">
          		<a class="nav-link " aria-current="page" href="/logout">Logout</a>
       	 	</li>
      	</ul>  
      	</div>
    </div>
    </nav>
<jsp:doBody/>
</header>
</body>
</html>
       		