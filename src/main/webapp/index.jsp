<%@page import="it.solving.model.utente.Utente"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="./header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Gestione della Biblioteca!</title>
  </head>
  <body>
  
  	<c:set var = "utenteInSessione" scope = "session" value = "${ utenteInSessione }"/>

	<c:forEach items="${ utenteInSessione.ruoli }" var="ruolo">
	    <c:if test="${ ruolo.codice == 'ADMIN_ROLE' }">
			<c:set var="admin" scope="session" value="1" />
		</c:if>
		<c:if test="${ ruolo.codice == 'CLASSIC_ROLE' }">
			<c:set var="classic" scope="session" value="1" />
		</c:if>
		<c:if test="${ ruolo.codice == 'GUEST_ROLE' }">
			<c:set var="guest" scope="session" value="1" />
		</c:if> 
	</c:forEach>


	<jsp:include page="./navbar.jsp"></jsp:include>
  
	<main role="main">

	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <h1 class="display-3">Benvenuto alla Gestione della Biblioteca!</h1>
	      <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
	      <c:if test = "${utenteInSessione != null && ( classic == '1' || guest == '1')}">
	      	<p>
	      		<a class="btn btn-primary btn-lg" href="PreparaRicercaLibroServlet" role="button">Ricerca Libro &raquo;</a>
	      		<a class="btn btn-primary btn-lg" href="PreparaRicercaAutoreServlet" role="button">Ricerca Autore &raquo;</a>
	      		<c:if test = "${utenteInSessione != null && admin == '1'}">
	      			<a class="btn btn-primary btn-lg" href="visualizzaUtenti.jsp">Gestione Utenti &raquo;</a>
	      		</c:if>
	      	</p>
	      </c:if>
	    </div>
	  </div>

	 <div class="container">
	    Example row of columns
	    <div class="row">
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	    </div>
	
	    <hr>
	
	  </div>

	</main>
	
	<jsp:include page="./footer.jsp" />
  </body>
</html>