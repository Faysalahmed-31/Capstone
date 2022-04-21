<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add new book</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

    <div class="container">

        <jsp:include page="WEB-INF/jsp/include/header.jsp"></jsp:include>

        <hr />

        <div id="success-alert" class="alert alert-success" role="alert" style="display: none;">
            New book has been created!
        </div>

        <form class="form-signin" id="create-book-form">
            <h2 class="form-signin-heading">Create new book</h2>
            <div class="form-group">
                <label for="book-name"> Name</label>
                <input type="text" id="book-name" class="form-control" placeholder="Book name" required autofocus />
            </div>
            <div class="form-group">
                <label for="book-description">Description:</label>
                <input type="text" id="book-description" class="form-control" placeholder="Description" required />
            </div>
            <div class="form-group">
                <label for="book-author">Author:</label>
                <input type="text" id="book-author" class="form-control" placeholder="Author" required />
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Create</button>
        </form>

    </div>

    <jsp:include page="WEB-INF/jsp/include/footer.jsp"></jsp:include>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/main.js"></script>
</body>
</html>