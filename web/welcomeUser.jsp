<%@page import="modelo.Consulta"%>
<%@page import="modelo.Profesor"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="modelo.Usuario"%>


<%
        Consulta consulta = new Consulta();
        Usuario usuario = (Usuario)session.getAttribute("user");
        Profesor profesor = consulta.getProfesor(usuario.getUsuario());
    %>
    
<!DOCTYPE html>

<html

<head>
    
    
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Bienvenido Profesor</title>

    <link rel="icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/en/1/1f/Universidad_Nacional_Costa_Rica.png" />

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/half-slider.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">
  <script>

        function updatePassword(){
            if(document.getElementById("contrasena1" ).value  ==  document.getElementById("contrasena2" ).value  ){

                document.getElementById("caja" ).innerHTML=" <p class='alert alert-success'>Contrasena modificada con exito</p>"; 

            }


        }
        
        function miPerfil(){
            

                var tabla = "    <div class='container' style='padding-top: 60px;'>";
                tabla+=" <h1 class='page-header'>Mi perfil</h1>";
                tabla+="<div class='row'>";
                tabla+="<div class='col-md-4 col-sm-6 col-xs-12'>";
                tabla+="<div class='text-center'>";    
                

                tabla+="<img src= 'pictures/usuario.jpg' class='avatar img-circle img-thumbnail'alt='avatar'>";  
                tabla+="<h6>Cargando una nueva foto...</h6>";
                tabla+="<input type='file' class= 'text-center center-block well well-sm'>";
                tabla+="</div>";
                tabla+="</div>";
                tabla+="<!-- edit form column -->";

                tabla+="<div class='col-md-8 col-sm-6 col-xs-12 personal-info'>";
              
                tabla+=" <h3>Informacion Personal</h3>";     
          
               
                tabla+="  <form class='form-horizontal' role='form'>";   
                tabla += "<div class='alert alert-info' >";

                    tabla+="<div class='form-group'>";                
                    tabla+="<label class='col-lg-3 control-label'>Nombre:</label>";  
                    tabla+="<div class='col-lg-8'>";                
                    tabla+="  <input class='form-control'  id= 'nombre' value='Adan' type='text'>";                     
                    tabla+=" </div>";                  
                    tabla+="    </div>";     

                    tabla+="<div class='form-group'>";                
                    tabla+="<label class='col-lg-3 control-label'>Primer Apellido:</label>";  
                    tabla+="<div class='col-lg-8'>";                
                    tabla+="  <input class='form-control' id='apellido1' value='Carranza' type='text'>";                     
                    tabla+=" </div>";                  
                    tabla+="    </div>";     

                    tabla+="<div class='form-group'>";                
                    tabla+="<label class='col-lg-3 control-label'>Segundo Apellido:</label>";  
                    tabla+="<div class='col-lg-8'>";                
                    tabla+="  <input class='form-control' id ='apellido2' value='Alfaro' type='text'>";                     
                    tabla+=" </div>";                  
                    tabla+="    </div>";     

                    

                    tabla+="<div class='form-group'>";                
                    tabla+="<label class='col-lg-3 control-label'>Telefono:</label>";  
                    tabla+="<div class='col-lg-8'>";                
                    tabla+="  <input class='form-control' id = 'telefono' value='5012-2202' type='text'>";                     
                    tabla+=" </div>";                  
                    tabla+="    </div>";     


                    tabla+="<div class='form-group'>";                
                    tabla+="<label class='col-lg-3 control-label'>Descripcion:</label>";  
                    tabla+="<div class='col-lg-8'>";                
                    tabla+="  <input class='form-control'  id = 'descripcion' value='Soy profesor de Matematicas para informatica.... ' type='text'>";                     
                    tabla+=" </div>";                  
                    tabla+="    </div>";     



                    tabla+="<div class='form-group'>";
                    tabla+="<label class='col-md-4 control-label' for='signup'></label>";
                    tabla+="<div class='col-md-4'>";
                    tabla+="<button id='guardar' name='guardar' class='btn btn-success'>Guardar</button>";
                    tabla+="</div>";
                    tabla+="</div>";
                tabla+="</div>";  
                
            

                tabla+=" <h3>Seguridad Personal</h3>";   



                 tabla+="<div class='form-group'>";                
                tabla+="<div  id ='caja'></div>";                            //Notificacion del la modificacion de la conrtrasena..   
                tabla+="<div class='alert alert-warning'>";

                    tabla+="<div class='form-group'>";                
                    tabla+="<label class='col-lg-3 control-label'>Antigua Contrasena:</label>";  
                    tabla+="<div class='col-lg-8'>";                
                    tabla+="  <input class='form-control'  id = 'lastpassword' placeholder='Digite la antigua contrasena' type='password'>";                     
                    tabla+=" </div>";                  
                    tabla+="    </div>";     


                    tabla+="<div class='form-group'>";                  
                    tabla+="<label class='col-lg-3 control-label'>Nueva contrasena:</label>";  
                    tabla+="<div class='col-lg-8'>";                
                    tabla+="  <input class='form-control'  id = 'contrasena1' placeholder='Nueva contrasena' type='password'>";                     
                    tabla+=" </div>";                  
                    tabla+="    </div>";     

                    tabla+="<div class='form-group'>";                
                    tabla+="<label class='col-lg-3 control-label'>Repetir contrasena:</label>";  
                    tabla+="<div class='col-lg-8'>";                
                    tabla+="  <input class='form-control'  id = 'contrasena2' placeholder = 'Repita la contrasena' type='password'>";                     
                    tabla+=" </div>";                  
                    tabla+="  </div>";  

                    tabla+="<div class='form-group'>";
                    tabla+="<label class='col-md-4 control-label' for='signup'></label>";
                    tabla+="<div class='col-md-4'>";
                    tabla+="<button id='guardar' name='guardar' class='btn btn-warning'  type='button' onclick = 'updatePassword()'>Actualizar</button>";
              
                tabla+="</div>";
                tabla+=" </div>";                  
                tabla+=" </div>";     


            document.getElementById("capaInformacion").innerHTML = tabla;
        }
        
   function nobackbutton(){
        window.location.hash="index.html";
        window.location.hash="Again-No-back-button" //chrome
        window.onhashchange=function(){window.location.hash="index.html";}
      
    }
    
    

        </script>

                
        
