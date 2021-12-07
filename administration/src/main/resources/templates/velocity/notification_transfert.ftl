<html>
<body style="background:#e6e6e6;" >
<br/>
<div style="background:white; margin-top:5%; margin-left:10%; margin-right:10%;" >
<div align="right"> <h4>${userService}</h4></div>
<div align="right"> <h4><p>courrier imputé le ${dateImputation}</h4></div>
<br/>
<center><h3><pre style="font-size: 16px;">Bonjour, ${prenomUser} ${nomUser}</pre></h3>
 <h3>Le courrier numéro  ${numero} comportant l'objet :</h3> <br/> "${objet}" <br/>
 <h2>vous a été transféré</h2></center>
<br/>
   <p style="margin-left: 2%; margin-right: 2%;"> Vous pouvez cliquer sur le lien suivant pour vous <a href="${url}">connecter</a> à l'application.</p>
   <br/>
  <br/>
   <p style="margin-left: 2%;">Dans le cas o&ugrave; le lien ne serait pas visible ou utilisable, vous pouvez s&eacute;lectionner le texte ci-dessous et le placer <br/>dans votre barre d'adresse de navigateur :</p><br/>
   <center><pre style="font-size: 12px;">${url}</pre>  </center>
	<br/>
   <hr/>
   </div>
   <center>
Ceci est un email généré automatiquement, veuillez ne pas y répondre svp. <br/>
</center>
<br/>
</body>
</html>