<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Cart</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

</head>
<body>
    <div class="container">

        <jsp:include page="WEB-INF/jsp/include/header.jsp"></jsp:include>

        <hr />
        <div class="card-deck">
            <c:forEach items="${cartItems}" var="book">
                    <a class="card book-parent" book-id="${book.id}" href="/book/${book.id}">
                        <img src="${contextPath}/${book.imageUrl}" class="card-img-top" height="200" width="250" alt="Book">
                        <div class="card-body">
                            <h5 class="card-title">${book.name}</h5>
                            <p class="card-text">${book.description}</p>
                        </div>
                        <div class="card-footer card-totals">
                            <h5>Price: ${book.price}</h5>
                        </div>
                    </a>
            </c:forEach>
        </div>

        <c:if test="${cartItems.size() > 0}">
            <div class="cart-totals">
                <div class="pull-right"><h5><a href="/clear-cart">Clear cart</a></h5></div>
            </div>
        </c:if>
        <div class="cart-totals">
            <div class="pull-right"><h5>Total items:  ${totalItems}</h5></div>
        </div>
        <div>
            <div class="pull-right"><h5>Total price:  ${totalPrice} $</h5></div>
        </div>
    </div>

    <jsp:include page="WEB-INF/jsp/include/footer.jsp"></jsp:include>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
</body>
</html>