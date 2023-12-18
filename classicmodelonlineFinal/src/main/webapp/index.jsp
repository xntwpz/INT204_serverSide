<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Classic Model Online</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.1/font/bootstrap-icons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        .cart-info {
            margin-left: -1em;
            border-radius: 12px;
            background-color: bisque;
            position: absolute;
            z-index: 100;
            border: none;
            padding-left: 5px;
            padding-right: 5px;
            display: none;
        }

        .div-link {
            cursor: pointer;
            border: 1px burlywood solid;
        }

        .div-link:hover {
            background-color: bisque;
        }

    </style>
    <script>
        function testAjax() {
            //alert("this is testAjax method")
            let xhr = new XMLHttpRequest();
            xhr.onload = function () {
                if (xhr.status != 200) {
                    alert(`Error ${xhr.status}: ${xhr.statusText}`);
                } else { // show the result
                    alert(`Done, got ${xhr.response.length} bytes`);
                }
            };
            xhr.open('GET', 'hello-servlet');
            xhr.send();
        }

        function loadProduct(page, pageSize = document.getElementById('itemsPage').value) {
            thisContent = 'product-list'
            setLoading('on')
            //alert('page: '+ page + ", size: "+ pageSize)
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                setLoading('off')
                // handle code
                if (xhttp.status == 200) {
                    document.getElementById('body-content').innerHTML = xhttp.responseText
                } else {
                    alert('Error : ' + xhttp.status)
                }
                document.getElementById("body-content").innerHTML = xhttp.responseText;
            }
            // called to server
            xhttp.open("GET", "product-list?page=" + page + "&pageSize=" + pageSize);
            // send
            xhttp.send();
        }

        // called office + check error
        function loadOffice(officeCode) {
            thisContent = 'product-list'
            setLoading('on')
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                setLoading('off')
                // handle error check status 200
                if (xhttp.status == 200) {
                    document.getElementById("body-content").innerHTML = xhttp.responseText;
                } else {
                    alert('Error : ' + xhttp.status)
                }
            }
            xhttp.open("GET", "office-list?officeCode=" + officeCode);

            xhttp.send();
        }

        var thisContent = '';

        function setLoading(on_off) {
            let loading = document.getElementById("loading");
            if (on_off == 'on') {
                loading.classList.remove("d-none");
                loading.classList.add("d-inline");
            } else {
                loading.classList.remove("d-inline");
                loading.classList.add("d-none");
            }
        }

        function addToCart(productCode) {
            setLoading('on')
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                setLoading('off');
                if (xhttp.status == 200) {
                    // cartInfo
                    cartInfo = document.getElementById("noOfItemInCart");
                    noOfItem = xhttp.responseText;
                    if (noOfItem > 0) {
                        cartInfo.style.display = 'inline'
                    } else {
                        cartInfo.style.display = 'none'
                    }
                    cartInfo.innerHTML = noOfItem;
                } else {
                    alert("Error : " + xhttp.status + "-----" + xhttp.getResponseHeader('error'))
                }
            }
            // use xml called to servlet add-to-cart
            // display 404 cus don't have servlet
            xhttp.open("GET", "add-to-cart?productCode=" + productCode);
            xhttp.send();
        }

        function viewCart() {
            setLoading('on')
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                setLoading('off');
                if (xhttp.status == 200) {
                    document.getElementById("view-cart").innerHTML = xhttp.responseText;
                    $('#viewCartModal').modal('show');
                } else {
                    alert("Error : " + xhttp.status + "-----" + xhttp.getResponseHeader('error'))
                }
            }
            xhttp.open("GET", "view-cart.jsp"); // must have controller (servlet) and then forward to jsp
            xhttp.send();
        }

        function showLoginForm() {
            let menu = document.getElementById("login-menu").innerHTML;
            if (menu.includes('Logout')) {
                logout();
            } else {
                $('#modalLoginForm').modal('show');
            }
        }

        function logout() {
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                location.reload();
            }
            xhttp.open("GET", "logout");
            xhttp.send();
        }

        function login(userName, password) {
            // parameter userName,password
            // validation
            if (userName == '' || password == '' || userName == undefined) {
                document.getElementById("login-message").innerHTML = "Invalid user name or password !!!";
                return;
            }
            // correct
            // spin blue
            setLoading('on');
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                setLoading('off');
                // if status 200 , change login to logout
                if (xhttp.status == 200) {
                    $('#modalLoginForm').modal('hide');
                    document.getElementById("login-menu").innerHTML =
                        "<i class='bi bi-box-arrow-left'></i> Logout"
                } else {
                    // if not
                    document.getElementById("login-message").innerHTML =
                        xhttp.getResponseHeader("error");
                    "Login error: " + xhttp.status + "----" + xhttp.getResponseHeader("error")
                }
            }
            let urlEncodedData = "", urlEncodedDataPairs = [];
            urlEncodedDataPairs.push(encodeURIComponent("userName") + '='
                + encodeURIComponent(userName));
            urlEncodedDataPairs.push(encodeURIComponent("password") + '=' + encodeURIComponent(password));
            urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');
            xhttp.open("POST", "login");
            xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhttp.send(urlEncodedData); // send to body

        }

    </script>

</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid"><a class="navbar-brand text-warning" href="javascript:void(0)">Classic Model</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"><span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="javascript:loadOffice('')">Office</a></li>
                <li class="nav-item"><a class="nav-link" href="javascript:loadProduct(1,15)">Product</a></li>
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void(0)">Order History</a>
                </li>
                <li class="nav-item ml-4">
                    <a class="nav-link text-light" href="javascript:showLoginForm()" id="login-menu">
                        <i class="bi bi-box-arrow-in-right"></i> Login</a>
                </li>
            </ul>
            <div style="margin-right: 20px">
                <img src="assets/images/cart2.png" width="42" onclick="viewCart()"/>
                <button id="noOfItemInCart" class="cart-info" onclick="viewCart()"></button>
            </div>
            <form class="d-flex">
                <input id="searchBox" class="form-control me-2" type="text" placeholder="Search">
                <button class="btn btn-primary" type="button" onclick="search(thisContent)">Search</button>
            </form>
        </div>
    </div>
</nav>
<div class="container" id="body-content">
    <h3>SIT-202: Classic Model Online</h3>
    <p>SIT-202 is a global online marketplace. <br>
    <p>SIT-202 is a young and dynamic company specialized in the online
        sale of static collectible models and related accessories. Our location are distributed around
        the world. <br> … they are popular collectibles for every automobile fan.rs! .. </p>
</div>

<div class="d-flex justify-content-center modal d-none" id="loading">
    <div class="spinner-border text-primary"
         style="margin-top: 10%; width: 6rem; height: 6rem;"></div>
</div>

<div class="modal" tabindex="-1" id="viewCartModal">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header"><h5 class="modal-title">Your Cart</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                        onclick="$('#viewCartModal').modal('hide')">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="view-cart"><p>Modal body text goes here.</p></div>
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/jsp/login-form.html"/>
</body>
</html>