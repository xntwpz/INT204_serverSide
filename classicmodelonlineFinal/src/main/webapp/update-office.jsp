<!DOCTYPE html>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Office Manager</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/office-list.jsp">Office Manager</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="office-list">Office List</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container my-5">
    <h2 class="mb-4">Update Office</h2>
    <form action="update-office" method="post" class="border rounded p-4">
        <div class="mb-3">
            <label for="officeCode" class="form-label">Office Code *</label>
            <input type="text" class="form-control" id="officeCode" name="officeCode"
                   value="${requestScope.office.officeCode}" readonly>
        </div>

        <div class="mb-3">
            <label for="addressLine1" class="form-label">Address Line 1</label>
            <input type="text" class="form-control" id="addressLine1" name="addressLine1"
                   value="${requestScope.office.addressLine1}">
        </div>

        <div class="mb-3">
            <label for="addressLine2" class="form-label">Address Line 2</label>
            <input type="text" class="form-control" id="addressLine2" name="addressLine2"
                   value="${requestScope.office.addressLine2}">
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="city" class="form-label">City</label>
                <input type="text" class="form-control" id="city" name="city" value="${requestScope.office.city}">
            </div>
            <div class="col-md-6">
                <label for="state" class="form-label">State</label>
                <input type="text" class="form-control" id="state" name="state" value="${requestScope.office.state}">
            </div>
        </div>

        <div class="mb-3">
            <label for="country" class="form-label">Country</label>
            <input type="text" class="form-control" id="country" name="country" value="${requestScope.office.country}">
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="postalCode" class="form-label">Postal Code</label>
                <input type="text" class="form-control" id="postalCode" name="postalCode"
                       value="${requestScope.office.postalCode}">
            </div>
            <div class="col-md-6">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" value="${requestScope.office.phone}">
            </div>
        </div>

        <div class="mb-3">
            <label for="territory" class="form-label">Territory</label>
            <input type="text" class="form-control" id="territory" name="territory"
                   value="${requestScope.office.territory}">
        </div>

        <div class="d-grid gap-2">
            <button class="btn btn-success" type="submit">
                Update Office
            </button>
        </div>
    </form>
</div>
</body>
</html>