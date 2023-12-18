<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Edit Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center justify-center h-screen">
<div class="w-full max-w-xs">
    <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" method="post" action="users">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="id" value="${requestScope.user.id}"/>
        <p class="text-gray-900 font-bold text-xl mb-2">Edit User (${requestScope.user.id})</p>
        <div class="mb-4">
            <label class="block text-gray-700 text-sm font-bold mb-2" for="username">
                Username
            </label>
            <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                   id="username" name="username" type="text" placeholder="Username" value="${requestScope.user.username}">
        </div>
        <div class="mb-6">
            <label class="block text-gray-700 text-sm font-bold mb-2" for="password">
                Password
            </label>
            <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                   id="password" name="password" type="password" placeholder="******************" value="">
        </div>
        <div class="mb-6">
            <label class="block text-gray-700 text-sm font-bold mb-2" for="role">
                Role
            </label>
            ${requestScope.user.role}
            <select class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="role" name="role" value="${requestScope.user.role}">
                <option value="USER" ${requestScope.user.role == "USER" ? "selected" : ""}>User</option>
                <option value="ADMIN" ${requestScope.user.role == "ADMIN" ? "selected" : ""}>Admin</option>
            </select>
        </div>
        <div class="flex items-center justify-between">
            <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    type="submit">
                Update
            </button>
            <a class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800" href="users">
                Back
            </a>
        </div>
    </form>
    <c:if test="${requestScope.error != null}">
        <p class="text-red-500 text-xs italic">${requestScope.error}</p>
    </c:if>
</div>
</body>
</html>
