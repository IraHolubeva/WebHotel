<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/View/css/fontawesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/View/css/bootstrap.css">
    <link href="${pageContext.request.contextPath}/View/css/mainView.css" rel="stylesheet" type="text/css">
    <meta charset="ISO-8859-1">
</head>
<body>

<div class="backLayer">

    <h1 class="title">St. Ermin's Hotel</h1>

    <form class="headForm" action="HotelMainServlet">

        <div class="dateInput">
            <span class="input-group-text" id="inputGroup-sizing-default">Check_in</span>
            <input id="datepicker1" id="date1" name="check-in_date" width="276"/>

            <span class="input-group-text" id="inputGroup-sizing-default">Check_Out</span>
            <input id="datepicker2" name="check-out_date" width="276"/>


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

            <script>
                current_date = new Date();
                month = current_date.getMonth() + 1;
                day = current_date.getDate() + 1;
                year = current_date.getFullYear();

                if (day < 10) {
                    day = "0" + day;
                }

                if (month < 10) {
                    month = "0" + month;
                }

                datepicker1.value = month + "/" + day + "/" + year;

                datepicker2.value = month + "/" + day + "/" + year;
            </script>

        </div>

        <div class="addData">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Guests</span>
                </div>
                <input type="number" min="1" value="1" max="6" name="guests_amount" class="form-control"
                       aria-label="Default" aria-describedby="inputGroup-sizing-default">
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Price</span>
                </div>
                <input type="number" min="30" value="30" name="price" class="form-control" aria-label="Default"
                       aria-describedby="inputGroup-sizing-default">
                <span class="input-group-text" id="inputGroup-sizing-default">$</span>
            </div>
        </div>

        <div class="formBtn">
            <button type="submit" class="btn btn-secondary" name="action" value="search">Search</button>
        </div>
    </form>
</div>

<div class="mainContent">

    <table class="table">
        <thead class="thead-purple">
        <th scope="col">Number</th>
        <th scope="col">Floor</th>
        <th scope="col">Guests</th>
        <th scope="col">Price per day</th>
        <th scope="col">Booking</th>
        </thead>

        <c:forEach var="listItem" items="${roomsList}">
            <tr class="tr-content">
                <td><c:out value="${listItem.getRoomNumber()}"/></td>
                <td><c:out value="${listItem.getFloor()}"/></td>
                <td><c:out value="${listItem.getGuests()}"></c:out></td>
                <td><c:out value="${listItem.getPricePerDay()}"></c:out></td>
                <td>
                    <c:choose>
                        <c:when test="${listItem.isFree() == true}">
                            <c:out value="room is free"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="room is booked"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <%-- <td>
                    <form> action="HotelMainServlet">
                        <button type="submit">Booking</button>
                    </form>
                </td> --%>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
