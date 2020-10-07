<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>
    List of Todos
</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	    		rel="stylesheet">
</head>

<body>

   <div class="container">
    <table class="table table-striped">
      <caption>Your Todos are</caption>
      <thead>
        <tr>
            <td>Description</td>
	        <td>Targate Date</td>
	        <td>Is it done?</td>
	        <td></td>
	        <td></td>
        </tr>
      </thead>
      
      <tbody>
         <c:forEach items="${todo}" var="todo">
	        <tr>
	          <td>${todo.desc}</td>
	          <td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
	          <td>${todo.done}</td>
	           <td><a class="btn btn-success"type="button" href="/update-todo?id=${todo.id}">Update</a></td>
	          <td><a class="btn btn-warning"type="button" href="/delete-todo?id=${todo.id}">Delete</a></td>
	        </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <div> <a class="button" href="/add-todo">Add a Todo</a></div>
    
  		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </div>
</body>
</html>