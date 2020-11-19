<%@page import="it.solving.model.libro.Libro"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Conferma Eliminazione</title>
	
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
		
		<c:set var = "utenteInSessione" scope = "session" value = "${utenteInSessione}"/>
		<c:if test="${ utenteInSessione == null }">
			<c:redirect url="/index.jsp"/>
		</c:if>
		
		<c:set var = "libro" value = "${LibroDaEliminare}"/>
		<c:set var="titoloCercato" value="${ titoloCercato }" />
		<c:set var="tramaCercata" value="${ tramaCercata }" />
		<c:set var="genereCercato" value="${ genereCercato }" />
		<c:set var="autoreCercato" value="${ autoreCercato }" />
		<p><a class="btn btn-primary btn-lg" href="EliminazioneLibroServlet?LibroDaEliminare=<c:out value = "${libro}"/>&titoloCercato=<c:out value = "${ titoloCercato }"/>&tramaCercata=<c:out value = "${ tramaCercata }"/>&genereCercato=<c:out value = "${ genereCercato }"/>&autoreCercato=<c:out value = "${ autoreCercato }"/>" role="button">Conferma &raquo; </a></p>
		<p><a class="btn btn-primary btn-lg" href="RicercaLibroServlet?&titolo=<c:out value = "${ titoloCercato }"/>&trama=<c:out value = "${ tramaCercata }"/>&genere=<c:out value = "${ genereCercato }"/>&autore=<c:out value = "${ autoreCercato }"/>" role="button">Annulla &raquo;</a></p>
</main>
<jsp:include page="./footer.jsp" />
</body>
</html>