<%@page import="it.solving.model.utente.Utente"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Pagina dei risultati</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<c:set var="nomeAutoreCercato"  value="${ nomeAutoreCercato }" />
		<c:set var="cognomeAutoreCercato"  value="${ cognomeAutoreCercato }" />
		<c:set var="dataNascitaAutoreCercato"  value="${ dataNascitaAutoreCercato }" />
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<c:set var = "utenteInSessione" scope = "session" value = "${ utenteInSessione}"/>
		<c:if test="${ utenteInSessione == null }">
			<c:redirect url="/index.jsp"/>
		</c:if>

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

		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista dei risultati</h5> 
		    </div>
		    <div class='card-body'>
		    	<c:if test="${admin == '1' || classic == '1'}">
		    		<a class="btn btn-primary" href="PreparaInserimentoAutoreServlet?nomeAutoreCercato=<c:out value = "${nomeAutoreCercato}"/>&cognomeAutoreCercato=<c:out value = "${cognomeAutoreCercato}"/>&dataNascitaAutoreCercato=<c:out value = "${dataNascitaAutoreCercato}"/>">Add New</a>
		    	</c:if>
		    	
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Nome</th>
		                        <th>Cognome</th>
		                        <th>Data di Nascita</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	
		                	<c:forEach items="${listaAutori}" var="autore" >
         						
         					<tr>
		                        <td>${ autore.id }</td>
		                        <td>${ autore.nome }</td>
		                        <td>${ autore.cognome }</td>
		                        <td>${ autore.data_nascita }</td>
		                        <td>
		                        	 
									<c:if test = "${admin == '1' || classic == '1'}">
										<a class="btn  btn-sm btn-outline-secondary" href="ShowArticoloServlet?IdDaVisualizzare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Visualizza</a>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateArticoloServlet?IdDaAggiornare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Edit</a>
										<a class="btn btn-outline-danger btn-sm" href="PreparaEliminazioneLibroServlet?LibroDaEliminare=<c:out value = "${libro.id}"/>">Delete</a>
									</c:if>
									<c:if test = "${guest == '1' && admin != '1' && classic != '1'}">
										<a class="btn  btn-sm btn-outline-secondary" href="ShowArticoloServlet?IdDaVisualizzare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Visualizza</a>
									</c:if>
									
								</td>
		                    </tr>
         						
     						 </c:forEach>
		              
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	
	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>