<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="bg-light">
    <div class="container my-5">
        <div class="row bg-primary text-white py-3">
            <h2> Classic Model Offices</h2>
        </div>

        <div class="row mt-3">
            <form id="searchForm" action="office-list" method="get" class="d-flex">
                <input type="text" id="searchInput" name="cityOrCountry" class="form-control me-2" placeholder="Search">
                <button type="button" class="btn btn-info" onclick="submitForm()">Search</button>
            </form>
        </div>

        <div class="row mt-4">
            <c:forEach items="${offices}" var="office">
                <div class="col-md-4 mb-4">
                    <div class="card ${office.officeCode == selectedOffice.officeCode ? 'border-warning' : 'border-secondary'}">
                        <div class="card-body">
                            <h5 class="card-title">Office :: ${office.officeCode}</h5>
                            <p class="card-text"><strong>Location:</strong>
                                <a href="javascript:loadOffice('${office.officeCode}')">${office.city}</a>, ${office.country}
                            </p>
                            <p class="card-text"><strong>Phone:</strong> ${office.phone}</p>
                        </div>
                        <div class="card-footer d-flex justify-content-between">
                            <form action="office-list" method="post">
                                <input type="hidden" name="delete" value="${office.officeCode}">
                                <button class="btn btn-danger"><i class="fas fa-trash-alt"></i> Delete</button>
                            </form>
                            <a href="update-office?officeCode=${office.officeCode}">
                                <button class="btn btn-primary"><i class="fas fa-edit"></i> Update</button>

                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="row mt-4">
            <a href="add-office" class="btn btn-dark">Add Office</a>
        </div>

        <div class="row mt-5">
            <c:forEach items="${selectedOffice.employeeList}" var="employee">
                <div class="col-md-4 mb-5">
                    <div>
                        ${employee.firstName} ${employee.lastName} - ${employee.jobTitle}
                    </div>
                </div>

            </c:forEach>
        </div>
    </div>
</div>