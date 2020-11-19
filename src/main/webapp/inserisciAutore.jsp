<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Inserisci Autore</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />

	<c:set var="nomeAutoreCercato" value="${ nomeAutoreCercato }" />
	<c:set var="cognomeAutoreCercato" value="${ cognomeAutoreCercato }" />
	<c:set var="dataNascitaAutoreCercato" value="${ dataNascitaAutoreCercato }" />

	<main role="main" class="container">
	
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
		        <h5>Inserisci valori</h5> 
		    </div>
		    <div class='card-body'>

					<form method="post" action="InserisciAutoreServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger"></span></label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome">
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Cognome <span class="text-danger"></span></label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome">
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Data di Nascita <span class="text-danger"></span></label>
								<input type="date" name="dataNascita" id="dataNascita" class="form-control" placeholder="Inserire il cognome">
							</div>
							
							<input type="hidden" id="nomeAutoreCercato" name="nomeAutoreCercato" value="<c:out value="${nomeAutoreCercato}"/>">
							<input type="hidden" id="cognomeAutoreCercato" name="cognomeAutoreCercato" value="<c:out value="${cognomeAutoreCercato}"/>">
							<input type="hidden" id="dataNascitaAutoreCercato" name="dataNascitaAutoreCercato" value="<c:out value="${dataNascitaAutoreCercato}"/>">
			
							
							
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Aggiungi</button>
					

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>