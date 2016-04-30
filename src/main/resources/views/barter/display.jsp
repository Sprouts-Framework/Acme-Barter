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

	<spring:message var="title" code="barter.title"/>
	<spring:message var="moment" code="barter.moment"/>
	
	<spring:message var="itemName" code="barter.item.name"/>
	<spring:message var="itemDescription" code="barter.item.description"/>
	<spring:message var="itemPictures" code="barter.item.pictures"/>
	
	<spring:message var="requestedName" code="barter.requested.name"/>
	<spring:message var="offeredName" code="barter.offered.name"/>
	
	<div class="text-center">
		<acme:display-column title="${title}" data="${barter.title}"/>
		<acme:display-column title="${moment}" path="barter.moment"/>
	</div>
	
<!-- Requested item -->
	
	<h3 class="text-center">
		<jstl:out value="${requestedName }"/>
	</h3>
	
	<div class="text-center">
		<acme:display-column title="${barter.requested.name}"/>
		<acme:display-column data="${barter.requested.description}"/>
		
		<br />
		
		<h4><acme:display-column title="${itemPictures}"/>	</h4>
		
		<acme:pictures-list pictures="${barter.requested.pictures } " size="${barter.requested.pictures.size() }"/>
	</div>
<!-- Offered item -->

<br />
<br />

	<h3 class="text-center">
		<jstl:out value="${offeredName }"/>
	</h3>
	
	<div class="text-center">
		<acme:display-column title="${barter.offered.name}"/>
		<acme:display-column data="${barter.offered.description}"/>
		
		<br />
		
		<h4><acme:display-column title="${itemPictures}"/>	</h4>
		
		<acme:pictures-list pictures="${barter.offered.pictures } " size="${barter.offered.pictures.size() }"/>
		
	</div>