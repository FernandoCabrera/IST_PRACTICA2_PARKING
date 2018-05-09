<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js">
	</script>
</head>
<body>
 
<form action="#" id="consulta">
	Matricula: <input type="text" name="matricula" id="matricula">
    <input type="submit" value="Consultar">
</form>
Coste:  <span id="coste"></span> Euros
<script type="text/javascript">
	$('#consulta').submit(function(evento){
		var urlrest = 'coste/' + $('#matricula').val();
		$('#coste').load(encodeURI(urlrest));  //Petición GET al servicio REST y el resultado se visualiza en elemento #coste
		evento.preventDefault();
	})
</script>

</body>
</html>