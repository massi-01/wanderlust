<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.beans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.TravelModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
    <head>
        <title>${travel.getNome()}</title>
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        <link rel="stylesheet" href="style/dashboard.css">
        <link rel="stylesheet" href="style/dashboard.css">
        <link rel="stylesheet" href="style/form.css">
        <link rel="stylesheet" href="style/product-page.css">
        <link rel="stylesheet" href="style/footer.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="icons/airplane_ticket_FILL0_wght400_GRAD0_opsz48.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    </head>

    
    <body style="height: 100vh; display: flex; flex-direction: column;">
        <%@ include file="/fragments/navbar.jsp"%>
        
        <main class="product">
 
            <div class="product-image">
              <img src="data:image/jpg;base64,${travel.getBase64Foto()}" alt="">
            </div>
    
            <div class="product-body">
                <h2>${travel.getNome()}</h2>
                
                <div class="product-description">         
                    <p>${travel.getDescrizione()}</p>
                    <span>${travel.getCitta()}, ${travel.getStato()}<br></span>
                    <span>Numero di giorni: ${travel.getGiorni()}</span>
                </div>
    
                <div class="product-footer">
                    <span class="product-price">${travel.getPrezzo()} &euro;</span>
                    <button class="button">Aggiungi al Carrello <span style="font-size: 1rem;" class="material-symbols-outlined">add_shopping_cart</span></button>
                </div>
            </div>
        </main>

        <div style="margin-top: auto;">
            <%@ include file="/fragments/footer.jsp"%>
        </div>

        <script 
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" 
            integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" 
            crossorigin="anonymous" 
            referrerpolicy="no-referrer">
        </script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
            const navbarToggle = document.querySelector(".navbar-toggle");

            navbarToggle.addEventListener('click', event => {
                event.preventDefault();
                const navbarMenu = document.querySelector('.navbar-menu');
                navbarMenu.classList.toggle('is-active');
            });

            $(".product-footer > button").click((event) => {
                event.preventDefault();
                const url = "cart?action=add-to-cart&product-code=${travel.getCodice()}";
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

        </script>
    </body>
</html>