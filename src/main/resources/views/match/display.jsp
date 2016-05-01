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
		<spring:message var="cancelled" code="match.cancelled"/>
		<spring:message var="report" code="match.report"/>
	
		<acme:display-column title="${moment}" path="match.moment"/>
		<jstl:if test="${match.requestSignedDate != null }">
			<acme:display-column title="${requestSignedDate}" path="match.requestSignedDate"/>
		</jstl:if>
		<jstl:if test="${match.offerSignedDate != null }">
			<acme:display-column title="${offerSignedDate}" path="match.offerSignedDate"/>
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
	
	
<!-- Legal Text -->

	<div class="text-center">
		<spring:message var="legalText" code="match.legalText"/>
		<acme:display-column title="${legalText}" data="${match.legalText.text}"/>
	</div>
	
	
<!-- Auditor -->

	<jstl:if test="${match.auditor != null }">
		<h3 class="text-center">
			<spring:message var="auditor" code="match.auditor"/>
			<jstl:out value="${auditor}"/>
		</h3>
		
		<div class="text-center">
			<spring:message var="name" code="match.auditor.name"/>
			<spring:message var="surname" code="match.auditor.surname"/>
			<acme:display-column title="${name}" data="${match.auditor.name}"/>
			<acme:display-column title="${surname}" data="${match.auditor.surname}"/>
		</div>
	</jstl:if>


<!-- Requested Barter -->

	<h3 class="text-center">
		<spring:message var="offeredBarter" code="match.barter.offered"/>
		<jstl:out value="${offeredBarter}"/>
	</h3>

	<spring:message var="title" code="barter.title"/>
	<spring:message var="moment" code="barter.moment"/>
	
	<spring:message var="itemName" code="barter.item.name"/>
	<spring:message var="itemDescription" code="barter.item.description"/>
	<spring:message var="itemPictures" code="barter.item.pictures"/>
	
	<spring:message var="requestedName" code="barter.requested.name"/>
	<spring:message var="offeredName" code="barter.offered.name"/>
	
	<div class="text-center">
		<acme:display-column title="${title}" data="${match.requested.title}"/>
		<acme:display-column title="${moment}" path="match.requested.moment"/>
	</div>
	
<!-- Requested item -->
	
	<h3 class="text-center">
		<jstl:out value="${requestedName }"/>
	</h3>
	
	<div class="text-center">
		<acme:display-column title="${match.requested.requested.name}"/>
		<acme:display-column data="${match.requested.requested.description}"/>
		
		<br />
		
		<h4><acme:display-column title="${itemPictures}"/>	</h4>
		
		<acme:pictures-list pictures="${match.requested.requested.pictures } " size="${match.requested.requested.pictures.size() }"/>
	</div>
<!-- Offered item -->

<br />
<br />

	<h3 class="text-center">
		<jstl:out value="${offeredName }"/>
	</h3>
	
	<div class="text-center">
		<acme:display-column title="${match.requested.offered.name}"/>
		<acme:display-column data="${match.requested.offered.description}"/>
		
		<br />
		
		<h4><acme:display-column title="${itemPictures}"/>	</h4>
		
		<acme:pictures-list pictures="${match.requested.offered.pictures } " size="${match.requested.offered.pictures.size() }"/>
		
	</div>


<!-- Offered Barter -->

	<h3 class="text-center">
		<spring:message var="offeredBarter" code="match.barter.offered"/>
		<jstl:out value="${offeredBarter}"/>
	</h3>

	<spring:message var="title" code="barter.title"/>
	<spring:message var="moment" code="barter.moment"/>
	
	<spring:message var="itemName" code="barter.item.name"/>
	<spring:message var="itemDescription" code="barter.item.description"/>
	<spring:message var="itemPictures" code="barter.item.pictures"/>
	
	<spring:message var="requestedName" code="barter.requested.name"/>
	<spring:message var="offeredName" code="barter.offered.name"/>
	
	<div class="text-center">
		<acme:display-column title="${title}" data="${match.offered.title}"/>
		<acme:display-column title="${moment}" path="match.offered.moment"/>
	</div>
	
<!-- Requested item -->
	
	<h3 class="text-center">
		<jstl:out value="${requestedName }"/>
	</h3>
	
	<div class="text-center">
		<acme:display-column title="${match.offered.requested.name}"/>
		<acme:display-column data="${match.offered.requested.description}"/>
		
		<br />
		
		<h4><acme:display-column title="${itemPictures}"/>	</h4>
		
		<acme:pictures-list pictures="${match.offered.requested.pictures } " size="${match.offered.requested.pictures.size() }"/>
	</div>
<!-- Offered item -->

<br />
<br />

	<h3 class="text-center">
		<jstl:out value="${offeredName }"/>
	</h3>
	
	<div class="text-center">
		<acme:display-column title="${match.offered.offered.name}"/>
		<acme:display-column data="${match.offered.offered.description}"/>
		
		<br />
		
		<h4><acme:display-column title="${itemPictures}"/>	</h4>
		
		<acme:pictures-list pictures="${match.offered.offered.pictures } " size="${match.offered.offered.pictures.size() }"/>
		
	</div>