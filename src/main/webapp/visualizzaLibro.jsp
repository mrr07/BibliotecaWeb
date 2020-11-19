<%@page import="it.solving.model.utente.Utente"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Visualizza elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<c:set var="titoloCercato"  value="${ titoloCercato }" />
	<c:set var="tramaCercata" value="${ tramaCercata }" />
	<c:set var="genereCercato" value="${ genereCercato }" />
	<c:set var="autoreCercato" value="${ autoreCercato }" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		    
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">ID:</dt>
				  <dd class="col-sm-9"><c:out value = "${LibroDaVisualizzare}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">titolo:</dt>
				  <dd class="col-sm-9"><c:out value = "${titoloDaVisualizzare}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">trama:</dt>
				  <dd class="col-sm-9"><c:out value = "${tramaDaVisualizzare}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">genere:</dt>
				  <dd class="col-sm-9"><c:out value = "${genereDaVisualizzare}"/></dd>
		    	
		    	</dl>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">autore:</dt>
				  <dd class="col-sm-9"><c:out value = "${autoreDaVisualizzare}"/></dd>
		    	</dl>
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="RicercaLibroServlet?titolo=<c:out value = "${ titoloCercato }"/>&trama=<c:out value = "${ tramaCercata }"/>&genere=<c:out value = "${ genereCercato }"/>&autore=<c:out value = "${ autoreCercato }"/>" class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>