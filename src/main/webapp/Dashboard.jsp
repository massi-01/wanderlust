<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.beans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.TravelModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
    <head>
        <title>WanderLust</title>
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        <link rel="stylesheet" href="style/dashboard.css">
        <link rel="stylesheet" href="style/footer.css">
        <link rel="stylesheet" href="style/form.css">
        <link rel="stylesheet" href="style/table.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="icons/airplane_ticket_FILL0_wght400_GRAD0_opsz48.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    </head>

    
    <body>
        <%@ include file="/fragments/navbar.jsp"%>

        <div class="search-box">
            <input class="search-txt" id="search" type="text" placeholder="Cerca...">
            <a class="search-btn" href="#"><span class="material-symbols-outlined">search</span></a>
        </div>

        <div class="card-container">
            <c:forEach items="${catalogo}" var="travel">

                <div class="card">
                    <div class="card-image">
                        <a href="product?product-code=${travel.getCodice()}">
                            <img src="data:image/jpg;base64,${travel.getBase64Foto()}">
                        </a>
                    </div>
                    <div class="card-body">
                        <h3>${travel.getNome()}</h3>
                        <p class="card-paragraph">${travel.getDescrizione()}</p>
                        <div style="display: flex; flex-direction: row; justify-content: space-between;">
                            <span style="font-size: 12px; color: #2565f8; letter-spacing: 1px;text-transform: uppercase;text-decoration: none;">${travel.getCitta()}</span>
                            <p style="font-size:20px; text-align: right; font-weight: 800;">${Math.round((travel.getPrezzo()) * 100.0) / 100.0} &euro;</p>
                        </div>
                    </div>
                    <div class="card-footer">
                        <c:if test="${user.getEditor() == true}">
                            <button id="myBtn-${travel.getCodice()}" data-targetModal="modal-${travel.getCodice()}" class="icon-link">
                                <span class="material-symbols-outlined">edit</span>
                            </button>
                            <a class="icon-link" href="travel?action=remove&codice=${travel.getCodice()}">
                                <span class="material-symbols-outlined">delete</span>
                            </a>
                        </c:if>
                        <button class="button">
                            Aggiungi al carrello
                            <span style="font-size: 1rem;" class="material-symbols-outlined">
                                add_shopping_cart
                            </span>
                        </a>
                    </div>
                    <div id="modal-${travel.getCodice()}" class="modal">
                        <form class="form" action="travel" style="margin-bottom: 3rem;"  method="POST" enctype="multipart/form-data">
                            <span class="close">&times;</span>
                            <div class="form-field">
                                <p>Codice univoco del viaggio:</p>
                                <input type="text" name="codice" value="${travel.getCodice()}" placeholder="Id (6 caratteri)" minlength="6" maxlength="6"required>
                            </div>
                            <div class="form-field">
                                <p>Nome del viaggio:</p>
                            <input type="text" name="nome" value="${travel.getNome()}" placeholder="Nome" minlength="4"required>
                            </div>
                            <div class="form-field">
                                <p>Prezzo in euro:</p>
                            <input type="number" name="prezzo" step="0.01" value="${travel.getPrezzo()}" placeholder="Prezzo" min="1"required>
                            </div>
                            <div class="form-field">
                                <p>Numero di giorni del viaggio:</p>
                            <input type="number" name="giorni" value="${travel.getGiorni()}" placeholder="Numero di giorni" min="1" required>
                            </div>
                            <div class="form-field">
                                <p>Citt&agrave;:</p>
                            <input type="text" name="citta" value="${travel.getCitta()}" placeholder="Citt&agrave;" required>
                            </div>
                            <div class="form-field">
                                <p>Stato:</p>
                            <input type="text" name="stato" value="${travel.getStato()}" placeholder="Stato" required>
                            </div>
                            <div class="form-field">
                                <p>Descrizione del viaggio:</p>
                            <textarea class="textbox" name="descrizione" placeholder="descrizione" minlength="2" cols="20" rows="4" required>${travel.getDescrizione()}</textarea>
                            </div>
                            <div class="form-field">
                                <input type="file" name="image">
                            </div>
                            <input type="hidden" name="edit" value="1">
                            <input class="form-button" type="submit" name="submit" value="Aggiungi Prodotto">
                        </form>
                    </div>
                </div>

            </c:forEach>
        </div>

        <c:if test="${user.getEditor() == true}">
            <button id="openModal" class="button"><span class="material-symbols-outlined">add_home_work</span></button>
             
            <div id="myModal"class="modal">
                <form class="form" style="margin-bottom: 3rem;"  action="travel" method="POST" enctype="multipart/form-data">
                    <span class="close">&times;</span>
                    <div class="form-field">
                        <p>Codice univoco del viaggio:</p>
                        <input type="text" name="codice" placeholder="Id (6 caratteri)" minlength="6" maxlength="6"required>
                    </div>
                    <div class="form-field">
                        <p>Nome del viaggio:</p>
                    <input type="text" name="nome" placeholder="Nome" minlength="4"required>
                    </div>
                    <div class="form-field">
                        <p>Prezzo in euro:</p>
                    <input type="number" name="prezzo" step="0.01" placeholder="Prezzo" min="1"required>
                    </div>
                    <div class="form-field">
                        <p>Numero di giorni del viaggio:</p>
                    <input type="number" name="giorni" placeholder="Numero di giorni" min="1" required>
                    </div>
                    <div class="form-field">
                        <p>Citt&agrave;:</p>
                    <input type="text" name="citta" placeholder="Citt&agrave;" required>
                    </div>
                    <div class="form-field">
                        <p>Stato:</p>
                    <input type="text" name="stato" placeholder="Stato" required>
                    </div>
                    <div class="form-field">
                        <p>Descrizione del viaggio:</p>
                    <textarea class="textbox" name="descrizione" placeholder="descrizione" minlength="2" cols="20" rows="4" required></textarea>
                    </div>
                    <div class="form-field">
                        <input type="file" name="image">
                    </div>
                    <input class="form-button" type="submit" name="submit" value="Aggiungi Prodotto">
                </form>
            </div>
        </c:if>

        <%@ include file="/fragments/footer.jsp"%>
        <c:if test="${user != null}">
            <script src="scripts/script.js"></script>
        </c:if>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
            const navbarToggle = document.querySelector(".navbar-toggle");

            navbarToggle.addEventListener('click', event => {
                event.preventDefault();
                const navbarMenu = document.querySelector('.navbar-menu');
                navbarMenu.classList.toggle('is-active');
            });

            $(document).ready(function () {
                $("#search").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $(".card-body div span").filter(function () {
                        $(this).parent().parent().parent().toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });

            const productsIDs = [];
            <c:forEach items="${catalogo}" var="travel">
                productsIDs.push("${travel.getCodice()}");
            </c:forEach>

            const cards = document.querySelectorAll('.card');
            
            for(let i=0; i < productsIDs.length; i++){
                
                const card = cards[i];
                const addToCartButton = card.querySelector('.card-footer > .button');
                

                addToCartButton.addEventListener('click', event => {
                    event.preventDefault();
                    
                    const url = 'cart?action=add-to-cart&product-code='+ productsIDs[i];
                    $.get(url, (data, status) => {
                        if(status === 'success'){
                            Swal.fire({
                                title: 'Fatto!',
                                text: "Elemento aggiunto al carrello!",
                                icon: 'success',
                                confirmButtonColor: '#2565f8',
                            });
                            return;
                        }

                        alert("Impossibile aggiungere questo prodotto al carrello");
                    });
                });
            }

        </script>
    </body>
</html>