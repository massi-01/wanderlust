<nav class="navbar">
    <a style="text-decoration: none;" href="/demo/travel" class="navbar-logo"><img class="navbar-logo" src="https://i.imgur.com/N0n0gMr.png"></a>
    <div class="navbar-menu">
        <c:if test="${user != null}">
            <c:if test="${user.getEditor() == true}"><a href="retrieve">Gestione Ordini</a></c:if>
            <a href="profile">Area Utente</a>
        </c:if>
        <c:if test="${user == null}">
            <a href="login.jsp">Login</a>
        </c:if>
        <a href="cart">Carrello
            <span style="vertical-align: middle; font-size: 1rem;" class="material-symbols-outlined">
                shopping_cart
            </span>
        </a>
        <c:if test="${user != null}">
            <a href="/demo/logout">Logout <span class="material-symbols-outlined" style="vertical-align: middle; font-size: 1rem;">logout</span></a>
        </c:if>
    </div>
    <button class="navbar-toggle">
        <span class="material-symbols-outlined">menu</span>
    </button>
</nav>