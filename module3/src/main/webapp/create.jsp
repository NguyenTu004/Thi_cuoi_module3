<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 12/8/2022
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="bootstrap-5.2.2-dist/css/bootstrap.min.css">
  <script src="bootstrap-5.2.2-dist/js/bootstrap.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
  <style>
    input {
      width: 90%;
    }
    a{
      text-decoration: none;
    }
  </style>
</head>
<body>
  <div class="container">
    <h1>Create employee</h1>
    <form class="col-md-8" action="${pageContext.request.contextPath}/adminServlet?action=create" method="post">
      <table class="table table-hover">
        <tr>
          <th>Name</th>
          <th><input  type="text" name="name"></th>
        </tr>
        <tr>
          <th>Email</th>
          <th><input type="text" name="email"></th>
        </tr>
        <tr>
          <th>Address</th>
          <th><input type="text" name="address"></th>
        </tr>
        <tr>
          <th>PhoneNumber</th>
          <th><input type="text" name="phone"></th>
        </tr>
        <tr>
          <th>Salary(USD)</th>
          <th><input type="number" name="salary"></th>
        </tr>
        <tr>
          <th>Department</th>
          <th>
            <label style="width: 100%;">
              <select name="department">
                <c:forEach items="${listDepartment}" var="d">
                  <option value="${d.getId()}"><c:out value="${d.getName()}"/></option>
                </c:forEach>
              </select>
            </label>
          </th>
        </tr>
        <tr>
          <th>
            <button type="submit" class="btn btn-primary">Create</button>
            <button type="button" class="btn btn-outline-dark"><a href="${pageContext.request.contextPath}/adminServlet">Exit</a></button>
          </th>
          <th></th>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>
