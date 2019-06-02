<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <meta charset="UTF-8">

  <title>Hotel</title>

  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js" type="text/javascript"></script>
  <link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/View/css/fontawesome.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/View/css/bootstrap.css">
  <link href="${pageContext.request.contextPath}/View/css/mainView.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <div class="backLayer">

    <div class="info">
      <ul>
        <li id="sign_in"><a href="#">Sign in</a></li>
        <li id="sign_up"><a href="#">Sign up</a></li>
      </ul>
    </div>

  <h1 class="title">St. Ermin's Hotel</h1>

 <div class="dateInput">
  <form action="HotelMainServlet">
    <input type = "hidden" name="action" value="check_date" />
    <h3>check-in</h3><input id="datepicker1"  name="check-in_date"  width="276"/>
    <h3>check-out</h3><input id="datepicker2" name="check-out_date" width="276"/>
    <input type="submit" class="btn btn-dark" value="Find" />
    <script>
    	$('#datepicker1').datepicker({
      	uiLibrary: 'bootstrap4'
    	});
    </script>
    <script>
    	$('#datepicker2').datepicker({
      uiLibrary: 'bootstrap4'
    });
    </script>
 </form>
 </div>
 <div class="menu">
  <ul>
    <li><img src="css/img/menu.png" class = "image">Menu
      <ul class="subMenu">
        <li>Rooms</li>
        <li>Relax</li>
        <li>Restaraunt</li>
      </ul>
    </li>
  </ul>
 </div>
 </div>

<table>
	<c:forEach var="listItem" items="${roomsList}">
		<tr>
			<td><c:out value="${listItem}"></c:out></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
