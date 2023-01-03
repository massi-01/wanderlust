<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <title>Receipt</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="icons/airplane_ticket_FILL0_wght400_GRAD0_opsz48.png">
        <link rel="stylesheet" href="style/receipt.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
        <style type="text/css" media="print">
            @page {
                size: auto;   /* auto is the initial value */
                margin: 0;  /* this affects the margin in the printer settings */
            }
            </style>
    </head>

    <body>
        <header class="receipt-header">
            <div class="header-logo">
                <img src="https://i.imgur.com/N0n0gMr.png">
            </div>
            <div>
                <p><strong>Clicca sull'icona per stampare la fattura</strong></p>
                <a id="print-button" onclick="window.print()" style="cursor: pointer;">
                    <span class="material-symbols-outlined">file_download</span>
                </a>
            </div>

        </header>

        <hr>

        <section class="receipt-info">
            <div class="receipt-info-img-container">
                <img src="data:image/jpg;base64,${order.getOrder().getBase64Foto()}">
            </div>
            <div class="receipt-info-details">
                <p><strong>${order.getOrder().getNome()}</strong></p>
                <p><strong>Ordine effettuato il:</strong> ${order.getDate()}</p>
                <p><strong>Indirizzo di fatturazione:</strong> <br> ${user.getNome()} ${user.getCognome()}<br> ${user.getIndirizzo()}, ${user.getUserCitta()} <br> ${user.getCap()}</p>
                <p><strong>Metodo di pagamento:</strong> ${user.getCreditCard().getCodice()}</p>
                <p><strong>Prezzo:</strong> ${Math.round((order.getOrder().getPrezzo()) * 100.0) / 100.0} &euro;</p>
                <p><strong>Quantit&agrave;: </strong>${order.getQuantita()}</p>
				<p><strong>Prezzo totale: <em>${Math.round((order.getOrder().getPrezzo() * order.getQuantita()) * 100.0) / 100.0} &euro;</em></strong></p>
            </div>
        </section>

        <hr>

        <div class="safety-info">
            <p> 
                <span class="material-symbols-outlined">info</span> 
                <strong>Informazioni Importanti</strong> <br>
                In risposta al Coronavirus (COVID-19) questa struttura ha adottato
                misure extra di sicurezza e igiene.
                I servizi di ristorazione e somministrazione di bevande in questa
                struttura potrebbero essere limitati o sospesi a causa del Coronavirus
                (COVID-19).
                A causa del Coronavirus (COVID-19) questa struttura sta adottando
                misure per proteggere la salute degli ospiti e dello staff. Pertanto, alcuni
                servizi potrebbero essere ridotti o non disponibili, cos&igrave; come le
                dotazioni.
                A causa del Coronavirus (COVID-19) questa struttura ha ridotto gli orari
                di servizio e reception.
                A causa del Coronavirus (COVID-19), la struttura applica rigide misure di
                distanziamento fisico.
                A causa del Coronavirus (COVID-19) &egrave; obbligatorio indossare la
                mascherina in tutte le aree comuni interne.
                Per effettuare il check-in &egrave; obbligatorio mostrare un referto negativo di
                tampone molecolare per Coronavirus (COVID-19).
                Siete pregati di comunicare in anticipo alla struttura l'orario in cui prevedete di arrivare.
                Potrete inserire informazione nella sezione Richieste Speciali al momento prenotazione, o contattare la
                struttura utilizzando i recapiti riportati nella conferma della prenotazione.
            </p>
        </div>

        <hr>
        <br>

        <p style="width: 80%; font-size: 0.7rem; color: #888; margin: 1px auto;">Questa versione stampabile della conferma contiene i dati pi&ugrave; importanti della tua prenotazione, e pu&ograve; essere
            utilizzata per il check-in all'arrivo presso la struttura a ${order.getOrder().getCitta()}. Per ulteriori dettagli, consulta l'e-mail di conferma inviata a ${user.getEmail()}.</p>

    </body>

</html>