</head>

<body onload="nobackbutton()">
    
    
<header>
    <div class="container-right">
        <a href="http://www.una.ac.cr" > <img src="pictures/imagenUna.png " class="img-responsive"> </a>
    </div>
</header>


    <!-- Navigation -->
    <nav class="navbar navbar-inverse" role="navigation" >
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Barra</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>


           
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"   >
                                     
                <ul class="nav navbar-nav">

                   <li style="color: white" class="navbar-brand">
                        <%
                            out.print("Bienvenido  "+usuario.getNombre());%> <!---  Capturar el valor de la cedula del  usuario -->
                        
                    </li>
                    
                    <li class="navbar-brand">
                        <form action="Consulta">
                             <input type="submit"  value="Curriculos"  class="btn btn-danger">
                        </form>
               
                    </li>

                 </ul>
       <!-- Area de cerrar seccion -->
                <table align ="right" border="0px" width="100px">
                    <td> 
                         <a href="#" >
                     <img onclick="miPerfil()" src= "https://scontent.fsjo1-1.fna.fbcdn.net/v/t1.0-9/15095084_1137771492937627_9134471628151648159_n.jpg?oh=644ab5b32c45c0fdeca8793ea7874e19&oe=5986CBB7" class="img-circle img-thumbnail " >
                        </a>

                    </td>
                  
                    <td alingn="center"> 
                        <form action="cerrarSesion">
                            <input type="submit" value="Salir"  class="btn btn-danger">
                        </form>
                            
                       
                    </td>
                    
                </table>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Half Page Image Background Carousel Header -->
    <header id="myCarousel" class="carousel slide"  >
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

       <!-- Wrapper for Slides -->
        <div class="carousel-inner" style="margin-top: -13px;">
            <div class="item active">
                <!-- Set the first background image using inline CSS below. -->
                <div class="fill" style="background-image:url('http://www.una.ac.cr/images/resized/images/sampledata/slideshow/022%20Banner%20PAG%20WEB%20INST-%20cita%20calendario_960_278.png ');"></div>
            </div>
            <div class="item">
                <!-- Set the second background image using inline CSS below. -->
                <div class="fill" style="background-image:url('http://www.una.ac.cr/images/resized/images/sampledata/slideshow/023%20Banner%20PAG%20WEB%20INST-%20cita%20calendario_960_278.png ');"></div>
             
            </div>
            <div class="item">
                <!-- Set the third background image using inline CSS below. -->
                <div class="fill" style="background-image:url('http://www.una.ac.cr/images/resized/images/sampledata/slideshow/021%20Banner%20PAG%20WEB%20INST-%20cita%20calendario_960_278.png');"></div>
            </div>


              <div class="item">
                <!-- Set the third background image using inline CSS below. -->
                <div class="fill" style="background-image:url('http://www.una.ac.cr/images/resized/images/sampledata/slideshow/020%20Banner%20PAG%20WEB%20INST-%20cita%20calendario_960_278.png ');"></div>
            </div>

        </div>


        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>

    </header>

    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <div class="col-lg-12" id="capaInformacion">
                
            </div>
        </div>


        <hr>

        <!-- Footer -->
        <footer>
            <div class="row" align="center" >
                <div class="col-lg-12">
                    <p>Copyright &copy; Creado bajo la normativa de Universidad Nacional de Costa Rica </p>
                    <p>Compania: Carranza-Alfaro</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>


