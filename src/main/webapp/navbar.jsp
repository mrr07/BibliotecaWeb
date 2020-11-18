<!-- navbar -->

<%@page import="it.solving.model.utente.Utente"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="it">
<c:set var="utenteInSessione" scope="session" value="${ utenteInSessione }" />

<!-- 
se l'utente è null vuol dire che non è presente nessun utente in sessione 
-->
<c:if test="${ utenteInSessione == null }">
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
			</ul>
			<a class="btn btn-success" href="login.jsp">Login</a>
		</div>
	</nav>
</c:if>

<!-- mi scorro tutti i ruoli dell'utente in sessione e imposto la variabile ruolo -->
<c:forEach items="${ utenteInSessione.ruoli }" var="ruolo">
	<c:if test="${ ruolo.codice == 'ADMIN_ROLE' }">
		<c:set var="ruolo" scope="session" value="${ ruolo.codice }" />
	</c:if>
	<c:if test="${ ruolo.codice == 'CLASSIC_ROLE' }">
		<c:set var="ruolo" scope="session" value="${ ruolo.codice }" />
	</c:if>
	<c:if test="${ ruolo.codice == 'GUEST_ROLE' }">
		<c:set var="ruolo" scope="session" value="${ ruolo.codice }" />
	</c:if>
</c:forEach>

<!-- se il ruolo è admin o classic allora gli è consentito di inserire nuovo  -->
<c:if test="${ ruolo == 'ADMIN_ROLE' || ruolo == 'CLASSIC_ROLE' || ruolo == 'GUEST_ROLE' }">
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
			</ul>

			<c:set var="logout" scope="session" value="${ utenteInSessione }" />

			<a>Bentornato/a <c:out value="${ utenteInSessione.nome }" /></a>
		    <a class="btn btn-success" href="LogoutServlet">Logout</a>
		</div>
	</nav>
</c:if>