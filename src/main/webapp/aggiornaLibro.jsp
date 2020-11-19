<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Modifica Libro</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<c:set var="titoloCercato" value="${ titoloCercato }" />
	<c:set var="tramaCercata" value="${ tramaCercata }" />
	<c:set var="genereCercato" value="${ genereCercato }" />
	<c:set var="autoreCercato" value="${ autoreCercato }" />
	
	
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
		        <h5>Modifica valori</h5> 
		    </div>
		    <div class='card-body'>

					<form method="post" action="AggiornaLibroServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Titolo <span class="text-danger"></span></label>
								<input type="text" name="titolo" id="titolo" class="form-control" placeholder="Inserire il titolo" value="<c:out value = "${ titoloDaAggiornare }"/>">                               
							</div>
							
							<div class="form-group col-md-6">
								<label>Genere <span class="text-danger"></span></label>
									<select name="genere" id="genere" class="form-control">
										<c:forEach items="${ listaGeneri}" var="genere">
											<c:if test="${ genere eq genereDaAggiornare }">
												<option value="${ genereDaAggiornare }" selected disabled hidden><c:out value = "${ genereDaAggiornare }"/></option>
											</c:if>
											<c:if test="${genere != 'EMPTY'}">
        										<option value="${genere}"><c:out value="${genere}"></c:out></option>
        									</c:if>
    									</c:forEach>
  									</select>
							</div>
							
							<div class="form-group col-md-6">
								<label>Trama <span class="text-danger"></span></label>
								<input type="text" name="trama" id="trama" class="form-control" placeholder="Inserire la trama" value="<c:out value = "${ tramaDaAggiornare }"/>">
							</div>
							
							<div class="form-group col-md-6">
								<label>Autore<span class="text-danger"></span></label>
								<select name="autore" id="autore" class="form-control">	
											
											
										<c:forEach items="${ listaAutori}" var="autore">
											<c:if test="${ autoreDaAggiornare.id == autore.id}">
												<option value="${ autoreDaAggiornare.id }" selected><c:out value="${autoreDaAggiornare.nome}"></c:out> <c:out value="${autoreDaAggiornare.cognome}"></c:out></option>
											</c:if>
        									<option value="${autore.id}"><c:out value="${autore.nome}"></c:out> <c:out value="${autore.cognome}"></c:out></option>
    									</c:forEach>
  								</select>
							</div>
							
							<input type="hidden" id="LibroDaAggiornare" name="LibroDaAggiornare" value="<c:out value="${LibroDaAggiornare}"/>">
							
							<input type="hidden" id="titoloCercato" name="titoloCercato" value="<c:out value="${titoloCercato}"/>">
							<input type="hidden" id="tramaCercata" name="tramaCercata" value="<c:out value="${tramaCercata}"/>">
							<input type="hidden" id="genereCercato" name="genereCercato" value="<c:out value="${genereCercato}"/>">
							<input type="hidden" id="autoreCercato" name="autoreCercato" value="<c:out value="${autoreCercato}"/>">
							
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