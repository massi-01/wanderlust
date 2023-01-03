<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.beans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.TravelModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
    <head>
        <title>Profilo Utente</title>
		<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style/dashboard.css">
        <link rel="stylesheet" href="style/profile.css">
		<link rel="stylesheet" href="style/form.css">
		<link rel="stylesheet" href="style/cart.css">
		<link rel="stylesheet" href="style/footer.css">
		<link rel="icon" href="icons/account_circle_FILL0_wght400_GRAD0_opsz48.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    </head>

    <body>
        <%@ include file="/fragments/navbar.jsp"%>

        <div class="container">
			<h1 class="text-center">Profilo Utente</h1>
			

			<section>
				<p class="profile-subtitle">Dati Anagrafici</p>
				<p>${user.getNome()} ${user.getCognome()}</p>
				
				<p class="profile-subtitle">Email e Password</p>
				<div class="fields">
					<div class="field">
						<label>Email: </label>
						<input type="text" value="${user.getEmail()}" readonly>	
					</div>
					<form action="restore-password" method="post">
						<div class="field">
							<label>Inserisci nuova password:  </label> 
							<input type="password" id="newpassword" name="newpassword" placeholder="inserisci nuova password" minlength="6" maxlength="20">
						</div>
						<div class="field">
							<label>Conferma nuova password: </label> 
							<input type="password" id="confirmnewpassword" name="confirmnewpassword" placeholder="conferma nuova password" minlength="6" maxlength="20">
						</div>
						<input type="submit" id="confirmbutton" name="confirmbutton" class="button" value="Conferma" disabled>
					</form>
				</div>
			</section>

			<section>
				<p class="profile-subtitle">Indirizzo di Spedizione</p>
				<address>
					${user.getIndirizzo()}, ${user.getUserCitta()}<br/>
					${user.getCap()}, <br/> Tel: ${user.getTelefono()}<br/>
				</address>
			</section>
			
            <c:if test="${user.getCreditCard() != null}">
                <section>
					<p class="profile-subtitle">Metodo di pagamento</p>
					<div class="field">
						<label for="">Numero carta: </label>
						<input type="tel" pattern="\d*" value="${user.getCreditCard().getCodice()}"  readonly/>
					</div>
					<div class="field">
						<label for="">CCV: </label>
						<input type="password" value="${user.getCreditCard().getCcv()}" readonly>
					</div>
					<div class="field">
						<label for="">Data Scadenza: </label>
						<input type="text" value="${user.getCreditCard().getMese()}/${user.getCreditCard().getAnno()}" readonly>
					</div>

					<button id="open-modal" class="button">Modifica carta di credito</button>
					<div id="modal" class="modal">
						<form class="form" style="margin-bottom: 3rem;"  action="creditcard" method="POST">
							<span class="close">&times;</span>
							<div class="form-field">
								<p>Codice carta di credito:</p>
								<input type="text" name="codice" placeholder="Codice carta di credito" minlength="16" maxlength="16"required>
							</div>
							<div class="form-field">
								<p>CCV:</p>
							<input type="text" name="ccv" placeholder="CCV" minlength="3" maxlength="3" required>
							</div>
							<div class="form-field">
								<p>Data di scadenza:</p>
							<select name="mese" aria-placeholder="MM">
								<c:forEach var ="i" begin="1" end="12">
									<option value="${i}">${i}</option>
								</c:forEach>
							</select>
							<select name="anno" aria-placeholder="YY">
								<c:forEach var="j" begin="22" end="42">
									<option value="${j}">${j}</option>
								</c:forEach>
							</select>
							</div>
							<input class="form-button" type="submit" name="submit" value="Modifica Carta di Credito">
						</form>
					</div>
                </section>
            </c:if>

            <c:if test="${user.getCreditCard() == null}">
                <p>Non hai inserito un metodo di pagamento</p>
				<button id="open-modal" class="button">Inserisci carta di credito</button>
				             
				<div id="modal" class="modal">
					<form class="form" style="margin-bottom: 3rem;"  action="creditcard" method="POST">
						<span class="close">&times;</span>
						<div class="form-field">
							<p>Codice carta di credito:</p>
							<input type="text" name="codice" placeholder="Codice carta di credito" minlength="16" maxlength="16"required>
						</div>
						<div class="form-field">
							<p>CCV:</p>
						<input type="text" name="ccv" placeholder="CCV" minlength="3" maxlength="3" required>
						</div>
						<div class="form-field">
							<p>Data di scadenza:</p>
							<select name="mese" aria-placeholder="MM">
								<c:forEach var ="i" begin="1" end="12">
									<option value="${i}">${i}</option>
								</c:forEach>
							</select>
							<select name="anno" aria-placeholder="YY">
								<c:forEach var="j" begin="22" end="42">
									<option value="${j}">${j}</option>
								</c:forEach>
							</select>
						<input class="form-button" type="submit" name="submit" value="Aggiungi Carta di Credito">
					</form>
				</div>
			</c:if>
		</div>
		
		<hr style="width: 60%; margin: 1rem auto;" />

		<h1 class="text-center">Ordini recenti</h1>
		<section class="container">
			<c:forEach items="${orders}" var="order">
				<div class="travel">
					<div class="travel-image">
						<img src="data:image/jpg;base64,${order.getOrder().getBase64Foto()}">
					</div>
		
					<div class="travel-body">
						<p class="title">${order.getOrder().getNome()}</p>
						<p><strong>Ordine effettuato il:</strong> ${order.getDate()}</p>
						<p><strong>Indirizzo di fatturazione:</strong></p>
						<p>${user.getNome()} ${user.getCognome()}.</p>
						<p>${order.getCapOrdine()} ${order.getIndirizzoOrdine()} ${order.getCittaOrdine()}</p>
						<p><strong>Modalit&agrave; di pagamento:</strong> ${user.getCreditCard().getCodice()}</p>
						<p><strong>Quantit&agrave;: </strong> ${order.getQuantita()}</p>
						<em class="price"> ${order.getOrder().getPrezzo()} &euro;</em>
						<p><strong>Prezzo totale: <em>${Math.round((order.getOrder().getPrezzo() * order.getQuantita()) * 100.0) / 100.0} &euro;</em></strong></p>
						<a href="receipt?order-code=${order.getOrderID()}" target="_blank"><span class="material-symbols-outlined">file_download</span></a>
					</div>
				</div>
			</c:forEach>
		</section>

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

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		
		<script>
			let newpwd = document.getElementById("newpassword");
			let confirmnewpwd = document.getElementById("confirmnewpassword");
			confirmnewpwd.addEventListener("keyup",event=>{
				const button = document.getElementById("confirmbutton");
				if(newpwd.value == confirmnewpwd.value && confirmnewpwd.value.length != 0){
					button.removeAttribute("disabled");
				}
			});
			
			// Modal event handlers
			const modal = $("#modal");
			const openModalButton = $("#open-modal");
			const closeModalButton = modal.find(".close");

			closeModalButton.click(() => modal.hide());

			openModalButton.click(() => modal.show());

		</script>
    </body>
</html>