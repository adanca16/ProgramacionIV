<!DOCTYPE html>
<html lang="en">
    <head> 
		<meta name="viewport" content="width=device-width, initial-scale=1">
    	<!-- Website CSS style -->

    <link rel="icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/en/1/1f/Universidad_Nacional_Costa_Rica.png" />

		<link href="css/bootstrap.min.css" rel="stylesheet">
    
    	<link href="css/estilos.css" rel="stylesheet">
		<!-- Google Fonts -->
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

		<title>Agregando Formacion Academica</title>

        



	</head>
        <script>
        function nobackbutton(){
            //window.location.hash="no-back-button";
            window.location.hash="";
            window.location.hash="Again-No-back-button" //chrome
            window.onhashchange=function(){window.location.hash="";}
         }
         </script>

    
    
    
<body onload="nobackbutton()">
    
    <header>
        <div class="container-right">
            <img src="pictures/imagenUna.png">
        </div>
    </header>
		
    <form class="form-horizontal"  action ="servletRegistrandoFormacionAcademica" method="POST">
<fieldset>

<!-- Form Name -->
<legend>Agregando Formacion Academica</legend>

<!-- Select Provincia -->
<div class="form-group">
  <label class="col-md-4 control-label" for="listaCurso">Grado Academico </label>
  <div class="col-md-4">
    <select id="areaLaboral" name="gradoAcademico" class="form-control" required=""  >
      
      <option value="Diplomado">Diplomado</option>
      <option value="Tecnico">Tecnico</option>
      <option value="Bachiller">Bachiller</option>
      <option value="Licenciatura">Licenciatura</option>
      <option value="Maestria">Maestria</option>
      <option value="Doctorado">Doctorado</option>
    </select>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="expeLaboral">Centro Educativo: </label>  
  <div class="col-md-4">
      <input id="experienciaLaboral" name="centroTitulo" type="text" placeholder="Nombre del Centro Educativo" class="form-control input-md" required="">    
  </div>
</div>


<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="expeLaboral">Titulo en: </label>  
  <div class="col-md-4">
      <input id="experienciaLaboral" name="nombreTitulo" type="text" placeholder="Ejemplo: Ingeniero en Sistemas" class="form-control input-md" required="">    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="expeLaboral">Obtenido el </label>  
  <div class="col-md-4">
      <input id="experienciaLaboral" name="tituloObtenido" type="date" placeholder="Año en que lo optuvo" class="form-control input-md" required="">    
  </div>
</div>
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="expeLaboral">Cantidad de años Laborados en el area</label>  
  <div class="col-md-4">
      <input id="experienciaLaboral" name="areaLaboral" type="number" placeholder="Años que laboro en esta area" class="form-control input-md" required="">    
  </div>
</div>



<div class="form-group">
  <label class="col-md-4 control-label" for="signup"></label>
  <div class="col-md-4">  
    <button id="signup" name="addNew" class="btn btn-primary">Agregar Otra</button>
  
    <button id="signup" name="signup" class="btn btn-info">Continuar</button>
  </div>
  
</div>

</fieldset>
</form>


     <!-- Footer -->
        <footer>
            <div class="row" align="center">
                <div class="col-lg-12">
                    <p>Copyright &copy; Creado bajo la normativa de Universidad Nacional de Costa Rica </p>
                    <p>Compania: Carranza-Alfaro</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>



		 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	</body>
</html>