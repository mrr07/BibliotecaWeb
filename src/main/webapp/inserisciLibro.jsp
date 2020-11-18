<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Inserisci Libro</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
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

					<form method="post" action="InserimentoLibroServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Titolo <span class="text-danger"></span></label>
								<input type="text" name="titolo" id="titolo" class="form-control" placeholder="Inserire il titolo">
							</div>
							
							<div class="form-group col-md-6">
								<label>Genere <span class="text-danger"></span></label>
									<select name="genere" id="genere" class="form-control">
										<c:forEach items="${ listaGeneri}" var="genere">
											<option value="" selected disabled hidden>Seleziona Genere</option>
											<c:if test="${genere != 'EMPTY'}">
        										<option value="${genere}"><c:out value="${genere}"></c:out></option>
        									</c:if>
    									</c:forEach>
  									</select>
							</div>
							
							<div class="form-group col-md-6">
								<label>Trama <span class="text-danger"></span></label>
								<input type="text" name="trama" id="trama" class="form-control" placeholder="Inserire la trama">
							</div>
							
							<div class="form-group col-md-6">
								<label>Autore <span class="text-danger"></span></label>
								<select name="autore" id="autore" class="form-control">
										<c:forEach items="${ listaAutori}" var="autore">
											<option value="" selected disabled hidden>Seleziona Autore</option>
        									<option value="${autore.id}"><c:out value="${autore.nome}"></c:out> <c:out value="${autore.cognome}"></c:out></option>
    									</c:forEach>
  								</select>
							</div>
							
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