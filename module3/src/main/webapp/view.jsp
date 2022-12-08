<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 12/8/2022
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="bootstrap-5.2.2-dist/css/bootstrap.min.css">
    <script src="bootstrap-5.2.2-dist/js/bootstrap.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
    <style>
        a{
            text-decoration: none;
            color: white
        }
    </style>
</head>
<body>
<div class="container">
    <div class="col col-md-12">
        <div class="col col-md-4">
            <h1>List employee</h1>
            <button class="btn btn-primary"><a href="${pageContext.request.contextPath}/adminServlet?action=add">Create employee</a></button>
            <p style="color: red"><c:out value="${notification}"/></p>
        </div>
        <div class="col col-md-6">
            <form action="${pageContext.request.contextPath}/adminServlet?action=search" method="post">
                <label>Search
                    <input type="text" name="search" placeholder="name....">
                </label>
                <button type="submit" class="btn-outline-primary">Search</button>
            </form>
        </div>
    </div>
    <table class="table table-hover">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>PhoneNumber</th>
            <th>Salary(USD)</th>
            <th>Department</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${listStaff}" var="s">
            <tr>
                <td><c:out value="${s.getId()}"/></td>
                <td><c:out value="${s.getName()}"/></td>
                <td><c:out value="${s.getEmail()}"/></td>
                <td><c:out value="${s.getAddress()}"/></td>
                <td><c:out value="${s.getPhone()}"/></td>
                <td><c:out value="${s.getSalary()}"/></td>
                <td><c:out value="${listMapDepartment.get(s.getDepartmentId()).getName()}"/></td>
                <td>
                    <button style="background-color: #ef3232"><a data-bs-toggle="modal" href="#exampleModalToggle" role="button">Delete</a></button>
                    <button style="background-color: #4343f5"><a href="${pageContext.request.contextPath}/adminServlet?action=update&id=${s.getId()}">update</a></button>
                </td>
            </tr>
            <div class="modal fade" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalToggleLabel">Delete</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Are you sure you want to delete <c:out value="${s.getName()}"/>?
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-danger"><a href="${pageContext.request.contextPath}/adminServlet?action=delete&id=${s.getId()}">Xóa</a></button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </table>
</div>
</body>
</html>
