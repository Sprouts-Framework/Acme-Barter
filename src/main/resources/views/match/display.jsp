<%--
 * list.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
	
<%@ include file="../template/libraries.jsp" %>

<!-- Match -->

	<h3 class="text-center">
		<spring:message var="matchTitle" code="match.title"/>
		<jstl:out value="${matchTitle}"/>
	</h3>
	
	<div class="text-center">
	
		<spring:message var="moment" code="match.moment"/>
		<spring:message var="requestSignedDate" code="match.requestSignedDate"/>
		<spring:message var="offerSignedDate" code="match.offerSignedDate"/>
		<spring:message var="cancelled" code="match.cancelled.column"/>
		<spring:message var="report" code="match.report"/>
		<spring:message var="requestUserDidNotSign" code="match.request-user-did-not-sign" />
		<spring:message var="offerUserDidNotSign" code="match.offer-user-did-not-sign" />
		
		<acme:display-column title="${moment}" path="match.moment"/>
		<jstl:if test="${match.requestSignedDate != null }">
			<acme:display-column title="${requestSignedDate}" path="match.requestSignedDate"/>
		</jstl:if>
		<jstl:if test="${match.requestSignedDate == null }">
			<acme:display-column title="${requestSignedDate}" data="${requestUserDidNotSign}"/>
		</jstl:if>
		
		<jstl:if test="${match.offerSignedDate != null }">
			<acme:display-column title="${offerSignedDate}" path="match.offerSignedDate"/>
		</jstl:if>
		<jstl:if test="${match.offerSignedDate == null }">
			<acme:display-column title="${offerSignedDate}" data="${offerUserDidNotSign}"/>
		</jstl:if>
		
		<jstl:choose>
			<jstl:when test="${match.cancelled == true}">
				<spring:message var="cancellation" code="match.cancelled.true"/>
			</jstl:when>
			<jstl:otherwise>
				<spring:message var="cancellation" code="match.cancelled.false"/>
			</jstl:otherwise>
		</jstl:choose>
		<acme:display-column title="${cancelled}" data="${cancellation}"/>


		
		<jstl:choose>
			<jstl:when test="${match.report != null }">
				<acme:display-column title="${report}" data="${match.report}"/>
			</jstl:when>
			<jstl:otherwise>
				<spring:message var="pending" code="match.nullCode"/>
				<acme:display-column title="${report}" data="${pending}"/>
			</jstl:otherwise>
		</jstl:choose>
		
	</div>

<br />
<br />

<!-- Offered Barter -->

	<h3 class="text-center">
		<spring:message var="offeredBarter" code="match.barter.offered"/>
		<jstl:out value="${offeredBarter}"/>
	</h3>

	<spring:message var="title" code="barter.title"/>
	<spring:message	var="creator" code ="barter.username" />
	
	<div class="text-center">
		<acme:display-column title="${title}" data="${match.offered.title}"/>
		<acme:display-column title="${creator}" 
		data="${match.offered.user.name } ${match.offered.user.surname } (${match.offered.user.userAccount.username })"/>
		<acme:button url="home/barter/${match.offered.id}/show.do" code="match.barter.offer.button"/>
	</div>

<br />
<br />
<!-- Requested Barter -->

	<h3 class="text-center">
		<spring:message var="requestedBarter" code="match.barter.requested"/>
		<jstl:out value="${requestedBarter}"/>
	</h3>

	<spring:message var="title" code="barter.title"/>
	<spring:message	var="creator" code ="barter.username" />
	
	<div class="text-center">
		<acme:display-column title="${title}" data="${match.requested.title}"/>
		<acme:display-column title="${creator}" 
		data="${match.requested.user.name } ${match.requested.user.surname } (${match.requested.user.userAccount.username })"/>
		<acme:button url="home/barter/${match.requested.id}/show.do" code="match.barter.request.button"/>
	</div>

<br />
<br />

<!-- Auditor -->


	<h3 class="text-center">
		<spring:message var="auditor" code="match.auditor"/>
		<jstl:out value="${auditor}"/>
	</h3>

<jstl:if test="${match.auditor != null }">
	<div class="text-center">
		<spring:message var="name" code="match.auditor.name"/>
		<spring:message var="surname" code="match.auditor.surname"/>
		<acme:display-column title="${name}" data="${match.auditor.name}"/>
		<acme:display-column title="${surname}" data="${match.auditor.surname}"/>
	</div>
</jstl:if>
<jstl:if test="${match.auditor == null }">
	<div class="text-center">
		<spring:message var="auditorNotAssinged" code="match.auditor.not.assigned"/>
		<acme:display-column  data="${auditorNotAssinged}"/>
	</div>
</jstl:if>
	
<br />
<br />

<!-- Legal Text -->
	<h3 class="text-center">
		<spring:message var="legalText" code="match.legalText"/>
		<jstl:out value="${legalText}"/>
	</h3>

	<div class="text-center">
		<acme:display-column data="${match.legalText.text}"/>
	</div>
	
	



