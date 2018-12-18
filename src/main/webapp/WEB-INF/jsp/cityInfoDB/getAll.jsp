<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <title>JSP</title>
</head>
<body>

<div>
    <h2>JSP GET_ALL Page</h2>
    <c:forEach items="${getAll}" var="cityInfoDB">
      <div> ${cityInfoDB.name} </div>
    </c:forEach>
</div>
<div>
    <a href="/">back</a>
</div>

</body>
</html>