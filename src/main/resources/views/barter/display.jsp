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

	<spring:message var="title" code="barter.title"/>
	<spring:message var="moment" code="barter.moment"/>
	
	<spring:message var="itemName" code="barter.item.name"/>
	<spring:message var="itemDescription" code="barter.item.description"/>
	<spring:message var="itemPictures" code="barter.item.pictures"/>
	
	<spring:message var="requestedName" code="barter.requested.name"/>
	<spring:message var="offeredName" code="barter.offered.name"/>
	
	<spring:message var="barterUsername" code="barter.username"/>
	<spring:message var="relatedRequestedBarters" code="barter.requested.related"/>
	<spring:message var="relatedOfferedBarters" code="barter.offered.related"/>
	
	<div class="text-center">
		<sprouts:display-column title="${title}" data="${modelObject.title}"/>
		<sprouts:display-column title="${moment}" path="modelObject.moment"/>
		<br/>
		<sprouts:display-column title="${barterUsername}" 
		data="${modelObject.user.name } ${modelObject.user.surname } (${modelObject.user.userAccount.username })"/>
		
		<sprouts:button url="home/user/profile/${modelObject.user.id }/show.do" code="user.profile"/>
	</div>

<br />
<br />
<br />


<!-- Requested item -->
	
	<h3 class="text-center">
		<jstl:out value="${requestedName }"/>
	</h3>
	
	<div class="text-center">
		<sprouts:display-column title="${modelObject.requested.name}"/>
		<sprouts:display-column data="${modelObject.requested.description}"/>
		<h4><sprouts:display-column title="${itemPictures}"/>	</h4>
		<sprouts:pictures-list pictures="${modelObject.requested.pictures } " size="${modelObject.requested.pictures.size() }"/>
	</div>
	
<br />
<br /> 
	
<!-- Offered item -->

<br />
<br />

	<h3 class="text-center">
		<jstl:out value="${offeredName }"/>
	</h3>
	
	<div class="text-center">
		<sprouts:display-column title="${modelObject.offered.name}"/>
		<sprouts:display-column data="${modelObject.offered.description}"/>
		<h4><sprouts:display-column title="${itemPictures}"/>	</h4>
		<sprouts:pictures-list pictures="${modelObject.offered.pictures } " size="${modelObject.offered.pictures.size() }"/>
	</div>
	
	<br />
	<br />
	<h3 class="text-center">
		<jstl:out value="${relatedRequestedBarters }"/>
	</h3>
	
	<sprouts:data-table i18n="datatables.language"  source="home/barter/related/${modelObject.id},requesteds/list/data.do" searcheable="false" >
		<sprouts:data-column code="barter.username" path="user.userAccount.username" />
		<sprouts:data-column code="barter.moment" path="moment" sortable="true" format="date"/>
		<sprouts:data-column code="barter.title" path="title" />
		<sprouts:data-column code="barter.requested.name" path="requested.name" />
		<sprouts:data-column code="barter.offered.name" path="offered.name" />
		
		<sprouts:action-button url="home/barter/{0}/show.do" code="show.button"/>
		<security:authorize access="hasRole('User')">
			<sprouts:action-button url="match/user/{0}/create.do" code="match.create.title"/>
		</security:authorize>
		<security:authorize access="hasRole('Administrator')">
			<sprouts:action-button url="barter/administrator/relate/{0}/update.do" code="relate.button"/>
		</security:authorize>
	</sprouts:data-table>
	
	
	<br />
	<br />
	<h3 class="text-center">
		<jstl:out value="${relatedOfferedBarters }"/>
	</h3>
	
	<sprouts:data-table i18n="datatables.language"  source="home/barter/related/${modelObject.id},offereds/list/data.do" searcheable="false">
		<sprouts:data-column code="barter.username" path="user.userAccount.username" />
		<sprouts:data-column code="barter.moment" path="moment" sortable="true" format="date"/>
		<sprouts:data-column code="barter.title" path="title" />
		<sprouts:data-column code="barter.requested.name" path="requested.name" />
		<sprouts:data-column code="barter.offered.name" path="offered.name" />
		
		<sprouts:action-button url="home/barter/{0}/show.do" code="show.button"/>
		<security:authorize access="hasRole('User')">
			<sprouts:action-button url="match/user/{0}/create.do" code="match.create.title"/>
		</security:authorize>
		<security:authorize access="hasRole('Administrator')">
			<sprouts:action-button url="barter/administrator/relate/{0}/update.do" code="relate.button"/>
		</security:authorize>
	</sprouts:data-table>