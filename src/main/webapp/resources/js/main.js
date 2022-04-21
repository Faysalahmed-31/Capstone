(function() {
    const BASE_URL = window.location.origin;
    const pathname = window.location.pathname;

    function init() {
        bindEvents();
        getCartCount();
        showHideSearchBooksFromHeader();
    }

    function showHideSearchBooksFromHeader() {
        if (pathname === "/" || pathname === "/search") {
            $("#search-books-form").show();
        } else {
            $("#search-books-form").hide();
        }
    }

    function bindEvents() {
        $(".add-to-cart-btn").off("click").on("click", addToCart);
        $("#create-book-form").off("submit").on("submit", createNewBook);
    }

    function addToCart(e) {
        e.preventDefault();
        const bookId = $(this).closest(".book-parent").attr("book-id");
        console.log(bookId);

        $.ajax({
            url: BASE_URL + "/cart/add/" + bookId,
            type: "POST",
            success: function(response) {
                console.log("Added successfully", response);
                if (response) {
                    $("#cart-items-count").text(response);
                }
            },
            error: function() {
                console.log("Failed to add book to cart");
            }
        });
    }

    function getCartCount() {
        $.ajax({
            url: BASE_URL + "/cart/total",
            type: "GET",
            success: function(response) {
                if (response) {
                    $("#cart-items-count").text(response);
                }
            },
            error: function() {
                console.log("Failed to get cart total");
            }
        });
    }

    function createNewBook(e) {
        e.preventDefault();
        const book = {
            name: $("#book-name").val(),
            description: $("#book-description").val(),
            author: $("#book-author").val()
        }

        console.log(book);

        $.ajax({
            url: BASE_URL + "/book/create",
            type: "POST",
            data: JSON.stringify(book),
            contentType: "application/json",
            success: function(response) {
                console.log("Created successfully", response);
                if (response) {
                    $("#success-alert").show();
                    $("#book-name").val("");
                    $("#book-description").val("");
                    $("#book-author").val("");
                }
            },
            error: function() {
                console.log("Failed to add book to cart");
            }
        });
    }

    init();
})();