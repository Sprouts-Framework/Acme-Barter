<%--
 * display.jsp
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
		
		<sprouts:display-column title="${moment}" path="modelObject.moment"/>
		<jstl:if test="${modelObject.requestSignedDate != null }">
			<sprouts:display-column title="${requestSignedDate}" path="modelObject.requestSignedDate"/>
		</jstl:if>
		<jstl:if test="${modelObject.requestSignedDate == null }">
			<sprouts:display-column title="${requestSignedDate}" data="${requestUserDidNotSign}"/>
		</jstl:if>
		
		<jstl:if test="${modelObject.offerSignedDate != null }">
			<sprouts:display-column title="${offerSignedDate}" path="modelObject.offerSignedDate"/>
		</jstl:if>
		<jstl:if test="${modelObject.offerSignedDate == null }">
			<sprouts:display-column title="${offerSignedDate}" data="${offerUserDidNotSign}"/>
		</jstl:if>
		
		<jstl:choose>
			<jstl:when test="${modelObject.cancelled == true}">
				<spring:message var="cancellation" code="match.cancelled.true"/>
			</jstl:when>
			<jstl:otherwise>
				<spring:message var="cancellation" code="match.cancelled.false"/>
			</jstl:otherwise>
		</jstl:choose>
		<sprouts:display-column title="${cancelled}" data="${cancellation}"/>


		
		<jstl:choose>
			<jstl:when test="${modelObject.report != null }">
				<sprouts:display-column title="${report}" data="${modelObject.report}"/>
			</jstl:when>
			<jstl:otherwise>
				<spring:message var="pending" code="match.nullCode"/>
				<sprouts:display-column title="${report}" data="${pending}"/>
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
<jstl:choose>
<jstl:when test="${modelObject.offered.cancelled == false }">
	<spring:message var="title" code="barter.title"/>
	<spring:message	var="creator" code ="barter.username" />
	
	<div class="text-center">
		<sprouts:display-column title="${title}" data="${modelObject.offered.title}"/>
		<sprouts:display-column title="${creator}" 
		data="${modelObject.offered.user.name } ${modelObject.offered.user.surname } (${modelObject.offered.user.userAccount.username })"/>
		<sprouts:button url="home/barter/${modelObject.offered.id}/show.do" code="match.barter.offer.button"/>
	</div>
</jstl:when>
<jstl:otherwise>
	<h4 class="text-center">
		<spring:message var="offeredBarterCancelled" code="match.barter.is.cancelled"/>
		<jstl:out value="${offeredBarterCancelled}"/>
	</h4>
</jstl:otherwise>
</jstl:choose>
<br />
<br />
<!-- Requested Barter -->

	<h3 class="text-center">
		<spring:message var="requestedBarter" code="match.barter.requested"/>
		<jstl:out value="${requestedBarter}"/>
	</h3>

<jstl:choose>
<jstl:when test="${modelObject.requested.cancelled == false }">
	<spring:message var="title" code="barter.title"/>
	<spring:message	var="creator" code ="barter.username" />
	
	<div class="text-center">
		<sprouts:display-column title="${title}" data="${modelObject.requested.title}"/>
		<sprouts:display-column title="${creator}" 
		data="${modelObject.requested.user.name } ${modelObject.requested.user.surname } (${modelObject.requested.user.userAccount.username })"/>
		<sprouts:button url="home/barter/${modelObject.requested.id}/show.do" code="match.barter.request.button"/>
	</div>

</jstl:when>
<jstl:otherwise>
	<h4 class="text-center">
		<spring:message var="requestedBarterCancelled" code="match.barter.is.cancelled"/>
		<jstl:out value="${requestedBarterCancelled}"/>
	</h4>
</jstl:otherwise>
</jstl:choose>
<br />
<br />

<!-- Auditor -->


	<h3 class="text-center">
		<spring:message var="auditor" code="match.auditor"/>
		<jstl:out value="${auditor}"/>
	</h3>

<jstl:if test="${modelObject.auditor != null }">
	<div class="text-center">
		<spring:message var="name" code="match.auditor.name"/>
		<spring:message var="surname" code="match.auditor.surname"/>
		<sprouts:display-column title="${name}" data="${modelObject.auditor.name}"/>
		<sprouts:display-column title="${surname}" data="${modelObject.auditor.surname}"/>
	</div>
</jstl:if>
<jstl:if test="${modelObject.auditor == null }">
	<div class="text-center">
		<spring:message var="auditorNotAssinged" code="match.auditor.not.assigned"/>
		<sprouts:display-column  data="${auditorNotAssinged}"/>
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
		<sprouts:display-column data="${modelObject.legalText.text}"/>
	</div>
	
	



