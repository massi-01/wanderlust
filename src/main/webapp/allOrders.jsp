<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="model.beans.*" %>
        <%@ page import="java.util.*" %>
            <%@ page import="model.TravelModel" %>
                <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                    <!DOCTYPE html>

                    <head>
                        <title>Dashboard</title>
                        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
                        <meta http-equiv="Pragma" content="no-cache">
                        <meta http-equiv="Expires" content="0">
                        <link rel="stylesheet" href="style/dashboard.css">
                        <link rel="stylesheet" href="style/table.css">
                        <link rel="stylesheet" href="style/footer.css">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <link rel="icon" href="icons/airplane_ticket_FILL0_wght400_GRAD0_opsz48.png">
                        <link rel="stylesheet"
                            href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
                    </head>


                    <%@ include file="/fragments/navbar.jsp" %>

                        <body style="display: flex; flex-direction: column; height: 100vh;">

                            <div class="search-box">
                                <input class="search-txt" id="search" type="text" placeholder="Cerca...">
                                <a class="search-btn" href="#"><span class="material-symbols-outlined">search</span></a>
                            </div>
                            <div style="overflow-x: auto;">
                            <table class="orders-table">
                                <thead>
                                    <tr>
                                        <td>Nome Prodotto</td>
                                        <td>Nome utente</td>
                                        <td>Prezzo</td>
                                        <td>Quantit&agrave;</td>
                                        <td>Data</td>
                                        <td>Indirizzo</td>
                                        <td>Prezzo Totale</td>
                                    </tr>
                                </thead>

                                
                                    <tbody id="table-body">
                                        <c:forEach items="${allOrders}" var="order">
                                            <tr>
                                                <td>${order.getOrder().getNome()}</td>
                                                <td>${order.getUserID()}</td>
                                                <td>${Math.round((order.getOrder().getPrezzo()) * 100.0) / 100.0} &euro;
                                                </td>
                                                <td>${order.getQuantita()}</td>
                                                <td>${order.getDate()}</td>
                                                <td>${order.getIndirizzoOrdine()},
                                                    ${order.getCapOrdine()},${order.getCittaOrdine()}</td>
                                                <td><b>${Math.round((order.getOrder().getPrezzo() * order.getQuantita())
                                                        * 100.0) / 100.0} &euro;</b></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                            </table>
                            </div>
                            <div style="margin-top: auto;">
                                <%@ include file="/fragments/footer.jsp" %>
                            </div>

                            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
                                        $("#table-body tr").filter(function () {
                                            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                                        });
                                    });
                                });

                            </script>
                        </body>

                        </html>