<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Jasper Reports</title>

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>

<script type="application/javascript">

window.onload = function(){
	
}


</script>

<body>

 	<div class="text-center page-header">
        <h2 class="mt-4 mb-4">Aplicación de gestion</h2>
    </div>   
    
    <div class="navbar navbar-expand-sm bg-dark navbar-dark">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">Clientes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Pedidos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Facturas</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="#">Informes</a>
            </li>
        </ul>
    </div> 
    
    <div class="container-fluid">
    
    	<div class="row">
    	
    		<div class="col-12 col-md-8 offset-md-2">
    
			    <h3 class="text-center mt-3 mb-3">
			        Formulario de informes
			    </h3>
			    
			    <!-- PEDIDOS POR CLIENTES -->
			    
			    <hr class="mt-4 mb-4"/>
			    
			    <h3>Pedidos por cliente</h3>
			    
			    <form action="SVInformes" method="POST" target="_blanck" class="row">
		    		<input type="hidden" name="nombreInforme" value="PedidosCliente"/>
			        
		            <div class="col-3 mt-1">
		                <label>Código de cliente</label>
		            </div>
		            
		            <div class="col-6 mt-1">
		                <input type="text" name="codigoCliente" class="form-control" required="required"/>
		            </div>

		            <div class="col-3 mt-1">
		                <input type="submit" name="formato" class="btn btn-primary" value="HTML"/>
		                <input type="submit" name="formato" class="btn btn-primary" value="PDF"/>
		            </div>
			    </form>
			    
			    <!-- EMPLEADOS POR OFICINAS -->
			    
			    <hr class="mt-4 mb-4"/>
			    
			    <h3>Empleados por oficina</h3>
			    
			    <form action="SVInformes" method="POST" target="_blanck" class="row">
		    		<input type="hidden" name="nombreInforme" value="EmpleadosOficina"/>
			        
		            <div class="col-3 mt-1">
		                <label>Código de la oficina (op)</label>
		            </div>
		            
		            <div class="col-6 mt-1">
		                <input type="text" name="codigoOficina" class="form-control" placeholder="opcional"/>
		            </div>

		            <div class="col-3 mt-1">
		                <input type="submit" name="formato" class="btn btn-primary" value="HTML"/>
		                <input type="submit" name="formato" class="btn btn-primary" value="PDF"/>
		            </div>
			    </form>
			    
			    <hr class="mt-4 mb-4"/>			    
			    
			</div>
			    
    	</form>
    
    </div>

</body>
</html>