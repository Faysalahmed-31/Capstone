<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div>
<%--    <sec:authentication property="principal.authorities"/>--%>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header navbar-left">
                <a class="navbar-brand white-link" href="/">Case Study</a>
            </div>

            <form id="search-books-form" class="navbar-form navbar-left" method="GET" action="search">
                <div class="form-group navbar-left">
                    <input type="text" name="name" value="<c:out value="${param.name}"/>" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-primary navbar-left">Submit</button>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown pull-right">
                    <a href="#" class="dropdown-toggle white-link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Profile<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li>${pageContext.request.userPrincipal.name}</li>
                        <li><a onclick="document.forms['logoutForm'].submit()">Logout</a></li>
                    </ul>
                </li>
                <li class="pull-right">
                    <h5 id="cart-items-count"></h5>
                    <a class="bi bi-cart white-link" href="/cart">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                        </svg>
                        <span>Cart</span>
                    </a>
                </li>
                <sec:authorize access="hasAuthority('admin')">
                    <li class="pull-right">
                        <a class="white-link" href="/book/add"><span>Add Book</span></a>
                    </li>
                </sec:authorize>
            </ul>

        </div>
    </nav>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </c:if>
</div>