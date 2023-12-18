<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User List Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex flex-col justify-center p-10">
<form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 w-full" method="post" action="users">
    <input type="hidden" name="action" value="search">
    <div class="mb-4">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="search">
            Search
        </label>
        <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
               id="search" name="search" type="text" placeholder="Search" value="${requestScope.search}">
    </div>
    <div class="flex items-center justify-between">
        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                type="submit">
            Search
        </button>
    </div>
</form>
<table class="table-auto w-full my-4">
    <thead>
    <tr>
        <th class="px-4 py-2">ID</th>
        <th class="px-4 py-2">Username</th>
        <th class="px-4 py-2">Role</th>
        <th class="px-4 py-2">Edit</th>
        <th class="px-4 py-2">Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" varStatus="loop" items="${requestScope.users}">
        <tr>
            <td class="border px-4 py-2">${loop.index + 1}</td>
            <td class="border px-4 py-2">${user.username}</td>
            <td class="border px-4 py-2">${user.role}</td>
            <td class="border px-4 py-2">
                <a class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" href="?id=${user.id}">Edit</a>
            </td>
            <td class="border px-4 py-2">
                <form action="users" method="post" class="m-0">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${user.id}">
                    <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded" type="submit">
                        Delete
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="5">
            <div class="flex justify-center">
                <div class="flex rounded-md mt-4">
                    <c:if test="${requestScope.page > 1}">
                        <a href="?page=${requestScope.page - 1}"
                           class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-l">
                            Previous
                        </a>
                    </c:if>
                    <div class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4">
                        Page ${requestScope.page} of ${requestScope.totalPages}
                    </div>
                    <c:if test="${requestScope.page < requestScope.totalPages}">
                        <a href="?page=${requestScope.page + 1}"
                           class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-r">
                            Next
                        </a>
                    </c:if>
                </div>
            </div>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>
