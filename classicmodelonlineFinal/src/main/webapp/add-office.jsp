<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Office</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .navbar {
            background-color: #ffffff;
        }

        .form-container {
            max-width: 500px;
            margin: auto;
            margin-top: 50px;
            border: 1px solid #ced4da; /* Bootstrap border color */
            border-radius: 0.25rem; /* Bootstrap border radius */
            padding: 20px;
        }

        .form-container h1 {
            text-align: center;
            margin-bottom: 30px;
        }

        .form-container button {
            width: 100%;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="office-list">Office Manager</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="office-list">Office List</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container form-container">
    <h1>Add Office</h1>
    <form action="add-office" method="post">
        <input type="hidden" name="action" value="add">

        <div class="mb-3">
            <label for="officeCode" class="form-label">Office Code</label>
            <input type="text" class="form-control" id="officeCode" name="officeCode" required>
        </div>

        <div class="mb-3">
            <label for="city" class="form-label">City</label>
            <input type="text" class="form-control" id="city" name="city" required>
        </div>

        <div class="mb-3">
            <label for="state" class="form-label">State</label>
            <input type="text" class="form-control" id="state" name="state" required>
        </div>

        <div class="mb-3">
            <label for="territory" class="form-label">Territory</label>
            <input type="text" class="form-control" id="territory" name="territory" required>
        </div>

        <div class="mb-3">
            <label for="postalCode" class="form-label">PostalCode</label>
            <input type="text" class="form-control" id="postalCode" name="postalCode" required>
        </div>

        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" class="form-control" id="phone" name="phone" required>
        </div>

        <div class="mb-3">
            <label for="addressLine1" class="form-label">Address Line 1</label>
            <input type="text" class="form-control" id="addressLine1" name="addressLine1" required>
        </div>

        <div class="mb-3">
            <label for="country" class="form-label">Country</label>
            <input type="text" class="form-control" id="country" name="country" required>
        </div>

        <div class="mb-3">
            <button class="btn btn-success" type="submit">Add Office</button>
        </div>
    </form>
</div>
</body>
</html>