<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Register</title>
		<link rel="icon" href="icons/login_FILL0_wght400_GRAD0_opsz48.png">
		<link rel="stylesheet" href="style/form.css">
        <style>
            body{
                height: auto;
				background: 
                    linear-gradient(
                        23deg, 
                        #2565f8 4%, 
                        rgba(255,255,255,1) 68%
                    ) 
                    no-repeat;
            }


            .form{
                margin: 5rem auto;
            }
        </style>
	</head>
	<body>
		<form class="form" action="register" method="POST">
			<a href="travel"><img class="login-logo" src="https://i.imgur.com/N0n0gMr.png"></a>
			<p>Registrati qui</p>
			<div class="form-field">
				<p>E-mail</p>
				<input type="email" name="email" id="email-field" required minlength="4" maxlength="45" placeholder="E-mail">
			</div>
			<div class="form-field">
				<p>Username</p>
				<input type="text" name="username" id="username-field" required minlength="4" maxlength="20" placeholder="Username">
			</div>
			<div class="form-field">
				<p>Password</p>
				<input type="password" name="password" minlength="6" maxlength="20" required placeholder="Password">
			</div>
			<div class="form-field">
				<p>Nome</p>
           		<input type="text" name="firstname" required placeholder="Nome">
			</div>
            <div class="form-field">
				<p>Cognome</p>
          		<input type="text" name="lastname" required placeholder="Cognome">
			</div>
            <div class="form-field">
				<p>Indirizzo</p>
				<input type="text" name="address" required placeholder="Indirizzo">
			</div>
			<div class="form-field">
				<p>Citt&agrave;</p>
				<input type="text" name="city" required placeholder="Citt&agrave;">
			</div>
			<div class="form-field">
				<p>CAP</p>
				<input type="text" name="postalcode" pattern="[0-9]{5}" required placeholder="CAP">
			</div>
			<div class="form-field">
				<p>Telefono</p>
				<input type="text" name="phone" required placeholder="Telefono">
			</div>
			<input type="submit" class="form-button" value="Registrati">
		</form>

		<script>

			const usernameField = document.getElementById("username-field");
			const emailField = document.getElementById("email-field");

			const fieldValidator = (field, event, condition, message) =>{
				if(condition) {
					field.style.border = "2px solid red";
				}else{
					message = "";
					field.style.border = "2px solid #ccd6dd";
				}
				event.target.setCustomValidity(message);
			};

			usernameField.addEventListener('input', event => {
				const username = usernameField.value;
				const xhr = new XMLHttpRequest();
				xhr.onload = ()=> {
					if(xhr.status != 200){
						usernameField.setCustomValidity("An error Occurred");
						return;
					}
					
					const response = JSON.parse(xhr.responseText);
					let message = "";
					if(username.length > 0) fieldValidator(usernameField, event, response.availability == false, "Username non disponibile");
				}
				
				xhr.open('POST' ,'validation', true);
				xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhr.send("username="+username);
			});

			emailField.addEventListener('blur', event => {
				const email = emailField.value;
				const xhr = new XMLHttpRequest();
				xhr.onload = ()=> {
					if(xhr.status != 200){
						emailField.setCustomValidity("An error Occurred");
						return;
					}
					
					const response = JSON.parse(xhr.responseText);
					
					if(email.length > 0) fieldValidator(emailField, event, response.availability == false, "E-mail non disponibile");
				}
				
				xhr.open('POST' ,'validation', true);
				xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhr.send("email="+email);
			});
		</script>

	</body>
</html>