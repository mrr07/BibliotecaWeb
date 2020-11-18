<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	 pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Login</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
    <script type="text/javascript" language="javascript">
        function validate() {
            
            if (document.login.username.value == null || document.login.username.value == "") {
                alert("Attenzione, username e/o password vuoti");
                return false;
            }
            if (document.login.password.value == null || document.login.password.value == "") {
                alert("Attenzione, username e/o password vuoti");
                return false;
            }

			return true;
            
        } 
	</script>
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
	
		<c:if test="${ utenteInSessione != null }">
			<c:redirect url="index.jsp"/>
		</c:if>
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
			 	Operazione fallita!
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
		</div>
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Inserisci credenziali utente</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form method="post" name="login" action="LoginServlet" onsubmit="return validate();">
					
						<div class="form-row" >
							<div class="form-group col-md-6">
								<label>Username <span class="text-danger">*</span></label>
								<input type="text" name="username" id="username" class="form-control" placeholder="Inserire username" required>
							</div>
							
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-3">
								<label>Password <span class="text-danger">*</span></label>
								<input type="password" class="form-control" name="password" id="password" placeholder="Inserire password" required>
							</div>
							
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					

					</form>
					
					

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>