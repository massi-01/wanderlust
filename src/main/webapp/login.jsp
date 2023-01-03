<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Login</title>
		<link rel="stylesheet" href="style/form.css">
        <link rel="icon" href="icons/login_FILL0_wght400_GRAD0_opsz48.png">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <% String loginError = (String)request.getAttribute("loginError");
			if(loginError != null){ 
        %>
            <link rel="stylesheet" href="style/error.css">
		<% } %>
        
        <style>
            body{
                height: 100vh;
                background: 
                    linear-gradient(
                        23deg, 
                        #2565f8 4%, 
                        rgba(255,255,255,1) 68%
                    ) 
                    no-repeat;
            }
        </style>
	</head>
	
	<body>
		

		<form class="form" id="login-form" action="/demo/login" method="post">
            <a href="travel"><img class="login-logo" src="https://i.imgur.com/N0n0gMr.png"></a>
			
            <p>Inserisci i tuoi dati per accedere</p>
			<c:if test="${loginError != null}">
				<div class="form-field">
					<p class="error-message">${loginError}</p>
				</div>
			</c:if>
            <div id="username-div" class="form-field">
                <p>Username</p>
                <input type="text" id="username-field" name="username" required placeholder="Username">
            </div>

            <div class="form-field">
                <p>Password</p>
                <input type="password" id="password-field" name="password" required placeholder="Password">
            </div>
			
            <input type="submit" id="submit-button" class="form-button" name="Submit" value="Accedi">

			<p style="margin-top: 1.5rem;">Non sei registrato? <a href="RegisterPage.jsp">Registrati qui!</a></p>
		</form>
	</body>
</html>