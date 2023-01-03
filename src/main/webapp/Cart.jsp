<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.beans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.TravelModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
    <head>
        <title>Carrello</title>
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style/dashboard.css">
        <link rel="stylesheet" href="style/footer.css">
        <link rel="stylesheet" href="style/cart.css">
        <link rel="stylesheet" href="style/profile.css">
        <link rel="stylesheet" href="style/form.css">
        <link rel="icon" href="icons/shopping_cart_FILL0_wght400_GRAD0_opsz48.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    </head>

    <body style="height: 100vh; display: flex; flex-direction: column;">
        <%@ include file="/fragments/navbar.jsp"%>
        
        <div class="container">

            <p class="page-title">Riepilogo ordine</p>
            <c:if test="${user != null}">
                <p class="page-subtitle">Indirizzo di fatturazione</p>
                <div class="data-field">
                    <p>${user.getIndirizzo()}, ${user.getCap()}</p>
                    <p>${user.getUserCitta()}</p>
                </div>

                <p class="page-subtitle">Metodo di pagamento</p>
                <c:if test="${user.getCreditCard() != null}">
                    <div class="data-field">
                        <p>Codice carta: ${user.getCreditCard().getCodice()}</p>
                        <p>CCV: ${user.getCreditCard().getCcv()}</p>
                        <p>Scadenza: ${user.getCreditCard().getMese()}/${user.getCreditCard().getAnno()}</p>
                    </div>
                </c:if>
                <c:if test="${user.getCreditCard() == null}">
                    <p>Non hai inserito ancora una carta di credito. Per inserirla vai nell'area utente.</p>
                </c:if>
            </c:if>

        <c:if test="${cart.size() > 0}">
		    <p class="page-subtitle">Rivedi gli articoli</p>
                <section class="cart">
                    <c:forEach items="${cart}" var="travel">
                        <div class="travel">
                            <div class="travel-image">
                                <img src="data:image/jpg;base64,${travel.value.getTravelBean().getBase64Foto()}">
                            </div>
                
                            <div class="travel-body">
                                <p class="title">${travel.value.getTravelBean().getNome()}</p>
                                <p class="description">${travel.value.getTravelBean().getDescrizione()}</p>
                                <em class="price"> ${Math.round((travel.value.getTravelBean().getPrezzo() * travel.value.getQuantity()) * 100.0) / 100.0} &euro;</em>
                                <div class="travel-footer">
                                    <div class="quantity-button">
                                        <p>
                                            <a style="text-decoration: none;" href="cart?action=remove-from-cart&product-code=${travel.value.getTravelBean().getCodice()}">
                                                <span class="material-symbols-outlined">remove</span>
                                            </a>
                                            Quantit&agrave;: ${travel.value.getQuantity()}
                                            <a style="text-decoration: none;" href="cart?action=increase&product-code=${travel.value.getTravelBean().getCodice()}">
                                                <span class="material-symbols-outlined">add</span>
                                            </a>
                                        </p>
                                    </div>
                                    <a class="remove-icon" href="cart?action=remove-all-occurrences&product-code=${travel.value.getTravelBean().getCodice()}"><span class="material-symbols-outlined">remove_shopping_cart</span></a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </section>

                <div class="pay-now">

                    <div>
                        <p class="total-price">${Math.round(total * 100.0) / 100.0} &euro;</p>
                        <c:if test="${user != null}"><p style="font-size: 12px;">Confermando il pagamento, accetti anche le nostre <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" target="_blank">Condizioni di vendita.</a></p></c:if>
                    </div>
        
                    <c:if test="${user != null}">
                        <c:if test="${user.getCreditCard() != null}">
                            <a class="pay-button" href="checkout?indirizzo-ordine=${user.getIndirizzo()}&cap=${user.getCap()}&citta=${user.getUserCitta()}">Paga ora</a>
                        </c:if>
                        <c:if test="${user.getCreditCard() == null}">
                            <a class="pay-button" href="profile">Inserisci una carta di credito</a>
                        </c:if>
                    </c:if>
                    <c:if test="${user == null}"><a class="pay-button" href="login.jsp">Effettua il login</a></c:if>
                </div>

                <c:if test="${user != null}">
                    <c:if test="${user.getCreditCard() != null}">
                        <div class="pay-form">
                            <p class="total-price">${Math.round(total * 100.0) / 100.0} &euro;</p>
                            <p class="total-price">Cambia indirizzo di fatturazione e paga ora</p>
                
                            <form class="form-payment" id="form-payment" action="checkout" method="get">
                                <div class="form-inputs">
                                    <div class="field">
                                        <label>Indirizzo:</label>
                                        <input type="text" name="indirizzo-ordine" placeholder="Indirizzo" minlength="4" maxlength="30">
                                    </div>
                                    <div class="field">
                                        <label>CAP: </label>
                                        <input type="text" name="cap" placeholder="Cap" pattern="[0-9]{5}" minlength="5" maxlength="5">
                                    </div>
                                    <div class="field">
                                        <label>Citt&agrave;: </label>
                                        <input type="text" name="citta" placeholder="Citt&agrave;" minlength="3" maxlength="30">
                                    </div>
                                </div>
                            </form>
                            <div class="pay-footer">
                                <p style="font-size: 12px;">Confermando il pagamento, accetti anche le nostre <a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" target="_blank">Condizioni di vendita.</a></p></p>
                                <button class="pay-button" type="submit" form="form-payment">Paga ora</button>
                            </div>
                        </div>
                    </c:if>  
                </c:if>
            </c:if>

            <c:if test="${cart.size() == 0}">
                <p>Il carrello &egrave; vuoto</p>
            </c:if>
        </div>
        <div style="margin-top: auto;">
            <%@ include file="/fragments/footer.jsp"%>
        </div>
        <script>
            const navbarToggle = document.querySelector(".navbar-toggle");

            navbarToggle.addEventListener('click', event => {
                event.preventDefault();
                const navbarMenu = document.querySelector('.navbar-menu');
                navbarMenu.classList.toggle('is-active');
            });
        </script>
    </body>
</html>